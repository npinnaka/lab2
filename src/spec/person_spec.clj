(ns spec.person-spec
  "spec for person"
  (:require [clojure.spec.alpha :as spec]
            [clojure.string :as strg]
            [utilities.utils :as util]
            [clj-time.format :as format]
            [clojure.tools.logging :as log]))

(spec/def ::last-name
  (spec/and string?
            #(not (strg/blank? %))))

(spec/def ::first-name
  (spec/and string?
            #(not (strg/blank? %))))

(spec/def ::gender
  (spec/and string?
            #(contains? #{"M" "F"} %)))

(spec/def ::favorite-color
  (spec/and string?
            #(not (strg/blank? %))))

(spec/def ::date-of-birth
  (spec/and string?
            #(not (strg/blank? %))
            #(= 10 (count %)) ;;MM/dd/YYYY 10 bytes
            #(try (some? (format/parse-local util/date-formatter %))
                  (catch IllegalArgumentException e
                    (log/error (.getMessage e))
                    (identity false)))))
(spec/def ::person
  (spec/keys :req-un
             [::last-name
              ::first-name
              ::gender
              ::favorite-color
              ::date-of-birth]))

(defn valid-person?
  "returns true for valid data and false for invaid, incase of false logging errors"
  [person]
  (if (spec/valid? ::person person)
    (identity true)
    (do (log/error (spec/explain ::person person))
        (identity false))))