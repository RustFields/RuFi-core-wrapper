package io.github.rustfields.rufi_core_wrapper

import io.github.rustfields.rufi_core_wrapper.TypeBinding.*
import scala.scalanative.unsafe
import scala.scalanative.unsafe.{CFuncPtr1, extern, link}

object TypeBinding:
  type CInt = unsafe.CInt
  type CFunIntToInt = unsafe.CFuncPtr1[CInt, CInt]

@link("rufi_core_wrapper")
@extern
object operations:
  def divide(a: CInt, b: CInt): CInt =
    extern

  def generic_operation(x: CInt, f: CFunIntToInt): CInt =
    extern

object Binding:
  def divide(a: CInt, b: CInt): CInt =
    operations.divide(a, b)

  def genericOperation(x: CInt, f: CFunIntToInt): CInt =
    operations.generic_operation(x, f)