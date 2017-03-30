(defproject collatz "0.1.0-SNAPSHOT"
  :description "Collatz conjecture computation with snazzy rendering."
  :url "https://github.com/Olical/collatz"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [quil "2.6.0"]]
  :aot [collatz.visualisation]
  :main collatz.visualisation)
