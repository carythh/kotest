package io.kotest.core.spec.style.scopes

import io.kotest.core.spec.resolvedDefaultConfig
import io.kotest.core.test.NestedTest
import io.kotest.core.test.TestCase
import io.kotest.core.test.TestContext
import io.kotest.core.test.TestType
import io.kotest.core.test.createNestedTest
import io.kotest.core.test.createTestName
import kotlin.coroutines.CoroutineContext

@Deprecated("This interface has been renamed to FunSpecContainerContext. This alias will be removed in 4.8")
typealias FunSpecContextScope = FunSpecContainerContext

/**
 * A context that allows tests to be registered using the syntax:
 *
 * context("some context")
 * test("some test")
 * test("some test").config(...)
 *
 */
class FunSpecContainerContext(
   private val testContext: TestContext,
) : ContainerContext {

   override val testCase: TestCase = testContext.testCase
   override val coroutineContext: CoroutineContext = testContext.coroutineContext
   override suspend fun registerTestCase(nested: NestedTest) = testContext.registerTestCase(nested)

   override suspend fun addTest(name: String, type: TestType, test: suspend TestContext.() -> Unit) {
      when (type) {
         TestType.Container -> context(name, test)
         TestType.Test -> test(name, test)
      }
   }

   /**
    * Adds a container test to this context.
    */
   suspend fun context(name: String, test: suspend FunSpecContainerContext.() -> Unit) {
      registerTestCase(
         createNestedTest(
            name = createTestName(name),
            xdisabled = false,
            config = testCase.spec.resolvedDefaultConfig(),
            type = TestType.Container,
            descriptor = null,
            factoryId = testCase.factoryId,
            test = { FunSpecContainerContext(this).test() }
         )
      )
   }

   /**
    * Adds a disabled container test to this context.
    */
   suspend fun xcontext(name: String, test: suspend FunSpecContainerContext.() -> Unit) {
      registerTestCase(
         createNestedTest(
            name = createTestName(name),
            xdisabled = true,
            config = testCase.spec.resolvedDefaultConfig(),
            type = TestType.Container,
            descriptor = null,
            factoryId = testCase.factoryId,
            test = { FunSpecContainerContext(this).test() }
         )
      )
   }

   /**
    * Adds a test case to this context, expecting config.
    */
   fun test(name: String) =
      TestWithConfigBuilder(createTestName(name), testContext, testCase.spec.resolvedDefaultConfig(), xdisabled = false)

   /**
    * Adds a disabled test case to this context, expecting config.
    */
   fun xtest(name: String) =
      TestWithConfigBuilder(createTestName(name), testContext, testCase.spec.resolvedDefaultConfig(), xdisabled = true)

   /**
    * Adds a test case to this context.
    */
   suspend fun test(name: String, test: suspend TestContext.() -> Unit) =
      registerTestCase(
         createNestedTest(
            name = createTestName(name),
            xdisabled = false,
            config = testCase.spec.resolvedDefaultConfig(),
            type = TestType.Test,
            descriptor = null,
            factoryId = testCase.factoryId,
            test = test,
         )
      )

   /**
    * Adds a disabled test case to this context.
    */
   suspend fun xtest(name: String, test: suspend TestContext.() -> Unit) =
      registerTestCase(
         createNestedTest(
            name = createTestName(name),
            xdisabled = true,
            config = testCase.spec.resolvedDefaultConfig(),
            type = TestType.Test,
            descriptor = null,
            factoryId = testCase.factoryId,
            test = test,
         )
      )
}
