(ns collatz.core)

(defn collatz
  "Generate a lazy Collatz conjecture sequence starting at the given position."
  [n]
  {:pre [(number? n)
         (>= n 1)]}
  (lazy-seq
   (cons n
         (when (> n 1)
           (let [next (if (even? n) (/ n 2) (+ 1 (* n 3)))]
             (collatz next))))))
