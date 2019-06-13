(ns chapter9-clojure.core-test
  (:require [midje.sweet :refer :all]
            [chapter9-clojure.core :refer :all]))

(facts "about a binary tree"
       (fact "a new node contains a value and points to no other nodes"
             (node 1) => {:value 1})
       (fact "values less than or equal are inserted on one side"
             (insert 1 (node 2)) => {:value 2, :lte {:value 1}}
             (insert 2 (node 2)) => {:value 2, :lte {:value 2}})
       (fact "values greater are inserted on the other side"
             (insert 3 (node 2)) => {:value 2, :gt {:value 3}})
       (fact "values are inserted in their proper position in a populated tree"
             (insert 3 {:value 2, :lte {:value 1}, :gt {:value 5, :lte {:value 4}, :gt {:value 6}}}) =>
             {:value 2, :lte {:value 1}, :gt {:value 5, :lte {:value 4, :lte {:value 3}}, :gt {:value 6}}}))