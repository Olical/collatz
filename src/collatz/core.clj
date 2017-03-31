(ns collatz.core)

(defn number-gt-zero?
  "Checks if n is a number that is greater than zero."
  [n]
  (and (number? n)
       (> n 0)))

(defn next-collatz
  "Returns the next step in the Collatz sequence."
  [n]
  {:pre [(number-gt-zero? n)]}
  (cond
    (even? n) (-> n (/ 2))
    (odd? n)  (-> n (* 3) (inc))))

(defn collatz
  "Generate a lazy-seq of Collatz conjecture numbers starting at the given number until 1."
  [n]
  {:pre [(number-gt-zero? n)]}
  (lazy-seq
   (cons n
         (when (> n 1)
           (collatz (next-collatz n))))))

(defn collatz-tree
  "Generate a lazy-seq of lazy-seqs from the collatz function. Starts the seqs at (collatz n), counts down until (collatz 1)."
  [n]
  {:pre [(number-gt-zero? n)]}
  (lazy-seq
   (cons (collatz n)
         (when (> n 1)
           (collatz-tree (dec n))))))
