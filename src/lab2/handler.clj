(ns lab2.handler
  "rest end points handler"
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.json :as middleware]
            [compojure.handler :as handler]
            [lab2.controller :as ctrl]))

(defroutes app-routes
  (GET "/" [] "Hello World")
  (POST "/records" req (ctrl/post-record (get (get req :body) :data)))
  (GET "/records/name" []
    (ctrl/sort-by-name))
  (GET "/records/gender" []
    (ctrl/sort-by-gender))
  (GET "/records/sortby" [_ :as r]
       (ctrl/sort-by (get (:params r) :sortby) (get (:params r) :direction)) )
  (GET "/records/birthdate" [] (ctrl/sort-by-birth-date))
  (route/not-found "Not Found"))

(def app
  (-> (handler/site app-routes)
      (middleware/wrap-json-body {:keywords? true})
      middleware/wrap-json-response))