package io.github.rustfields.rufi_core_wrapper

import io.github.rustfields.rufi_core_wrapper.Binding.*
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers.shouldBe

class TestBinding extends AnyFunSuite:

  test("20 / 2 should be 10") {
    divide(20, 2) shouldBe 10
  }

  test("43 + 1 should be 44") {
    genericOperation(43, (x: Int) => x + 1) shouldBe 44
  }
