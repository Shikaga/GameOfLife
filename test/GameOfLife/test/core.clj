(ns GameOfLife.test.core
  (:use [GameOfLife.core] :reload)
  (:use [clojure.test]))


(def empty-board (get-column-of-size 10))
(def board-0-0 (make-cell-alive empty-board 0 0))
(def board-0-0-1-1 (make-cell-alive board-0-0 1 1))
(def board-1-0 (make-cell-alive empty-board 1 0))
(def board-5-5 (make-cell-alive empty-board 5 5))

(deftest replace-me 
  (is (= false false))
  )

(deftest get-row-of-0-cells
  (def row (get-row-of-size 0))
  (is (= (count row) 0))
  )

(deftest get-row-of-1-cells
  (def row (get-row-of-size 1))
  (is (= (count row) 1))
  )

(deftest get-row-of-10-cells
  (def row (get-row-of-size 10))
  (is (= (count row) 10))
  )

(deftest assert-rows-contain-false
  (def row (get-row-of-size 10))
  (is (= false (first row)))
  )

(deftest get-column-of-0-rows
  (def column (get-column-of-size 0))
  (is (= (count column) 0))
  )

(deftest get-column-of-1-rows
  (def column (get-column-of-size 1))
  (is (= (count column) 1))
  )

(deftest get-column-of-10-rows
  (def column (get-column-of-size 10))
  (is (= (count column) 10))
  )

(deftest assert-columns-contain-rows
  (def column (get-column-of-size 10))
  (is (= (first (first column)) false))
  )

(deftest asseert-rows-same-size-as-columns
  (def column (get-column-of-size 10))
  (is (= (count (first column)) (count column)))
  )

(deftest first-cell-in-row-can-be-made-alive
  (def empty-row (get-row-of-size 10))
  (def row (make-row-cell-alive empty-row 0))
  (is (= true (first row)))
  )

(deftest second-cell-in-row-can-be-made-alive
  (def empty-row (get-row-of-size 10))
  (def row (make-row-cell-alive empty-row 1))
  (is (= true (first (rest row))))
  )

(deftest final-cell-in-row-can-be-made-alive
  (def empty-row (get-row-of-size 10))
  (def row (make-row-cell-alive empty-row 9))
  (is (= true (nth row 9)))
  )

(deftest top-left-cell-can-be-made-alive
  (def board (make-cell-alive empty-board 0 0))
  (is (= true (first (first board))))
  )

(deftest middle-cell-can-be-made-alive
  (def board (make-cell-alive empty-board 5 6))
  (is (= true (get-cell board 5 6)))
  )

(deftest assert-cell-is-not-alive
  (is (= false (cell-alive? empty-board 0 0)))
  )

(deftest assert-cell-is-alive
  (is (= true (cell-alive? board-0-0 0 0)))
  )

(deftest assert-is-alive?-returns-false-if-cell-left-of-board
  (is (= false (cell-alive? empty-board -1 0)))
  )

(deftest assert-is-alive?-returns-false-if-cell-above-board
  (is (= false (cell-alive? empty-board 0 -1)))
  )

(deftest assert-is-alive?-returns-false-if-cell-right-of-board
  (is (= false (cell-alive? empty-board 10 0)))
  )

(deftest assert-is-alive?-returns-false-if-cell-bottom-of-board
  (is (= false (cell-alive? empty-board 0 10)))
  )

(deftest assert-cell-has-no-neighbours
  (is (= 0 (get-neighbours empty-board 0 0)))
  )

(deftest assert-cell-has-neighbour-on-left
  (is (= 1 (get-neighbours board-0-0 1 0)))
  )

(deftest assert-cell-has-neighbout-on-right
  (is (= 1 (get-neighbours board-5-5 4 5)))
  )

(deftest assert-cell-has-neighbour-on-top
  (is (= 1 (get-neighbours board-5-5 5 6)))
  )

(deftest assert-cell-has-neighbour-on-bottom
  (is (= 1 (get-neighbours board-5-5 5 4)))
  )

(deftest assert-cell-has-neighbour-on-top-left
  (is (= 1 (get-neighbours board-5-5 6 6)))
  )

(deftest assert-cell-has-neighbour-on-top-right
  (is (= 1 (get-neighbours board-5-5 4 6)))
  )(deftest assert-cell-has-neighbour-on-bottom-left
  (is (= 1 (get-neighbours board-5-5 6 4)))
  )

(deftest assert-cell-has-neighbour-on-bottom-right
  (is (= 1 (get-neighbours board-5-5 4 4)))
  )

(deftest assert-cell-with-two-neighbours
  (is (= 2 (get-neighbours board-0-0-1-1 0 1)))
)

(deftest assert-empty-board-return-empty-board
  (is (= empty-board (get-next-board empty-board)))
  )

(deftest assert-board-with-single-living-cell-returns-empty-board
  (is (= empty-board (get-next-board board-0-0)))
  )

(deftest assert-board-with-two-living-cells-returns-empty-board
  (is (= empty-board (get-next-board board-0-0-1-1)))
  )

(deftest get-next-board-recursive-on-empty-board-returns-empty-board
  (is (= empty-board (get-next-board-recursive empty-board empty-board 10 10 0 0)))
  )

(deftest get-next-board-were-cell-0-0-has-3-neighbours-returns-single-cell-board
  (def board-1-0-0-1 (make-cell-alive board-1-0 0 1))
  (def board-1-0-0-1-1-1 (make-cell-alive board-1-0-0-1 1 1))
  (is (= board-0-0 (get-next-board-recursive board-1-0-0-1-1-1 empty-board 10 10 0 0)))
  )

(deftest get-next-board-recursive-when-living-cell-not-current-x
  (def board-0-0-2-0-1-1 (make-cell-alive board-0-0-1-1 2 0))
  (def board-1-0 (make-cell-alive empty-board 1 0))
  (is (= board-1-0 (get-next-board-recursive board-0-0-2-0-1-1 empty-board 10 10 0 0)))
  )

(deftest get-next-board-recursive-when-living-cell-not-current-y
  (def board-0-0-1-1-0-2 (make-cell-alive board-0-0-1-1 0 2))
  (def board-0-1 (make-cell-alive empty-board 0 1))
  (is (= board-0-1 (get-next-board-recursive board-0-0-1-1-0-2 empty-board 10 10 0 0)))
  )
