(ns chapter3-clojure.core)

(defn transcribe [dna-sequence]
  (apply str
         (map {\C \G
               \G \C
               \A \U
               \T \A}
              dna-sequence)))
