(ns chapter9-clojure.core)

(defn node [from]
  (hash-map :value from))

(defn insert
  ([value in]
   (condp apply [value (:value in)]
     <= (insert value in :lte)
     > (insert value in :gt)))
  ([value tree side]
   (if (contains? tree side)
     (assoc tree side (insert value (tree side)))
     (assoc tree side (node value)))))
