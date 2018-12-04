(ns lab2.core
  "entry for console program"
  (:require [lab2.file-processor :as fp]
            [utilities.sorts :as sorts]))


(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (if (= 3 (count args))
    (->
     (fp/prepare-data (first args) (second args) (last args))
     (sorts/apply-sortings))
    (println "Usage: java -jar target/uberjar/lab1-0.1.0-SNAPSHOT-standalone.jar
                                                     csv-file-path
                                                     pipe-file-path
                                                     space-file-path")))