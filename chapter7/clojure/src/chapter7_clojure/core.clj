(ns chapter7-clojure.core
  (:require [clojure.string :refer [join]]
            [clojure.string :as str]))

(def neighbours
  [[-1, 1] [0, 1] [1, 1]
   [-1, 0] [1, 0]
   [-1, -1] [0, -1] [1, -1]])

(defn neighbours-of [x y]
  (set (map (fn [[dx dy]]
              [(+ x dx) (+ y dy)])
            neighbours)))

(defn generate-cell
  [neighbours y x]
  (if (contains? neighbours [x y]) 1 0))

(defn generate-line
  [neighbours width y]
  (map (partial generate-cell neighbours y)
       (range 0 width)))

(defn generate-board
  [{width :w height :h} neighbours]
  (mapcat (partial generate-line neighbours width)
          (range 0 height)))

(defn contains-mine? [cell]
  (= \* cell))

(defn board-for-cell
  [dimensions y x cell]
  (generate-board dimensions
                  (if (contains-mine? cell)
                    (neighbours-of x y)
                    #{})))

(defn boards-for-line
  [dimensions line y]
  (map-indexed (partial board-for-cell dimensions y) line))

(defn sum-up [& vals]
  (reduce + vals))

(defn cell-as-text
  [cell-value]
  (if (zero? cell-value)
    \space
    (str cell-value)))

(defn overlay-cell [top bottom]
  (if (contains-mine? top) top bottom))

(defn overlay-boards [top bottom]
  (reduce str (map overlay-cell top bottom)))

(defn intermediate-boards
  [lines dimensions]
  (mapcat (partial boards-for-line dimensions)
          lines
          (range 0 (dimensions :h))))

(defn draw [input-board]
  (let [lines (str/split-lines input-board),
        dimensions {:h (count lines), :w (count (first lines))}]
    (->> (intermediate-boards lines dimensions)
         (apply map sum-up)
         (map cell-as-text)
         (partition (dimensions :h))
         (map (partial reduce str))
         (interpose \newline)
         (reduce str)
         (overlay-boards input-board))))
