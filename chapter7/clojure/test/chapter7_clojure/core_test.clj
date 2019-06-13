(ns chapter7-clojure.core-test
  (:use midje.sweet)
  (:require [clojure.string :refer [join]])
  (:use [chapter7-clojure.core]))

(defn- board [lines]
  (join \newline lines))

(facts "minesweeper board"
       (fact "with no mines reports no neighbouring mines"
             (draw (board ["   "
                           "   "
                           "   "])) => (board ["   "
                                               "   "
                                               "   "]))
       (fact "with one mine has eight cells reporting one mine each"
             (draw (board ["   "
                           " * "
                           "   "])) => (board ["111"
                                               "1*1"
                                               "111"]))
       (fact "with one cell surrounded by mines reporting eight neighbouring mines"
             (draw (board ["***"
                           "* *"
                           "***"])) => (board ["***"
                                               "*8*"
                                               "***"]))
       (fact "full of mines reports no neighbouring mines"
             (draw (board ["***"
                           "***"
                           "***"])) => (board ["***"
                                               "***"
                                               "***"])))
