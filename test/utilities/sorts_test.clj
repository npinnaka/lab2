(ns utilities.sorts-test
  "unit test cases for function in sorts namespace"
  (:require [utilities.sorts :as sorts]
            [clojure.test :as test]))

(test/deftest generic-sort-test
  (test/testing "test generic-sort function"
                (let [input (list
                             {:last-name      "Biles"
                              :first-name     "Simone"
                              :geneder        "F"
                              :favorite-color "Red"
                              :date-of-birth  "03/14/1997"
                              :name           "Simone Biles"}
                             {:last-name      "Manning"
                              :first-name     "Peyton"
                              :geneder        "M"
                              :favorite-color "Blue"
                              :date-of-birth  "06/14/1974"
                              :name           "Peyton Manning"})]
                  (test/is
                   (= (reverse input)
                      (sorts/generic-sort input :last-name :sort-order "desc"))
                   "last-name descending sort test failed"))
                (let [input (list
                             {:last-name      "Biles"
                              :first-name     "Simone"
                              :geneder        "F"
                              :favorite-color "Red"
                              :date-of-birth  "03/14/1997"
                              :name           "Simone Biles"}
                             {:last-name      "Manning"
                              :first-name     "Peyton"
                              :geneder        "M"
                              :favorite-color "Blue"
                              :date-of-birth  "06/14/1974"
                              :name           "Peyton Manning"})]
                  (test/is
                   (= input
                      (sorts/generic-sort input :last-name))
                   "last-name ascending sort test failed"))
                (let [input (list
                             {:last-name      "Biles"
                              :first-name     "Simone"
                              :geneder        "F"
                              :favorite-color "Red"
                              :date-of-birth  "03/14/1997"
                              :name           "Simone Biles"}
                             {:last-name      "Manning"
                              :first-name     "Peyton"
                              :geneder        "M"
                              :favorite-color "Blue"
                              :date-of-birth  "06/14/1974"
                              :name           "Peyton Manning"})]
                  (test/is
                   (= (reverse input)
                      (sorts/generic-sort input :date-of-birth :sort-order "date-asc"))
                   "date-of-birth ascending sort test failed"))
                (let [input (list
                             {:last-name      "Biles"
                              :first-name     "Simone"
                              :geneder        "F"
                              :favorite-color "Red"
                              :date-of-birth  "03/14/1997"
                              :name           "Simone Biles"}
                             {:last-name      "Manning"
                              :first-name     "Peyton"
                              :geneder        "M"
                              :favorite-color "Blue"
                              :date-of-birth  "06/14/1974"
                              :name           "Peyton Manning"})]
                  (test/is
                   (= input
                      (sorts/generic-sort input :date-of-birth :sort-order "date-desc"))
                   "date-of-birth decending sort test failed"))
                (let [input (list
                             {:last-name      "Biles"
                              :first-name     "Simone"
                              :geneder        "F"
                              :favorite-color "Red"
                              :date-of-birth  "03/14/1997"
                              :name           "Simone Biles"}
                             {:last-name      "Manning"
                              :first-name     "Peyton"
                              :geneder        "M"
                              :favorite-color "Blue"
                              :date-of-birth  "06/14/1974"
                              :name           "Peyton Manning"})]
                  (test/is
                   (= input
                      (sorts/generic-sort input :name :sort-order "desc"))
                   "name decending sort test failed"))
                (let [input (list
                             {:last-name      "Biles"
                              :first-name     "Simone"
                              :geneder        "F"
                              :favorite-color "Red"
                              :date-of-birth  "03/14/1997"
                              :name           "Simone Biles"}
                             {:last-name      "Manning"
                              :first-name     "Peyton"
                              :geneder        "M"
                              :favorite-color "Blue"
                              :date-of-birth  "06/14/1974"
                              :name           "Peyton Manning"})]
                  (test/is
                   (= (reverse input)
                      (sorts/generic-sort input :name))
                   "name ascending sort test failed"))))