(ns lab2.controller
  "controller for all routers"
  (:require [lab2.file-processor :as fp]
            [clojure.tools.logging :as log]
            [clojure.data.json :as json]
            [utilities.sorts :as sorts]))

(def data (atom '()))

(defn post-record
  "create a record or add new row to existing reocrds, return all data in-memory."
  [input-data]
  (try
    (swap! data conj
           (->
            input-data
            (fp/get-validated-data-service)))
    (catch RuntimeException e (.getMessage e))
    (catch Exception e (.getMessage e))))

(defn sort-by-name
  "sort data by last-name, returns json formated data"
  []
  (json/write-str (sorts/generic-sort @data :last-name)))

(defn sort-by-gender
  "sort data by gneder, returns json formated data"
  []
  (json/write-str (sorts/generic-sort @data :gender)))

(defn sort-by-birth-date
  "sort data by birth-date, returns json formated data"
  []
  (json/write-str
   (sorts/generic-sort @data :date-of-birth :sort-order "date-desc")))

(defn view-all
  "Returns all data, in atom @data"
  []
  @data)