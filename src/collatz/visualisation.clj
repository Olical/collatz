(ns collatz.visualisation
  (:require [quil.core :as q]
            [quil.middleware :as m]
            [collatz.core :as c])
  (:gen-class))

(def size {:x 800 :y 800})
(def step-length 10)
(def step-weight 10)
(def step-border 2)
(def deflect-angle 5)

(defn render-branch
  "Render a single Collatz branch."
  [branch]
  (q/push-matrix)
  (doseq [step branch]
    (q/stroke 0 0 0)
    (q/stroke-weight step-weight)
    (q/line 0 0 0 step-length)
    (q/stroke 255 255 255)
    (q/stroke-weight (- step-weight step-border))
    (q/line 0 (- (inc step-border)) 0 step-length)
    (q/translate 0 step-length)
    (q/rotate (q/radians (if (even? step) deflect-angle (- deflect-angle)))))
  (q/pop-matrix))

(defn setup
  "Set up the context and state."
  []
  (q/frame-rate 5)
  {:tree (c/collatz-tree 1000)})

(defn update-state
  "Perform modifications to the state for the next render."
  [state]
  state)

(defn draw-state
  "Render the current state."
  [state]
  (q/background 255 255 255)
  (q/push-matrix)
  (q/translate 20 (-> size :y (- 20)))
  (q/rotate (q/radians 180))
  (doseq [branch (:tree state)]
    (render-branch branch))
  (q/pop-matrix))

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
