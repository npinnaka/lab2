(ns utilities.utils
  "utility functions for the proeject"
  (:require [clojure.java.io :as io]
            [clojure.edn :as edn]
            [clj-time.format :as format]))

(defn load-edn
  "Load edn from an io/reader source (filename or io/resource)."
  [source]
  (with-open [r (io/reader source)]
    (edn/read (java.io.PushbackReader. r))))

(defonce error-codes
  (load-edn "resources/errorcodes.edn"))

(def date-formatter
  "formatter for date string MM/dd/yyyy"
  (format/formatter-local "MM/dd/yyyy"))

(defn parse-string->date
  "convert date string to LoadDate"
  [date-string]
  (format/parse-local-date date-formatter date-string))

(defn get-name-from-map
  "get name field from map first-name and last-name"
  [row]
  (str (get row :first-name) " " (get row :last-name)))