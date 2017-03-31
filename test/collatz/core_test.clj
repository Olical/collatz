(ns collatz.core-test
  (:require [collatz.core :as sut]
            [clojure.test :as t]))

(t/testing "number-gt-zero?"
  (t/deftest false-for-non-numbers
    (t/is (= false (sut/number-gt-zero? "foo")))
    (t/is (= false (sut/number-gt-zero? true))))
  (t/deftest false-for-non-gt-zero
    (t/is (= false (sut/number-gt-zero? 0)))
    (t/is (= false (sut/number-gt-zero? -10))))
  (t/deftest true-for-number-gt-zero
    (t/is (= true (sut/number-gt-zero? 1)))
    (t/is (= true (sut/number-gt-zero? 10)))))

(t/testing "next-collatz"
  (t/deftest steps-various-numbers
    (t/is (= 5 (sut/next-collatz 10)))
    (t/is (= 2 (sut/next-collatz 4)))
    (t/is (= 34 (sut/next-collatz 11)))
    (t/is (= 22 (sut/next-collatz 7))))
  (t/deftest invalid-args-throw
    (t/is (thrown-with-msg? AssertionError #"^Assert failed: \(number-gt-zero\? n\)$" (sut/next-collatz -5)))
    (t/is (thrown-with-msg? AssertionError #"^Assert failed: \(number-gt-zero\? n\)$" (sut/next-collatz 0)))
    (t/is (thrown-with-msg? AssertionError #"^Assert failed: \(number-gt-zero\? n\)$" (sut/next-collatz "no")))))

(t/testing "collatz"
  (t/deftest first-is-the-input
    (t/is (= 10 (first (sut/collatz 10)))))
  (t/deftest last-is-always-1-hopefully
    (t/is (= 1 (last (sut/collatz 1))))
    (t/is (= 1 (last (sut/collatz 5))))
    (t/is (= 1 (last (sut/collatz 10)))))
  (t/deftest invalid-args-throw
    (t/is (thrown-with-msg? AssertionError #"^Assert failed: \(number-gt-zero\? n\)$" (sut/collatz -5)))
    (t/is (thrown-with-msg? AssertionError #"^Assert failed: \(number-gt-zero\? n\)$" (sut/collatz 0)))
    (t/is (thrown-with-msg? AssertionError #"^Assert failed: \(number-gt-zero\? n\)$" (sut/collatz "no")))))

(t/testing "collatz-tree"
  (t/deftest simple-tree
    (t/is (= [[2 1] [1]] (sut/collatz-tree 2))))
  (t/deftest deeper-tree
    (t/is (= [[4 2 1] [3 10 5 16 8 4 2 1] [2 1] [1]] (sut/collatz-tree 4))))
  (t/deftest invalid-args-throw
    (t/is (thrown-with-msg? AssertionError #"^Assert failed: \(number-gt-zero\? n\)$" (sut/collatz-tree -5)))
    (t/is (thrown-with-msg? AssertionError #"^Assert failed: \(number-gt-zero\? n\)$" (sut/collatz-tree 0)))
    (t/is (thrown-with-msg? AssertionError #"^Assert failed: \(number-gt-zero\? n\)$" (sut/collatz-tree "no")))))
