(ns GameOfLife.test.draw
  (:use [GameOfLife.draw] :reload)
  (:use [GameOfLife.core] :reload)
  (:use [clojure.test]))

(deftest replace-me
  (is (= false false))
  )

(deftest draw-row-on-empty-row-returns-empty-string
  (def empty-row (get-row-of-size 0))
  (is (= "" (draw-row empty-row)))
  )

(deftest draw-row-on-1-cell-row-returns-.
  (def row-1-cell (get-row-of-size 1))
  (is (= "." (draw-row row-1-cell)))
  )

(deftest draw-row-on-10-cell-row-returns-.-space-.
  (def row-10-cell (get-row-of-size 10))
  (is (= ". . . . . . . . . ." (draw-row row-10-cell)))
  )

(deftest draw-row-on-10-cell-row-returns-x-inline
  (def row-10-cell-5 (make-row-cell-alive (get-row-of-size 10) 5))
  (is (= ". . . . . x . . . ." (draw-row row-10-cell-5)))
  )

(deftest draw-row-on-10-cell-row-returns-x-at-beginning
  (def row-10-cell-0 (make-row-cell-alive (get-row-of-size 10) 0))
  (is (= "x . . . . . . . . ." (draw-row row-10-cell-0)))
  )

(deftest draw-1-x-1-board-of-empty-cells
  (def board-1-x-1 (get-board 1 1))
  (is (= "." (draw-board board-1-x-1)))
  )

(deftest draw-10-x-1-board-of-empty-cells
  (def board-10-x-1 (vec (take 1 (repeat (get-row-of-size 10)))))
  (is (= ". . . . . . . . . ." (draw-board board-10-x-1)))
  )

(deftest draw-3-x-3-board-of-empty-cells
  (def board-3-x-3 (get-board 3 3))
  (is (= ". . .\n. . .\n. . ." (draw-board board-3-x-3)))
  )

(deftest draw-3-x-3-board-0-0
  (def board-3-x-3 (make-cell-alive (get-board 3 3) 0 0))
  (is (= "x . .\n. . .\n. . ." (draw-board board-3-x-3)))
  )