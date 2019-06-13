(ns chapter2-clojure.core-test
  (:use midje.sweet)
  (:use [chapter2-clojure.core]))

(println "You should expect to see three failures below.")

(facts "roman numerals"
       (fact "for given number are"
             (convert 1) => "I"
             (convert 2) => "II"
             (convert 3) => "III"
             (convert 4) => "IV"
             (convert 5) => "V"
             (convert 6) => "VI"
             (convert 9) => "IX"
             (convert 27) => "XXVII"
             (convert 48) => "XLVIII"
             (convert 59) => "LIX"
             (convert 93) => "XCIII"
             (convert 141) => "CXLI"
             (convert 163) => "CLXIII"
             (convert 402) => "CDII"
             (convert 575) => "DLXXV"
             (convert 911) => "CMXI"
             (convert 1024) => "MXXIV"))
