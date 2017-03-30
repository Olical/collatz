(ns collatz.core-test
  (:require [collatz.core :as sut]
            [clojure.test :as t]))

(t/testing "Collat seq"
  (t/deftest first-is-the-input
    (t/is (= 10 (first (sut/collatz 10)))))
  (t/deftest last-is-always-1-hopefully
    (t/is (= 1 (last (sut/collatz 1))))
    (t/is (= 1 (last (sut/collatz 5))))
    (t/is (= 1 (last (sut/collatz 10)))))
  (t/deftest invalid-numbers-are-1
    (t/is (= [1] (sut/collatz -5)))
    (t/is (= [1] (sut/collatz 0)))
    (t/is (= [1] (sut/collatz 1)))))
