(ns GameOfLife.core)

(defn get-row-of-size [size]
   (vec (take size (repeat false)))
  )

(defn get-column-of-size [size]
  (vec (take size (repeat (get-row-of-size size))))
  )

(defn get-cell [board x y]
  (nth (nth board y) x)
  )

(defn make-row-cell-alive [row x]
  (assoc row x true)
  )

(defn make-cell-alive [board x y]
  (assoc board y (make-row-cell-alive (nth board y) x))
  )

(defn cell-alive? [board x y]
  (cond
   (< x 0) false
   (< y 0) false
   (> y (- (count board) 1)) false
   (> x (- (count (nth board y)) 1)) false
   :default (nth (nth board y) x)
   )
  )

(defn get-neighbours [board x y]
  (+
   0
   (if (cell-alive? board (- x 1) y) 1 0)
   (if (cell-alive? board (+ x 1) y) 1 0)
   (if (cell-alive? board x (- y 1)) 1 0)
   (if (cell-alive? board x (+ y 1)) 1 0)
   (if (cell-alive? board (- x 1) (- y 1)) 1 0)
   (if (cell-alive? board (+ x 1) (- y 1)) 1 0)
   (if (cell-alive? board (- x 1) (+ y 1)) 1 0)
   (if (cell-alive? board (+ x 1) (+ y 1)) 1 0)
   )
  )

(defn get-next-board [board]
  (let [return-board (get-column-of-size 10)]
    return-board
    )
  )

(defn get-next-board-recursive [board return-board width height current-x current-y]
  (loop [my-board board
	 my-return-board return-board
	 my-width width
	 my-height height
	 my-current-x current-x
	 my-current-y current-y]
    (def next-board
	 (if (> (get-neighbours my-board my-current-x my-current-y) 2)
	   (make-cell-alive my-return-board my-current-x my-current-y)
	   my-return-board)
	 )
    (cond
     (< my-current-x width) (recur my-board next-board my-width my-height (inc my-current-x) my-current-y)
     (< my-current-y height) (recur my-board next-board my-width my-height 0 (inc my-current-y))
     :default next-board
     )
    )
  )