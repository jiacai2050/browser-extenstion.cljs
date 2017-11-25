(ns hello-world.core-test
  (:require [cljs.test :refer-macros [deftest is testing]]
           [hello-world.background]
           [hello-world.content]
           [hello-world.option]))

(deftest test-demo
  (testing "I'm a test"
    (is (= 1 1))))
