(ns chapter2-clojure.core)

(def numerals 
  [["M" 1000] ["CM" 900] ["D" 500]
   ["CD" 400] ["C" 100] ["XC" 90]
   ["L" 50] ["XL" 40] ["X" 10]
   ["IX" 9] ["V" 5] ["IV" 4]
   ["I" 1]])

(defn convert
  ([number]
   (convert number 0 ""))
  ([remainder i roman]
   (if (zero? remainder)
     roman
     (let [[symbol value] (nth numerals  i)]
       (if (>= remainder value)
         (convert (- remainder value) i (str roman symbol))
         (convert remainder (inc i) roman))))))

(defn convert-v2 [number]
  (loop [remainder number, i 0, roman ""]
    (if (zero? remainder)
      roman
      (let [[symbol value] (nth numerals i)]
        (if (>= remainder value)
          (recur (- remainder value) i (str roman symbol))
          (recur remainder (inc i) roman))))))