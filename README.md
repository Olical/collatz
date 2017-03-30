# collatz

> I still need to write the visualisation in quil. But the sequence works fine.

The [Collatz conjecture][wiki] in Clojure as a lazy sequence. Also includes a neat visualisation inspired by [this awesome video][numberphile], you can run the visualisation with `lein run`.

You'll find the actual Collatz sequence function in the `collatz.core` namespace.

```clojure
(ns cool.thing
  (:require [collatz.core :as c]))

(first (c/collatz 1000)) ;; Returns 1000, the seq starts where you specify and counts down.
(last (c/collatz 1000)) ;; Returns 1, as it always will be, I assume?
```

If the last value is ever not 1, notify your closest mathematician and run.

[wiki]: https://en.wikipedia.org/wiki/Collatz_conjecture
[numberphile]: https://www.youtube.com/watch?v=LqKpkdRRLZw
