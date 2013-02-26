(ns screwdriver-config
  (:require [clojure.java.io :as io])
  (:import com.semperos.screwdriver.TestUtil))

(def output-dir (str (TestUtil/outputDirectory)))

(defn write-test
  []
  (with-open [w (io/writer (str output-dir "/clojure-output.txt"))]
    (.write w (str "THIS IS CLOJURE WRITING TO A FILE IN: \n"
                    output-dir))))

(write-test)