(ns utilities.utils-test
  "unit test cases for utils"
  (:require [utilities.utils :as util]
            [clj-time.core :as time-core]
            [clojure.java.io :as io]
            [clojure.test :as test]))

(defn create-files
  []
  (with-open [wr (io/writer "test.edn")]
    (.write wr (pr-str {:a 1}))))

(defn clean-files []
  (io/delete-file "test.edn"))

(defn test-fixtures [f]
  (create-files)
  (f)
  (clean-files))

(test/deftest parse-string->date-test
  (test/testing "test parse-string->date function"
    (test/is
     (= (time-core/local-date 1990 01 01)
        (util/parse-string->date "01/01/1990"))
     "01/01/1990")
    (test/is
     (= (time-core/local-date 2016 2 29)
        (util/parse-string->date "02/29/2016"))
     "02/29/2016")))

(test/deftest load-edn-file-test
  (test/testing "test load-edn-file function"
    (test/is
     (= {:a 1}
        (util/load-edn "test.edn")))))

(test/use-fixtures :once test-fixtures)