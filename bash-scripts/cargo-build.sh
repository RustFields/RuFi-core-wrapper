#!/bin/bash

CUR_DIR=$(pwd)
cd native
cargo build
cargo build --release
cd $CUR_DIR