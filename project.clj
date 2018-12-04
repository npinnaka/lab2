(defproject lab2 "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :bikeshed {:max-line-length 160}
  :eastwood
  {:add-linters [:unused-locals
                 :unused-namespaces]}
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [clj-time "0.15.0"]
                 [org.clojure/spec.alpha "0.2.176"]
                 [org.clojure/data.json "0.2.6"]
                 [compojure "1.6.1"]
                 [org.clojure/tools.logging "0.4.1"]
                 [ring/ring-defaults "0.3.2"]
                 [ring/ring-json "0.4.0"]]
  :plugins
  [[lein-ring "0.12.4"]
   [lein-pprint "1.1.1"]
   [lein-cloverage "1.0.13"]
   [jonase/eastwood "0.3.3"]
   [lein-cljfmt "0.6.2"]
   [pjstadig/humane-test-output "0.8.3"]
   [lein-bikeshed "0.5.1"]]
  :ring {:handler lab2.handler/app}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring/ring-mock "0.3.2"]]}})
