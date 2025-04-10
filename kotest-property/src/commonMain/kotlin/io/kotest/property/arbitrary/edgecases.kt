package io.kotest.property.arbitrary

import io.kotest.property.Arb
import io.kotest.property.EdgeConfig
import io.kotest.property.RandomSource
import io.kotest.property.Sample

/**
 * Randomly chooses an arb and then generates an edgecase from that arb.
 * If the chosen arb has no edgecases, then another arb will be chosen.
 * If all arbs have no edgecases, then returns null.
 */
tailrec fun <A> List<Arb<A>>.edgecase(rs: RandomSource): A? {
   if (this.isEmpty()) return null
   val shuffled = this.shuffled(rs.random)
   return when (val edge = shuffled.first().edgecase(rs)) {
      null -> this.drop(1).edgecase(rs)
      else -> edge
   }
}

/**
 * Collects the edgecases from this arb.
 * Will stop after the given number of iterations.
 * This function is mainly used for testing.
 */
fun <A> Arb<A>.edgecases(iterations: Int = 100, rs: RandomSource = RandomSource.default()): Set<A> =
   generate(rs, EdgeConfig(edgecasesGenerationProbability = 1.0))
      .take(iterations)
      .map { it.value }
      .toSet()

/**
 * Returns a new [Arb] with the supplied edgecases replacing any existing edgecases.
 */
fun <A> Arb<A>.withEdgecases(edgecases: List<A>): Arb<A> = arbitrary(edgecases) { this.next(it) }

/**
 * Returns a new [Arb] with the supplied edgecases replacing any existing edgecases.
 */
fun <A> Arb<A>.withEdgecases(vararg edgecases: A): Arb<A> = withEdgecases(edgecases.toList())

/**
 * Returns a new [Arb] with the edgecases from this arb transformed by the given function [f]
 */
fun <A> Arb<A>.modifyEdgecases(f: (A) -> A?): Arb<A> = object : Arb<A>() {
   override fun edgecase(rs: RandomSource): A? = this@modifyEdgecases.edgecase(rs)?.let(f)
   override fun sample(rs: RandomSource): Sample<A> = this@modifyEdgecases.sample(rs)
   override fun values(rs: RandomSource): Sequence<Sample<A>> = this@modifyEdgecases.values(rs)
}
