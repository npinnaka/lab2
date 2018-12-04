(ns lab2.handler-test
  (:require [clojure.test :as test]
            [ring.mock.request :as mock]
            [lab2.handler :as handler]
            [lab2.controller :as ctrl]
            [clojure.data.json :as json]))

(def data ["Jobs,Steve,M,Black,10/19/1964"
           "Johnson,Abigail,F,Green,12/19/1961"])

(def output
  [{:last-name      "Jobs"
    :first-name     "Steve"
    :gender         "M"
    :favorite-color "Black"
    :date-of-birth  "10/19/1964"}
   {:last-name      "Johnson"
    :first-name     "Abigail"
    :gender         "F"
    :favorite-color "Green"
    :date-of-birth  "12/19/1961"}])

(test/deftest test-app
  (test/testing "main route"
                (let [response (handler/app (mock/request :get "/"))]
                  (test/is (= (:status response) 200))
                  (test/is (= (:body response) "Hello World")))
                (let [response (handler/app
                                (-> (mock/request :post "/records")
                                    (mock/json-body
                                     {:data (first data)})))]
                  (test/is (= (:status response) 200))
                  (test/is
                   (= (json/read-str (:body response) :key-fn keyword)
                      (vector(first output)))))
                (let [response (handler/app
                                (-> (mock/request :post "/records")
                                    (mock/json-body
                                     {:data (second data)})))]
                  (test/is (= (:status response) 200))
                  (test/is
                   (= (json/read-str (:body response) :key-fn keyword)
                      (reverse output))))
                (let [response (handler/app
                                (mock/request :get "/records/gender"))]
                  (test/is (= (:status response) 200))
                  (test/is
                   (= (json/read-str (:body response) :key-fn keyword)
                      (reverse output))))
                (let [response (handler/app
                                (mock/request :get "/records/name"))]
                  (test/is (= (:status response) 200))
                  (test/is
                   (= (json/read-str (:body response) :key-fn keyword)
                      output)))
                (let [response (handler/app
                                (mock/request :get "/records/birthdate"))]
                  (test/is (= (:status response) 200))
                  (test/is
                   (= (json/read-str (:body response) :key-fn keyword)
                      output)))

                (let [response (handler/app (mock/request :get "/invalid"))]
                  (test/is (= (:status response) 404)))))