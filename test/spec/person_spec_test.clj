(ns spec.person-spec-test
  (:require [spec.person-spec :as ps]
            [clojure.spec.alpha :as spec]
            [clojure.test :as test]
            [clj-time.core :as time-core]))

(test/deftest last-name-spec-test
  (test/testing "test last name spec validation"
    (test/is (spec/valid? ::ps/last-name "Brady"))
    (test/is (false? (spec/valid? ::ps/last-name "   ")))
    (test/is (false? (spec/valid? ::ps/last-name "")))
    (test/is (false? (spec/valid? ::ps/last-name nil)))))

(test/deftest first-name-spec-test
  (test/testing "test first name spec validation"
    (test/is (spec/valid? ::ps/first-name "Tom"))
    (test/is (false? (spec/valid? ::ps/first-name "   ")))
    (test/is (false? (spec/valid? ::ps/first-name "")))
    (test/is (false? (spec/valid? ::ps/first-name nil)))))

(test/deftest first-name-spec-test
  (test/testing "test first name spec validation"
    (test/is (spec/valid? ::ps/first-name "Tom"))
    (test/is (false? (spec/valid? ::ps/first-name "   ")))
    (test/is (false? (spec/valid? ::ps/first-name "")))
    (test/is (false? (spec/valid? ::ps/first-name nil)))))

(test/deftest gender-spec-test
  (test/testing "test gender spec validation"
    (test/is (spec/valid? ::ps/gender "M"))
    (test/is (spec/valid? ::ps/gender "F"))
    (test/is (false? (spec/valid? ::ps/gender "X")))
    (test/is (false? (spec/valid? ::ps/gender "   ")))
    (test/is (false? (spec/valid? ::ps/gender "")))
    (test/is (false? (spec/valid? ::ps/gender nil)))))

(test/deftest favorite-color-spec-test
  (test/testing "test favorite-color spec validation"
    (test/is (spec/valid? ::ps/favorite-color "Green"))
    (test/is (spec/valid? ::ps/favorite-color "Red"))
    (test/is (false? (spec/valid? ::ps/favorite-color "   ")))
    (test/is (false? (spec/valid? ::ps/favorite-color "")))
    (test/is (false? (spec/valid? ::ps/favorite-color nil)))))

(test/deftest favorite-color-spec-test
  (test/testing "test favorite-color spec validation"
    (test/is (spec/valid? ::ps/favorite-color "Green"))
    (test/is (spec/valid? ::ps/favorite-color "Red"))
    (test/is (false? (spec/valid? ::ps/favorite-color "   ")))
    (test/is (false? (spec/valid? ::ps/favorite-color "")))
    (test/is (false? (spec/valid? ::ps/favorite-color nil)))))

(test/deftest date-of-birth-spec-test
  (test/testing "test date-of-birth spec validation"
    (test/is
     (false? (spec/valid? ::ps/date-of-birth "190011")))
    (test/is (false? (spec/valid? ::ps/date-of-birth "1900/01/01")))
    (test/is (false? (spec/valid? ::ps/date-of-birth "20180101")))
    (test/is (false? (spec/valid? ::ps/date-of-birth "   ")))
    (test/is (false? (spec/valid? ::ps/date-of-birth "")))
    (test/is (false? (spec/valid? ::ps/date-of-birth nil)))))

(test/deftest person-record-test
  (test/testing "test person record spec validation"
    (test/is (false? (spec/valid? ::ps/person nil)))
    (test/is (false? (spec/valid? ::ps/person {})))
    (test/is
     (false?
      (spec/valid? ::ps/person
                   {:last-name      "Brees"
                    :first-name     "Brew"
                    :favorite-color "Black"
                    :date-of-birth  "01/01/1971"})))
    (test/is
     (true?
      (spec/valid? ::ps/person
                   {:last-name      "Brees"
                    :first-name     "Brew"
                    :gender         "M"
                    :favorite-color "Black"
                    :date-of-birth  "01/01/1971"})))))

(test/deftest valid-person?-test
  (test/testing "test valid-person?-test"
    (test/is (false? (ps/valid-person? nil)))
    (test/is (false? (ps/valid-person? {})))))

(test/deftest valid-person?-test
  (test/testing "test valid-person? function"
    (test/is
     (true?
      (ps/valid-person?
       {:last-name      "Hammon"
        :first-name     "Becky"
        :gender         "F"
        :favorite-color "Black"
        :date-of-birth  "01/01/1980"})))
    (test/is
     (false?
      (ps/valid-person?
       {:last-name      "Root"
        :first-name     "Joe"
        :gender         "M"
        :favorite-color "Blue"
        :date-of-birth  "19900101"})))))