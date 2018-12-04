(ns lab2.core-test
  "unit testcases for core"
  (:require [clojure.test :as test]
            [lab2.core :as core]))

(test/deftest core-main-test
  (test/testing "core main test"
    (test/is
     (nil?
      (core/-main)))))