package com.sksamuel.kotest.property.arbitrary

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.collections.shouldHaveAtLeastSize
import io.kotest.matchers.comparables.beGreaterThan
import io.kotest.matchers.comparables.beLessThan
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.property.Arb
import io.kotest.property.EdgeConfig
import io.kotest.property.RandomSource
import io.kotest.property.arbitrary.bind
import io.kotest.property.arbitrary.bool
import io.kotest.property.arbitrary.double
import io.kotest.property.arbitrary.filter
import io.kotest.property.arbitrary.map
import io.kotest.property.arbitrary.negativeInts
import io.kotest.property.arbitrary.positiveInts
import io.kotest.property.arbitrary.string
import io.kotest.property.arbitrary.take
import io.kotest.property.arbitrary.withEdgecases
import io.kotest.property.checkAll
import io.kotest.matchers.doubles.beGreaterThan as gtd

class BindTest : StringSpec({

   data class FooA(val a: String)
   data class User(val email: String, val id: Int)
   data class FooC(val a: String, val b: Int, val c: Double)
   data class FooD(val a: String, val b: Int, val c: Double, val d: Int)
   data class FooE(val a: String, val b: Int, val c: Double, val d: Int, val e: Boolean)

   "Arb.bindA" {
      val gen = Arb.string().map { FooA(it) }
      checkAll(gen) {
         it.a shouldNotBe null
      }
   }

   "Arb.bind(a,b) should generate distinct values" {
      val arbA = Arb.string()
      val arbB = Arb.string()
      Arb.bind(arbA, arbB) { a, b -> a + b }.take(1000).toSet().shouldHaveAtLeastSize(100)
   }

   "Arb.bindB" {
      val gen = Arb.bind(Arb.string(), Arb.positiveInts(), ::User)
      checkAll(gen) {
         it.email shouldNotBe null
         it.id should beGreaterThan(0)
      }
   }

   "Arb.bindC" {
      val gen = Arb.bind(Arb.string(), Arb.positiveInts(), Arb.double().filter { it > 0 }, ::FooC)
      checkAll(gen) {
         it.a shouldNotBe null
         it.b should beGreaterThan(0)
         it.c should gtd(0.0)
      }
   }

   "Arb.bind(a,b,c) should generate distinct values" {
      val arbA = Arb.string()
      val arbB = Arb.string()
      val arbC = Arb.string()
      Arb.bind(arbA, arbB, arbC) { a, b, c -> "$a$b$c" }.take(1000).toSet().shouldHaveAtLeastSize(100)
   }

   "Arb.bindD" {
      val gen =
         Arb.bind(Arb.string(), Arb.positiveInts(), Arb.double().filter { it > 0 }, Arb.negativeInts(), ::FooD)
      checkAll(gen) {
         it.a shouldNotBe null
         it.b should beGreaterThan(0)
         it.c should gtd(0.0)
         it.d should beLessThan(0)
      }
   }

   "Arb.bind(a,b,c,d) should generate distinct values" {
      val arbA = Arb.string()
      val arbB = Arb.string()
      val arbC = Arb.string()
      val arbD = Arb.string()
      Arb.bind(arbA, arbB, arbC, arbD) { a, b, c, d -> "$a$b$c$d" }.take(1000).toSet().shouldHaveAtLeastSize(100)
   }

   "Arb.bindE" {
      val gen = Arb.bind(
         Arb.string(),
         Arb.positiveInts(),
         Arb.double().filter { it > 0 },
         Arb.negativeInts(),
         Arb.bool(),
         ::FooE
      )
      checkAll(gen) {
         it.a shouldNotBe null
         it.b should beGreaterThan(0)
         it.c should gtd(0.0)
         it.d should beLessThan(0)
      }
   }

   "Arb.bind(a,b,c,d,e) should generate distinct values" {
      val arbA = Arb.string()
      val arbB = Arb.string()
      val arbC = Arb.string()
      val arbD = Arb.string()
      val arbE = Arb.string()
      Arb.bind(arbA, arbB, arbC, arbD, arbE) { a, b, c, d, e -> "$a$b$c$d$e" }.take(1000).toSet()
         .shouldHaveAtLeastSize(100)
   }


   "Arb.bind(a,b,c,d,e,f) should generate distinct values" {
      val arbA = Arb.string()
      val arbB = Arb.string()
      val arbC = Arb.string()
      val arbD = Arb.string()
      val arbE = Arb.string()
      val arbF = Arb.string()
      Arb.bind(arbA, arbB, arbC, arbD, arbE, arbF) { a, b, c, d, e, f -> "$a$b$c$d$e$f" }.take(1000).toSet()
         .shouldHaveAtLeastSize(100)
   }

   "Arb.bind(a,b,c,d,e,f,g) should generate distinct values" {
      val arbA = Arb.string()
      val arbB = Arb.string()
      val arbC = Arb.string()
      val arbD = Arb.string()
      val arbE = Arb.string()
      val arbF = Arb.string()
      val arbG = Arb.string()
      Arb.bind(arbA, arbB, arbC, arbD, arbE, arbF, arbG) { a, b, c, d, e, f, g -> "$a$b$c$d$e$f$g" }.take(1000).toSet()
         .shouldHaveAtLeastSize(100)
   }

   "Arb.bind(a,b,c,d,e,f,g,h) should generate distinct values" {
      val arbA = Arb.string()
      val arbB = Arb.string()
      val arbC = Arb.string()
      val arbD = Arb.string()
      val arbE = Arb.string()
      val arbF = Arb.string()
      val arbG = Arb.string()
      val arbH = Arb.string()
      Arb.bind(arbA, arbB, arbC, arbD, arbE, arbF, arbG, arbH) { a, b, c, d, e, f, g, h -> "$a$b$c$d$e$f$g$h" }
         .take(1000).toSet().shouldHaveAtLeastSize(100)
   }

   "Arb.bind(a,b,c,d,e,f,g,h,i) should generate distinct values" {
      val arbA = Arb.string()
      val arbB = Arb.string()
      val arbC = Arb.string()
      val arbD = Arb.string()
      val arbE = Arb.string()
      val arbF = Arb.string()
      val arbG = Arb.string()
      val arbH = Arb.string()
      val arbI = Arb.string()
      Arb.bind(arbA, arbB, arbC, arbD, arbE, arbF, arbG, arbH, arbI) { a, b, c, d, e, f, g, h, i ->
         "$a$b$c$d$e$f$g$h$i"
      }.take(1000).toSet().shouldHaveAtLeastSize(100)
   }

   "Arb.bind(a,b,c,d,e,f,g,h,i,j) should generate distinct values" {
      val arbA = Arb.string()
      val arbB = Arb.string()
      val arbC = Arb.string()
      val arbD = Arb.string()
      val arbE = Arb.string()
      val arbF = Arb.string()
      val arbG = Arb.string()
      val arbH = Arb.string()
      val arbI = Arb.string()
      val arbJ = Arb.string()
      Arb.bind(arbA, arbB, arbC, arbD, arbE, arbF, arbG, arbH, arbI, arbJ) { a, b, c, d, e, f, g, h, i, j ->
         "$a$b$c$d$e$f$g$h$i$j"
      }.take(1000).toSet().shouldHaveAtLeastSize(100)
   }

   "Arb.bind(a,b) should compute the probabilistic edgecases" {
      val arbA = Arb.string(1).withEdgecases("a")
      val arbB = Arb.string(1).withEdgecases("a", "b")
      val arb = Arb.bind(arbA, arbB) { a, b -> a + b }
      val edgecases = arb
         .generate(RandomSource.seeded(1234L), EdgeConfig(edgecasesGenerationProbability = 1.0))
         .take(5)
         .map { it.value }
         .toList()
      edgecases shouldContainExactly listOf(
         "ab",
         "aa",
         "ab",
         "aa",
         "aa"
      )
   }

   "Arb.bind(a,b,c) should compute probabilistic edgecases" {
      val arbA = Arb.string(1).withEdgecases("a")
      val arbB = Arb.string(1).withEdgecases("a", "b")
      val arbC = Arb.string(1).withEdgecases(emptyList())
      val arb = Arb.bind(arbA, arbB, arbC) { a, b, c -> a + b + c }
      val edgecases = arb
         .generate(RandomSource.seeded(1234L), EdgeConfig(edgecasesGenerationProbability = 1.0))
         .take(5)
         .map { it.value }
         .toList()
      edgecases shouldContainExactly listOf(
         "abK",
         "abz",
         "aaw",
         "ab/",
         "ab;"
      )
   }

   "Arb.bind(a,b,c,d) should compute probabilistic edgecases" {
      val arbA = Arb.string(1).withEdgecases("a")
      val arbB = Arb.string(1).withEdgecases("a", "b")
      val arbC = Arb.string(1).withEdgecases("a", "b")
      val arbD = Arb.string(1).withEdgecases(emptyList())
      val arb = Arb.bind(arbA, arbB, arbC, arbD) { a, b, c, d -> "$a$b$c$d" }
      val edgecases = arb
         .generate(RandomSource.seeded(1234L), EdgeConfig(edgecasesGenerationProbability = 1.0))
         .take(5)
         .map { it.value }
         .toList()
      edgecases shouldContainExactly listOf(
         "aaa#",
         "aba-",
         "aaaS",
         "abb;",
         "aaa\\"
      )
   }

   "Arb.bind(a,b,c,d,e) should compute probabilistic edgecases" {
      val arbA = Arb.string(1).withEdgecases("a")
      val arbB = Arb.string(1).withEdgecases("a", "b")
      val arbC = Arb.string(1).withEdgecases(emptyList())
      val arbD = Arb.string(1).withEdgecases("a", "b")
      val arbE = Arb.string(1).withEdgecases("a", "b")
      val arb = Arb.bind(arbA, arbB, arbC, arbD, arbE) { a, b, c, d, e -> "$a$b$c$d$e" }
      val edgecases = arb
         .generate(RandomSource.seeded(1234L), EdgeConfig(edgecasesGenerationProbability = 1.0))
         .take(5)
         .map { it.value }
         .toList()
      edgecases shouldContainExactly listOf(
         "aaGaa",
         "aawba",
         "absbb",
         "abLab",
         "ab`ba"
      )
   }


   "Arb.bind(a,b,c,d,e,f) should compute probabilistic edgecases" {
      val arbA = Arb.string(1).withEdgecases("a", "b")
      val arbB = Arb.string(1).withEdgecases("a", "b")
      val arbC = Arb.string(1).withEdgecases(emptyList())
      val arbD = Arb.string(1).withEdgecases("a", "b")
      val arbE = Arb.string(1).withEdgecases("a", "b")
      val arbF = Arb.string(1).withEdgecases(emptyList())
      val arb = Arb.bind(arbA, arbB, arbC, arbD, arbE, arbF) { a, b, c, d, e, f -> "$a$b$c$d$e$f" }
      val edgecases = arb
         .generate(RandomSource.seeded(1234L), EdgeConfig(edgecasesGenerationProbability = 1.0))
         .take(5)
         .map { it.value }
         .toList()
      edgecases shouldContainExactly listOf(
         "abzba-",
         "badabs",
         "ba`aae",
         "bb`baj",
         "ab abB"
      )
   }


   "Arb.bind(a,b,c,d,e,f,g) should compute probabilistic edgecases" {
      val arbA = Arb.string(1).withEdgecases(emptyList())
      val arbB = Arb.string(1).withEdgecases("a", "b")
      val arbC = Arb.string(1).withEdgecases("a")
      val arbD = Arb.string(1).withEdgecases("a", "b")
      val arbE = Arb.string(1).withEdgecases("a", "b")
      val arbF = Arb.string(1).withEdgecases(emptyList())
      val arbG = Arb.string(1).withEdgecases("a", "b")
      val arb = Arb.bind(arbA, arbB, arbC, arbD, arbE, arbF, arbG) { a, b, c, d, e, f, g -> "$a$b$c$d$e$f$g" }
      val edgecases = arb
         .generate(RandomSource.seeded(1234L), EdgeConfig(edgecasesGenerationProbability = 1.0))
         .take(5)
         .map { it.value }
         .toList()
      edgecases shouldContainExactly listOf(
         "zbabawb",
         "Sbaabba",
         "Laabbia",
         "=aabaEb",
         "Baaaa*a"
      )
   }


   "Arb.bind(a,b,c,d,e,f,g,h) should compute probabilistic edgecases" {
      val arbA = Arb.string(1).withEdgecases("a", "b")
      val arbB = Arb.string(1).withEdgecases("a")
      val arbC = Arb.string(1).withEdgecases("a", "b")
      val arbD = Arb.string(1).withEdgecases("a", "b")
      val arbE = Arb.string(1).withEdgecases(emptyList())
      val arbF = Arb.string(1).withEdgecases("a", "b")
      val arbG = Arb.string(1).withEdgecases("a", "b")
      val arbH = Arb.string(1).withEdgecases(emptyList())
      val arb =
         Arb.bind(arbA, arbB, arbC, arbD, arbE, arbF, arbG, arbH) { a, b, c, d, e, f, g, h -> "$a$b$c$d$e$f$g$h" }
      val edgecases = arb
         .generate(RandomSource.seeded(1234L), EdgeConfig(edgecasesGenerationProbability = 1.0))
         .take(5)
         .map { it.value }
         .toList()
      edgecases shouldContainExactly listOf(
         "babawbad",
         "aabb;bbL",
         "babb`baj",
         "aabb'abh",
         "aaab bb7"
      )
   }

   "Arb.bind(a,b,c,d,e,f,g,h,i) should compute probabilistic edgecases" {
      val arbA = Arb.string(1).withEdgecases("a", "b")
      val arbB = Arb.string(1).withEdgecases(emptyList())
      val arbC = Arb.string(1).withEdgecases("a")
      val arbD = Arb.string(1).withEdgecases("a", "b")
      val arbE = Arb.string(1).withEdgecases("a", "b")
      val arbF = Arb.string(1).withEdgecases(emptyList())
      val arbG = Arb.string(1).withEdgecases("a", "b")
      val arbH = Arb.string(1).withEdgecases("a", "b")
      val arbI = Arb.string(1).withEdgecases("a", "b")
      val arb = Arb.bind(
         arbA,
         arbB,
         arbC,
         arbD,
         arbE,
         arbF,
         arbG,
         arbH,
         arbI
      ) { a, b, c, d, e, f, g, h, i -> "$a$b$c$d$e$f$g$h$i" }
      val edgecases = arb
         .generate(RandomSource.seeded(1234L), EdgeConfig(edgecasesGenerationProbability = 1.0))
         .take(5)
         .map { it.value }
         .toList()
      edgecases shouldContainExactly listOf(
         "b8abadaba",
         "b;abaBbbb",
         "aAabaBabb",
         "bBaaaubab",
         "b\\aaa0aaa"
      )
   }

   "Arb.bind(a,b,c,d,e,f,g,h,i,j) should compute probabilistic edgecases" {
      val arbA = Arb.string(1).withEdgecases("a", "b")
      val arbB = Arb.string(1).withEdgecases(emptyList())
      val arbC = Arb.string(1).withEdgecases("a", "b")
      val arbD = Arb.string(1).withEdgecases("a", "b")
      val arbE = Arb.string(1).withEdgecases("a")
      val arbF = Arb.string(1).withEdgecases("a", "b")
      val arbG = Arb.string(1).withEdgecases("a", "b")
      val arbH = Arb.string(1).withEdgecases(emptyList())
      val arbI = Arb.string(1).withEdgecases("a", "b")
      val arbJ = Arb.string(1).withEdgecases("a", "b")
      val arb = Arb.bind(arbA, arbB, arbC, arbD, arbE, arbF, arbG, arbH, arbI, arbJ) { a, b, c, d, e, f, g, h, i, j ->
         "$a$b$c$d$e$f$g$h$i$j"
      }
      val edgecases = arb
         .generate(RandomSource.seeded(1234L), EdgeConfig(edgecasesGenerationProbability = 1.0))
         .take(5)
         .map { it.value }
         .toList()
      edgecases shouldContainExactly listOf(
         "beaaaabsbb",
         "byaaabbiab",
         "bYbaabb'ab",
         "aubaabaOab",
         "b=aaaabqab"
      )
   }

   "Arb.bind(a,b,c,d,e,f,g,h,i,j,k) should compute probabilistic edgecases" {
      val arbA = Arb.string(1).withEdgecases("a", "b")
      val arbB = Arb.string(1).withEdgecases("a")
      val arbC = Arb.string(1).withEdgecases("a", "b")
      val arbD = Arb.string(1).withEdgecases("a", "b")
      val arbE = Arb.string(1).withEdgecases(emptyList())
      val arbF = Arb.string(1).withEdgecases("a", "b")
      val arbG = Arb.string(1).withEdgecases("a", "b")
      val arbH = Arb.string(1).withEdgecases("a", "b")
      val arbI = Arb.string(1).withEdgecases("a", "b")
      val arbJ = Arb.string(1).withEdgecases(emptyList())
      val arbK = Arb.string(1).withEdgecases("a", "b")
      val arb =
         Arb.bind(arbA, arbB, arbC, arbD, arbE, arbF, arbG, arbH, arbI, arbJ, arbK) { a, b, c, d, e, f, g, h, i, j, k ->
            "$a$b$c$d$e$f$g$h$i$j$k"
         }
      val edgecases = arb
         .generate(RandomSource.seeded(1234L), EdgeConfig(edgecasesGenerationProbability = 1.0))
         .take(5)
         .map { it.value }
         .toList()
      edgecases shouldContainExactly listOf(
         "baab/aabb;b",
         "aabbQbabb=a",
         "aabb'abaa0a",
         "baab\\baab=a",
         "aaaavbaab:b"
      )
   }

   "Arb.bind(a,b,c,d,e,f,g,h,i,j,k,l) should compute probabilistic edgecases" {
      val arbA = Arb.string(1).withEdgecases("a", "b")
      val arbB = Arb.string(1).withEdgecases("a", "b")
      val arbC = Arb.string(1).withEdgecases(emptyList())
      val arbD = Arb.string(1).withEdgecases("a", "b")
      val arbE = Arb.string(1).withEdgecases("a", "b")
      val arbF = Arb.string(1).withEdgecases("a", "b")
      val arbG = Arb.string(1).withEdgecases("a", "b")
      val arbH = Arb.string(1).withEdgecases("a", "b")
      val arbI = Arb.string(1).withEdgecases("a", "b")
      val arbJ = Arb.string(1).withEdgecases("a")
      val arbK = Arb.string(1).withEdgecases("a", "b")
      val arbL = Arb.string(1).withEdgecases(emptyList())
      val arb = Arb.bind(
         arbA,
         arbB,
         arbC,
         arbD,
         arbE,
         arbF,
         arbG,
         arbH,
         arbI,
         arbJ,
         arbK,
         arbL
      ) { a, b, c, d, e, f, g, h, i, j, k, l ->
         "$a$b$c$d$e$f$g$h$i$j$k$l"
      }

      val edgecases = arb
         .generate(RandomSource.seeded(1234L), EdgeConfig(edgecasesGenerationProbability = 1.0))
         .take(5)
         .map { it.value }
         .toList()
      edgecases shouldContainExactly listOf(
         "ab/aabbbaabL",
         "bbiabbabaabP",
         "abBaaaaabab ",
         "ab?baaaababq",
         "ba0babaaaaa "
      )
   }

   "Arb.bind(a,b,c,d,e,f,g,h,i,j,k,l,m) should compute probabilistic edgecases" {
      val arbA = Arb.string(1).withEdgecases(emptyList())
      val arbB = Arb.string(1).withEdgecases("a", "b")
      val arbC = Arb.string(1).withEdgecases("a", "b")
      val arbD = Arb.string(1).withEdgecases("a")
      val arbE = Arb.string(1).withEdgecases("a", "b")
      val arbF = Arb.string(1).withEdgecases("a", "b")
      val arbG = Arb.string(1).withEdgecases(emptyList())
      val arbH = Arb.string(1).withEdgecases("a", "b")
      val arbI = Arb.string(1).withEdgecases("a", "b")
      val arbJ = Arb.string(1).withEdgecases("a")
      val arbK = Arb.string(1).withEdgecases("a", "b")
      val arbL = Arb.string(1).withEdgecases("a", "b")
      val arbM = Arb.string(1).withEdgecases(emptyList())
      val arb = Arb.bind(
         arbA,
         arbB,
         arbC,
         arbD,
         arbE,
         arbF,
         arbG,
         arbH,
         arbI,
         arbJ,
         arbK,
         arbL,
         arbM
      ) { a, b, c, d, e, f, g, h, i, j, k, l, m ->
         "$a$b$c$d$e$f$g$h$i$j$k$l$m"
      }

      val edgecases = arb
         .generate(RandomSource.seeded(1234L), EdgeConfig(edgecasesGenerationProbability = 1.0))
         .take(5)
         .map { it.value }
         .toList()
      edgecases shouldContainExactly listOf(
         "/aaabb:baaabs",
         "`baaabmbbaabB",
         "0ababbbbaaaa0",
         "Gabaaa]aaaba^",
         "6aaaaapbbaaak"
      )
   }

   "Arb.bind(a,b,c,d,e,f,g,h,i,j,k,l,m,n) should compute probabilistic edgecases" {
      val arbA = Arb.string(1).withEdgecases("a")
      val arbB = Arb.string(1).withEdgecases("a", "b")
      val arbC = Arb.string(1).withEdgecases("a", "b")
      val arbD = Arb.string(1).withEdgecases("a", "b")
      val arbE = Arb.string(1).withEdgecases("a", "b")
      val arbF = Arb.string(1).withEdgecases("a", "b")
      val arbG = Arb.string(1).withEdgecases("a", "b")
      val arbH = Arb.string(1).withEdgecases(emptyList())
      val arbI = Arb.string(1).withEdgecases("a", "b")
      val arbJ = Arb.string(1).withEdgecases("a", "b")
      val arbK = Arb.string(1).withEdgecases("a", "b")
      val arbL = Arb.string(1).withEdgecases("a", "b")
      val arbM = Arb.string(1).withEdgecases("a", "b")
      val arbN = Arb.string(1).withEdgecases(emptyList())
      val arb = Arb.bind(
         arbA,
         arbB,
         arbC,
         arbD,
         arbE,
         arbF,
         arbG,
         arbH,
         arbI,
         arbJ,
         arbK,
         arbL,
         arbM,
         arbN
      ) { a, b, c, d, e, f, g, h, i, j, k, l, m, n ->
         "$a$b$c$d$e$f$g$h$i$j$k$l$m$n"
      }
      val edgecases = arb
         .generate(RandomSource.seeded(1234L), EdgeConfig(edgecasesGenerationProbability = 1.0))
         .take(5)
         .map { it.value }
         .toList()
      edgecases shouldContainExactly listOf(
         "aabbbabyaabbbi",
         "aababbaEbababh",
         "ababbab\\baabaJ",
         "abaaabbIbbaba<",
         "aabaabawaaabaj"
      )
   }

   "Arb.bind list" {
      val arbs: List<Arb<String>> = generateSequence { Arb.string(1).withEdgecases("a") }.take(100).toList()
      val arb: Arb<String> = Arb.bind(arbs) { it.joinToString("") }

      val edgecases = arb
         .generate(RandomSource.seeded(1234L), EdgeConfig(edgecasesGenerationProbability = 1.0))
         .take(5)
         .map { it.value }
         .toList()

      edgecases shouldContainExactly listOf(
         "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
         "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
         "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
         "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
         "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
      )
   }

   "Arb.reflectiveBind" {
      val arb = Arb.bind<Wobble>()
      arb.take(10).toList().size shouldBe 10
   }

   "Arb.reflectiveBind should generate probabilistic edgecases" {
      val arb = Arb.bind<Wobble>()
      val edgecases = arb
         .generate(RandomSource.seeded(1234L), EdgeConfig(edgecasesGenerationProbability = 1.0))
         .take(5)
         .map { it.value }
         .toList()

      edgecases shouldContainExactly listOf(
         Wobble(a = "", b = true, c = -2147483648, d = -4.9E-324, e = Float.NEGATIVE_INFINITY),
         Wobble(a = "", b = false, c = 0, d = 1.7976931348623157E308, e = Float.NEGATIVE_INFINITY),
         Wobble(a = "", b = true, c = 1, d = 1.0, e = Float.NEGATIVE_INFINITY),
         Wobble(a = "", b = true, c = 0, d = -1.7976931348623157E308, e = 0.0F),
         Wobble(a = "", b = false, c = 1, d = -1.0E300, e = 1.4E-45f)
      )
   }
})

data class Wobble(val a: String, val b: Boolean, val c: Int, val d: Double, val e: Float)
