(ns lab2.controller-test
  "unit test cases for service name sapce"
  (:require [lab2.controller :as ctrl]
            [clojure.test :as test]
            [clojure.data.json :as json]))

(def input-data "Black|Steve|M|Black|10/19/1956")
(def bad-input-data "Steve|M|Black|10/19/1956")


(def output-data
  '({:last-name      "Black",
     :first-name     "Steve",
     :gender         "M",
     :favorite-color "Black",
     :date-of-birth  "10/19/1956",
     :name           "Steve Black"}))

(def output-data-2
  [{:last-name      "Johnson",
    :first-name     "Abigail",
    :gender         "F",
    :favorite-color "Green",
    :date-of-birth  "12/19/1961",
    :name           "Abigail Johnson"}
   {:last-name      "Black",
    :first-name     "Steve",
    :gender         "M",
    :favorite-color "Black",
    :date-of-birth  "10/19/1956",
    :name           "Steve Black"}
   {:last-name      "Jobs",
    :first-name     "Steve",
    :gender         "M",
    :favorite-color "Black",
    :date-of-birth  "10/19/1964",
    :name           "Steve Jobs"}])

(test/deftest post-record-test
  (test/testing "test post-record function"
                (test/is (= output-data (ctrl/post-record input-data)))
                (test/is (= output-data @ctrl/data))
                (test/is (= output-data (ctrl/view-all)))
                (test/is (= (json/write-str (reverse output-data)) (ctrl/sort-by-birth-date)))
                (test/is (= (json/write-str output-data) (ctrl/sort-by-name)))
                (test/is (= (json/write-str output-data) (ctrl/sort-by-gender)))))
