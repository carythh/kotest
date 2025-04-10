package io.kotest.property

import io.kotest.property.arbitrary.of

/**
 * A [Gen] is responsible for providing values to be used in property testing. You can think of it as like
 * an input stream for values. Each arg will provide data for a specific type <A>.
 *
 * Gens can be created in two ways: with arbitrary (random) values from instances of [Arb] and
 * exhaustive values over a closed space from instances of [Exhaustive].
 *
 * Arbs generate random values across a given space. The values may be repeated, and some
 * values may never be generated at all. For example generating 1000 random integers between 0 and Int.MAX
 * will clearly not return all possible values, and some values may happen to be generated more than once.
 *
 * Exhaustives generate all values from a given space. This is useful when you want to ensure every
 * value in that space is used. For instance for enum values, it is usually more helpful to ensure each
 * enum is used, rather than picking randomly from the enums values.
 *
 * Both types of gens can be mixed and matched in property tests. For example,
 * you could test a function with 100 random positive integers (arbitrary) alongside every
 * even number from 0 to 200 (exhaustive).
 */
sealed class Gen<out A> {

   /**
    * Returns values from this generator as a lazily generated sequence.
    *
    * If this gen is an [Arb], then each value will either be a sample or an edgecase. The bias
    * towards edgecases or samples is given by the value of [EdgeConfig.edgecasesGenerationProbability]
    * inside the [edgeConfig] parameter.
    *
    * If this gen is an [Exhaustive], then the returned values will iterate in turn, repeating
    * once exhausted as required.
    *
    */
   fun generate(
      rs: RandomSource,
      edgeConfig: EdgeConfig = EdgeConfig.default()
   ): Sequence<Sample<A>> =
      when (this) {
         is Arb -> {
            val samples = this.samples(rs).iterator()
            generateSequence {
               val isEdgeCase = rs.random.nextDouble(0.0, 1.0) < edgeConfig.edgecasesGenerationProbability
               if (isEdgeCase) {
                  this.edgecase(rs)?.asSample() ?: samples.next()
               } else samples.next()
            }
         }
         is Exhaustive -> {
            check(this.values.isNotEmpty()) { "Exhaustive.values shouldn't be a empty list." }
            generateSequence { this.values.map { Sample(it) } }.flatten()
         }
      }

   /**
    * The minimum iteration count required for this [Gen] to be invoked.
    * Requesting a property test with fewer than this will result in an exception.
    */
   fun minIterations(): Int = when (this) {
      is Arb -> 1
      is Exhaustive -> this.values.size
   }
}

/**
 * An [Arb] (short for arbitrary) is a generator of values in two categories: edgecases and samples.
 *
 * Edgecases are values that are a common source of bugs. For example, a function using ints is
 * more likely to fail for common edge cases like zero, minus 1, positive 1, [Int.MAX_VALUE] and [Int.MIN_VALUE]
 * rather than random values like 965489. Therefore it is useful that we try to include such values
 * rather than relying entirely on random values which are unlikely to generate these.
 *
 * Not all arbitraries will utilize edge cases. For example, if you define an integer generator
 * using a subset of the number space - say from 100 to 200 - then no edge cases would be provided.
 *
 * Samples are chosen randomly from the sample space and are used to give a greater breadth to
 * the test cases. For example, in the case of a function using integers, these random values
 * could be from across the entire integer number line, or could be limited to a subset of ints
 * such as natural numbers or even numbers.
 *
 * In order to use an [Arb] outside a property test, one must invoke the [take] method, passing in
 * the number of iterations required and optionally a [ShrinkingMode].
 */
abstract class Arb<out A> : Gen<A>() {

   /**
    * Returns a single edgecase for this arbitrary.
    * If this arb provides mutliple edgecases, then one should be chosen randomly.
    * Can return null if no edgecases are available.
    */
   abstract fun edgecase(rs: RandomSource): A?

   /**
    * Returns a random [Sample] from this [Arb] using the supplied random source.
    */
   open fun sample(rs: RandomSource): Sample<A> = values(rs).first()

   @Deprecated(
      "implement one value at a time using sample(rs). This function will be removed in 4.7",
      ReplaceWith("sample(rs)")
   )
   open fun values(rs: RandomSource): Sequence<Sample<A>> = emptySequence()

   /**
    * Returns a sequence from values generated from this arb.
    * Edgecases will be ignored.
    */
   fun samples(rs: RandomSource = RandomSource.default()): Sequence<Sample<A>> {
      val valuesIterator = values(rs).iterator()
      return if (valuesIterator.hasNext()) {
         generateSequence { valuesIterator.next() }
      } else {
         generateSequence { sample(rs) }
      }
   }

   companion object
}

/**
 * An exhaustive is a type of [Gen] which generates an exhaustive set of values from a defined range.
 *
 * An example of a exhaustive is the sequence of integers from 0 to 100.
 * Another example is all strings of two characters.
 *
 * A progression is useful when you want to generate an exhaustive set of values from a given
 * sample space, rather than random values from that space. For example, if you were testing a
 * function that used an enum, you might prefer to guarantee that every enum value is used, rather
 * than selecting randomly from amongst the enum values (with possible duplicates and gaps).
 *
 * Exhaustives do not shrink their values. There is no need to find a smaller failing case, because
 * the smaller values will themselves naturally be included in the tested values.
 *
 * An exhaustive is less suitable when you have a large sample space you need to select values from.
 */
abstract class Exhaustive<out A> : Gen<A>() {

   /**
    * Returns the values of this [Exhaustive].
    */
   abstract val values: List<A>

   /**
    * Converts this into an [Arb] where the generated values of the returned arb
    * are choosen randomly from the values provided by this exhausive.
    */
   fun toArb(): Arb<A> = Arb.of(values)

   companion object
}

/**
 * Contains a single generated value from a [Gen] and an [RTree] of lazily evaluated shrinks.
 */
data class Sample<out A>(val value: A, val shrinks: RTree<A> = RTree({ value }))

fun <A> A.asSample(): Sample<A> = Sample(this)

/**
 * Returns a [Sample] with shrinks by using the supplied [Shrinker] against the input value [a].
 */
fun <A> sampleOf(a: A, shrinker: Shrinker<A>) = Sample(a, shrinker.rtree(a))

data class EdgeConfig(
   val edgecasesGenerationProbability: Double = PropertyTesting.edgecasesGenerationProbability
) {
   companion object;

   init {
      check(edgecasesGenerationProbability in 0.0..1.0) {
         "provided edgecasesProbability $edgecasesGenerationProbability is not between 0.0 and 1.0"
      }
   }
}
