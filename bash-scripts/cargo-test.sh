#!/bin/bash

CUR_DIR=$(pwd)
cd native
cargo test
cd $CUR_DIR