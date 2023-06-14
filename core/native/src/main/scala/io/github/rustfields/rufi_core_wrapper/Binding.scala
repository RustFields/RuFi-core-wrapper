package io.github.rustfields.rufi_core_wrapper

import io.github.rustfields.rufi_core_wrapper.TypeBinding.*
import scala.scalanative.unsafe
import scala.scalanative.unsafe.{CFuncPtr1, extern}

object TypeBinding:
  type CInt = unsafe.CInt
  type CFunIntToInt = unsafe.CFuncPtr1[CInt, CInt]

@extern
object Native:
  def divide(a: CInt, b: CInt): CInt =
    extern

  def generic_operation(x: CInt, f: CFunIntToInt): CInt =
    extern

object Binding:
  def divide(a: CInt, b: CInt): CInt =
    Native.divide(a, b)

  def genericOperation(x: CInt, f: CFunIntToInt): CInt =
    Native.generic_operation(x, f)