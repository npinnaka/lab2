(ns utilities.sorts
  "sotring functions"
  (:require [utilities.utils :as util]
            [clj-time.core :as time-core]))

(defn generic-sort
  "a generic sorting function to sort by keys"
  [records
   key-field
   &
   {:keys [sort-order]
    :or   {sort-order "asc"}}]
  (sort-by key-field
           (condp = sort-order
             "date-asc"   #(time-core/after? (util/parse-string->date %2) (util/parse-string->date %1))
             "date-desc"  #(time-core/after? (util/parse-string->date %1) (util/parse-string->date %2))
             "asc"        #(compare %1 %2)
             "desc"       #(compare %2 %1))
           records))

(defn apply-sortings
  "apply sortings for given data"
  [person-records]
  (println "-----------------------------------------------------------------------------------------------")
  (println "Output 1 – sorted by gender (females before males) then by last name ascending.")
  (println
        (-> person-records
            (generic-sort :last-name)
            (generic-sort :gender)))
  (println "-----------------------------------------------------------------------------------------------")

  (println "Output 2 – sorted by birth date, ascending.")
  (println
        (generic-sort person-records :date-of-birth :sort-order "date-asc"))

  (println "-----------------------------------------------------------------------------------------------")

  (println "Output 3 – sorted by last name, descending.")
  (println
   (generic-sort person-records :last-name :sort-order "desc"))
  (println "-----------------------------------------------------------------------------------------------"))