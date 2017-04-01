(ns collatz.visualisation
  (:require [quil.core :as q]
            [quil.middleware :as m]
            [collatz.core :as c])
  (:gen-class))

(defn reversed-indexed
  "Returns the lazy sequence but each item is now a vector pair. The first value
  is the index, the second is the original value from the seq. The sequence is
  also reversed."
  [items]
  (map-indexed (fn [n v] [n v]) (reverse items)))

(defn gen-tree
  "Generates a full Collatz tree by building the lazy-seqs and reversing them all."
  [n]
  (->> n c/collatz-tree (map reversed-indexed) reversed-indexed))

(def size {:x 800 :y 800})
(def tree (gen-tree 10000))
(def part-size 5)

(defn render-branch
  "Render a single Collatz branch."
  [[bn branch]]
  (q/push-matrix)
  (doseq [[pn part] branch]
    (q/stroke 0)
    (q/stroke-weight 15)
    (q/line 0 0 0 part-size)

    (q/stroke (+ 155 (mod bn 100)) 100 100)
    (q/stroke-weight 13)
    (q/line 0 (if (= pn 0) 0 -3) 0 part-size)

    (q/translate 0 part-size)
    (q/rotate (q/radians (if (even? part) 3 -3))))
  (q/pop-matrix))

(defn setup
  "Set up the context and state."
  []
  (q/frame-rate 0.5)
  {:tree tree})

(defn update-state
  "Perform modifications to the state for the next render."
  [state]
  {:tree tree})

(defn draw-state
  "Render the current state."
  [state]
  (q/background 255 255 255)
  (q/translate 200 (-> size :y (- 20)))
  (q/rotate (q/radians 110))
  (doseq [branch (:tree state)]
    (render-branch branch)))

(defn -main
  "Initialise the sketch."
  []
  (q/sketch
   :title "Collatz in Clojure"
   :size (map size [:x :y])
   :setup #'setup
   :update #'update-state
   :draw #'draw-state
   :features []
   :middleware [m/fun-mode]))
