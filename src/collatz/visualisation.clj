(ns collatz.visualisation
  (:require [quil.core :as q]
            [quil.middleware :as m]
            [collatz.core :as c])
  (:gen-class))

(def size {:x 800 :y 800})
(def tree (c/collatz-tree 1000))
(def part-size 8)

(defn indexed
  "Returns the lazy sequence but each item is now a vector pair. The first value
  is the index, the second is the original value from the seq."
  [items]
  (map-indexed (fn [n v] [n v]) items))

(defn render-branch
  "Render a single Collatz branch."
  [[bn branch]]
  (q/push-matrix)
  (doseq [[pn part] (indexed branch)]
    (q/stroke 0)
    (q/stroke-weight 15)
    (q/line 0 0 0 part-size)

    (q/stroke 80 100 (+ 100 (mod bn 155)))
    (q/stroke-weight 13)
    (q/line 0 (if (= pn 0) 0 -3) 0 part-size)

    (q/translate 0 part-size)
    (q/rotate (q/radians (if (even? part) 6 -6))))
  (q/pop-matrix))

(defn setup
  "Set up the context and state."
  []
  (q/frame-rate 1)
  {:tree tree})

(defn update-state
  "Perform modifications to the state for the next render."
  [state]
  {:tree tree})

(defn draw-state
  "Render the current state."
  [state]
  (q/background 255 255 255)
  (q/translate 450 (-> size :y (- 20)))
  (q/rotate (q/radians 110))
  (doseq [branch (indexed (:tree state))]
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
