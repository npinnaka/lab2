(ns lab2.file-processor
  "functions for file load, split and convert data to map"
  (:require [utilities.utils :as util]
            [clojure.string :as strg]
            [spec.person-spec :as ps]
            [clojure.java.io :as io]))

(defn identify-delimiter
  "identify delimiter for given input"
  [person-record-data]
  (cond
    (strg/includes? person-record-data ",")   ","
    (strg/includes? person-record-data "|")   "|"
    (strg/includes? person-record-data " ")   "s"
    :else                                     (throw (RuntimeException. (get util/error-codes "000003")))))

(defn create-map
  "convert person-record to map, return a map"
  [person-record]
  (if (= (count person-record) 5)
    {:last-name      (first person-record)
     :first-name     (second person-record)
     :gender         (nth person-record 2)
     :favorite-color (nth person-record 3)
     :date-of-birth  (last person-record)}
    (throw (RuntimeException. (get util/error-codes "000001")))))

(defn split-row-and-create-map
  "split row by delimiter, returns map"
  [person-record]
  (->
   person-record
   (strg/split (re-pattern (str "\\" (identify-delimiter person-record) "+")))
   (create-map)))

(defn get-validated-data
  "validate loaded data against spec, if successful get data else raise error"
  [persons-vector]
  (let [rows (map split-row-and-create-map persons-vector)]
    (if (every? ps/valid-person? rows)
      (identity
       (map #(merge % {:name (util/get-name-from-map %)}) rows))
      (throw (RuntimeException. (get util/error-codes "000002"))))))

(defn get-validated-data-service
  "validate loaded data against spec, if successful get data else raise error"
  [persons-vector]
  (let [person-row (split-row-and-create-map persons-vector)]
    (if (ps/valid-person? person-row)
      (identity
       (merge person-row
              {:name (util/get-name-from-map person-row)}))
      (throw (RuntimeException. (get util/error-codes "000002"))))))

(defn load-delimiter-file
  "loads file for given delimiter, returns seq of rows from file"
  [file-name]
  (try
    (with-open [rd (io/reader (io/file file-name))]
      (->> (line-seq rd)
           (rest) ;; exclude header
           (into [])))
    (catch java.io.FileNotFoundException e
      (throw (java.io.FileNotFoundException. (get util/error-codes "000000"))))))

(defn load-valid-data
  "load data from given input file, if data is valid then return data from file."
  [file-name]
  (->
   (load-delimiter-file file-name)
   (get-validated-data)))

(defn prepare-data
  "load data from all 3 files and return sequence"
  [csv-file pipe-file space-file]
  (concat (load-valid-data csv-file)
          (load-valid-data pipe-file)
          (load-valid-data space-file)))