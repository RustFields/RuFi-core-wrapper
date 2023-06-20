# RuFi-core-wrapper

This is the junction point between RuFi-core and ScaFi-core. The purpose of this project is to wrap the RuFi-core API and make them compatible with Scala.

## How it works

The project is composed by two subprojects: Native and Core.
The Native subproject (Rust + Cargo) makes the APIs exposed by RuFi-core compatible with C.
The Core subproject (Scala + SBT) takes advantage of Scala Native's interoperability with C and makes use of the native implementation provided in Rust and exposes the API in Scala.