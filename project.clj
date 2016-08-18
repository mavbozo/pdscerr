(defproject pdscerr "0.1.0-SNAPSHOT"
  :description "repro case for https://github.com/pedestal/pedestal/issues/386"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :resource-paths ["config"]
  :dependencies [[org.clojure/clojure "1.9.0-alpha10"]
                 [io.pedestal/pedestal.service "0.5.1-SNAPSHOT"]
                 [ch.qos.logback/logback-classic "1.1.3" :exclusions [org.slf4j/slf4j-api]]
                 [org.slf4j/jul-to-slf4j "1.7.12" :exclusions [org.slf4j/slf4j-api]]
                 [org.slf4j/jcl-over-slf4j "1.7.12" :exclusions [org.slf4j/slf4j-api]]
                 [org.slf4j/log4j-over-slf4j "1.7.12" :exclusions [org.slf4j/slf4j-api]]
                 [io.pedestal/pedestal.jetty "0.5.1-SNAPSHOT"]]
  :main ^:skip-aot pdscerr.core)
