(ns lab2.file-processor-test
  "unit test cases for file processing functions"
  (:require [clojure.test :as test]
            [lab2.file-processor :as fp]))

(def data-out ["Doe,John,M,Red,01/01/1990"
             "Jobs,Steve,M,Black,10/19/1964"
             "Johnson,Abigail,F,Green,12/19/1961"])

(def out-map '({:last-name      "Doe",
  :first-name     "John",
  :gender         "M",
  :favorite-color "Red",
  :date-of-birth  "01/01/1990"}
  {:last-name      "Jobs",
   :first-name     "Steve",
   :gender         "M",
   :favorite-color "Black",
   :date-of-birth  "10/19/1964"}
  {:last-name      "Johnson",
   :first-name     "Abigail",
   :gender         "F",
   :favorite-color "Green",
   :date-of-birth  "12/19/1961"}))

(test/deftest create-map-test
  (test/testing "test create-map function"
                (test/is
                 (=
                  {:last-name      "Downs"
                   :first-name     "Robert"
                   :gender         "M"
                   :favorite-color "Green"
                   :date-of-birth  "12/31/1978"}
                  (fp/create-map ["Downs" "Robert" "M" "Green" "12/31/1978"]))
                 "test faied for valid data vector")
                (test/is (thrown? RuntimeException (fp/create-map nil)) "tet failed for data nil")
                (test/is (thrown? RuntimeException (fp/create-map {})) "test failed for data {}")
                (test/is
                 (thrown? RuntimeException
                          (fp/create-map
                           {:last-name      "Downs"
                            :first-name     "Robert"
                            :gender         "M"
                            :favorite-color "Green"})))))

(test/deftest split-row-and-create-map-test
  (test/testing "test split-row-and-create-map  function"
                (test/is
                 (=
                  {:last-name      "Downs"
                   :first-name     "Robert"
                   :gender         "M"
                   :favorite-color "Green"
                   :date-of-birth  "12/31/1978"}
                  (fp/split-row-and-create-map "Downs,Robert,M,Green,12/31/1978"))
                 "test failed for comma delimiter string")
                (test/is
                 (=
                  {:last-name      "Downs"
                   :first-name     "Robert"
                   :gender         "M"
                   :favorite-color "Green"
                   :date-of-birth  "12/31/1978"}
                  (fp/split-row-and-create-map "Downs|Robert|M|Green|12/31/1978"))
                 "test failed for pipe delimiter string")
                (test/is
                 (=
                  {:last-name      "Downs"
                   :first-name     "Robert"
                   :gender         "M"
                   :favorite-color "Green"
                   :date-of-birth  "12/31/1978"}
                  (fp/split-row-and-create-map "Downs Robert M Green 12/31/1978")))
                (test/is (thrown? RuntimeException (fp/split-row-and-create-map nil)) "test failed for data nil")
                (test/is (thrown? RuntimeException (fp/split-row-and-create-map {})) "test failed for data {}")
                (test/is
                 (thrown? RuntimeException
                          (fp/split-row-and-create-map
                           {:last-name      "Downs"
                            :first-name     "Robert"
                            :gender         "M"
                            :favorite-color "Green"})))))

(test/deftest load-delimiter-file-test
  (test/testing "test load-delimiter-file  function"
                (test/is
                 (=
                  data-out
                  (fp/load-delimiter-file "resources/csv-data.csv"))
                 "test failed loading csv-data.csv")
                (test/is
                 (thrown? java.io.FileNotFoundException (fp/load-delimiter-file "resources/xyz.csv"))
                 "test failed to load resources/xyz.csv")))


(test/deftest get-validated-data-test
  (test/testing "test get-validated-data function"
                (test/is (= out-map (fp/get-validated-data data-out)))))

(test/deftest prepare-data-test
  (test/testing "test prepare-data function"
                (let [data-out '({:last-name      "Doe",
                               :first-name     "John",
                               :gender         "M",
                               :favorite-color "Red",
                               :date-of-birth  "01/01/1990"}
                              {:last-name      "Jobs",
                               :first-name     "Steve",
                               :gender         "M",
                               :favorite-color "Black",
                               :date-of-birth  "10/19/1964"}
                              {:last-name      "Johnson",
                               :first-name     "Abigail",
                               :gender         "F",
                               :favorite-color "Green",
                               :date-of-birth  "12/19/1961"}
                              {:last-name      "Page",
                               :first-name     "Larry",
                               :gender         "M",
                               :favorite-color "Blue",
                               :date-of-birth  "08/01/1970"}
                              {:last-name      "Brin",
                               :first-name     "Sergey",
                               :gender         "M",
                               :favorite-color "Green",
                               :date-of-birth  "10/29/1964"}
                              {:last-name      "Jung",
                               :first-name     "Andrea",
                               :gender         "F",
                               :favorite-color "Silver",
                               :date-of-birth  "10/29/1954"}
                              {:last-name      "Nooyi",
                               :first-name     "Indra",
                               :gender         "F",
                               :favorite-color "Blue",
                               :date-of-birth  "08/06/1959"}
                              {:last-name      "Warrior",
                               :first-name     "Padmasree",
                               :gender         "F",
                               :favorite-color "White",
                               :date-of-birth  "02/18/1950"})]
                  (test/is
                   (=
                    data-out
                    (fp/prepare-data "resources/csv-data.csv" "resources/pipe-data.csv" "resources/space-data.csv"))
                   "failed to load delimited data file"))
                (test/is
                 (thrown? RuntimeException
                          (fp/prepare-data "resources/invalid-spec-data-4.csv" "resources/pipe-data.csv" "space-data.csv"))
                 "failed @ 4 columns file")
                (test/is
                 (thrown? RuntimeException
                          (fp/prepare-data "resources/invalid-spec-data-5.csv" "resources/pipe-data.csv" "space-data.csv"))
                 "failed at 5 columns file")))