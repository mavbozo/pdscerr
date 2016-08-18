(ns pdscerr.core
  (:gen-class)
  (:require [io.pedestal.interceptor :refer [interceptor]]
            [io.pedestal.http.ring-middlewares :as middlewares]
            [io.pedestal.http.csrf :as csrf]
            [io.pedestal.http :as http]
            [io.pedestal.http.route :as route]
            [io.pedestal.http.body-params :as body-params]
            [io.pedestal.http.route.definition.table :as table]
            [io.pedestal.http.impl.servlet-interceptor :as servlet-interceptor]
            [ring.util.response :as ring-resp]
            ))


(def home-page
  (interceptor
   {:name ::home-page
    :enter (fn [ctx]
             (assoc ctx :response
                    (ring-resp/response "hello-world")))}))

(def routes
  (table/table-routes
   [
    ["/" :get home-page]
    ]))


(def service
  {::http/interceptors [servlet-interceptor/exception-debug
                        middlewares/cookies
                        (middlewares/session {})
                        (csrf/anti-forgery {:cookie-token true})
                        (route/router routes :map-tree)]
   ::http/resource-path "/public"
   ::http/type :jetty
   ::http/join? false
   ::http/port 8080})

(def server)


(defn start
  []
  (alter-var-root #'server
                  (constantly (-> service
                                  http/create-server
                                  http/start))))

(defn stop
  ""
  []
  (alter-var-root #'server
                  (constantly (http/stop server))))


(defn -main
  ""
  []
  (start)
  (println "server started on http://localhost:8080"))

