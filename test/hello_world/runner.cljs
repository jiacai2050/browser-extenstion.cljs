(ns hello-world.runner
  (:require [doo.runner :refer-macros [doo-tests]]
            [hello-world.core-test]))

(doo-tests 'hello-world.core-test)
