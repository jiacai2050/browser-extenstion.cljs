#!/bin/bash

set -x
lein with-profile release do clean, cljsbuild once option background content && \
rm -rf resources/release/background/js/out resources/release/option/js/out resources/release/content/js/out && \
zip -r hello_world.zip resources/release/*
