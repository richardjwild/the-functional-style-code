(ns chapter3-clojure.core-test
  (:require [midje.sweet :refer :all]
            [chapter3-clojure.core :refer :all]))

(facts "RNA transcriber"
  (fact "transcribes DNA"
    (transcribe "ACGTGGTCTTAA") => "UGCACCAGAAUU"))
