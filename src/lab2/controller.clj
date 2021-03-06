(ns lab2.controller
  "controller for all routers"
  (:require [lab2.file-processor :as fp]
            [clojure.data.json :as json]
            [utilities.sorts :as sorts]
            [clojure.tools.logging :as log]))

(def data (atom '()))

(defn post-record
  "create a record or add new row to existing reocrds, return all data in-memory."
  [input-data]
  (try
    (swap! data conj
           (->
            input-data
            (fp/get-validated-data-service)))
    (catch RuntimeException e (.getMessage e)
      (log/error "runtime exception occured while posting record " e))
    (catch Exception e (.getMessage e)
      (log/error "exception occured while posting record " e))))

;
;(defn sort-by-name
;  "sort data by last-name, returns json formated data"
;  []
;  (json/write-str (sorts/generic-sort @data :last-name)))

(defn sort-by-name
  "sort data by name, returns json formated data"
  []
  (json/write-str (sorts/generic-sort @data :name)))

(defn sort-by
  "a generic sort by function"
  [sort-by direction]
   (sorts/generic-sort @data (keyword sort-by)
                       :sort-order direction))

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