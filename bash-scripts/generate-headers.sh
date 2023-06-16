#!/bin/bash

CUR_DIR=$(pwd)
cd native
cbindgen
cd $CUR_DIR