package io.kotest.core.spec.style.scopes

import io.kotest.core.Tag
import io.kotest.core.extensions.TestCaseExtension
import io.kotest.core.spec.KotestDsl
import io.kotest.core.test.EnabledIf
import io.kotest.core.test.TestCaseSeverityLevel
import io.kotest.core.test.TestContext
import io.kotest.core.test.createTestName
import io.kotest.core.test.deriveTestConfig
import kotlin.time.Duration

@Deprecated("Renamed to FreeSpecRootContext. This typealias will be removed in 4.8")
typealias FreeSpecRootScope = FreeSpecRootContext

@KotestDsl
interface FreeSpecRootContext : RootContext {

   // eg, "this test" - { } // adds a container test
   infix operator fun String.minus(test: suspend FreeSpecContainerContext.() -> Unit) {
      val name = createTestName(this)
      registration().addContainerTest(name, xdisabled = false) {
         FreeSpecContainerContext(this).test()
      }
   }

   // "this test" { } // adds a leaf test
   infix operator fun String.invoke(test: suspend TestContext.() -> Unit) {
      registration().addTest(createTestName(this), xdisabled = false, test = test)
   }

   // adds a leaf test with config
   fun String.config(
      enabled: Boolean? = null,
      invocations: Int? = null,
      threads: Int? = null,
      tags: Set<Tag>? = null,
      timeout: Duration? = null,
      extensions: List<TestCaseExtension>? = null,
      enabledIf: EnabledIf? = null,
      invocationTimeout: Duration? = null,
      severity: TestCaseSeverityLevel? = null,
      test: suspend TestContext.() -> Unit
   ) {
      val config = defaultConfig().deriveTestConfig(
         enabled,
         tags,
         extensions,
         timeout,
         invocationTimeout,
         enabledIf,
         invocations,
         threads,
         severity
      )
      registration().addTest(createTestName(this), false, config, test)
   }
}

