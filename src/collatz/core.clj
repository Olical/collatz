(ns collatz.core)

(defn collatz
  "Generate a lazy Collatz conjecture sequence starting at the given position."
  [n]
  (lazy-seq
   (if (<= n 1)
     (list 1)
     (cons n (collatz (if (even? n) (/ n 2) (+ 1 (* n 3))))))))
