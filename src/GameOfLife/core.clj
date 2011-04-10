(ns GameOfLife.core)

(defn get-row-of-size [size]
   (vec (take size (repeat false)))
  )

(defn make-row-cell-alive [row x]
  (assoc row x true)
  )

(defn make-row-cell-dead [row x]
  (assoc row x false)
  )

(defn make-cell-alive [board x y]
  (assoc board y (make-row-cell-alive (nth board y) x))
  )

(defn make-cell-dead [board x y]
  (assoc board y (make-row-cell-dead (nth board y) x))
  )

(defn get-board [width height]
  (vec (take width (repeat (get-row-of-size width))))
  )

(defn get-board-with-cells [width height init-cells]
  (let [init-board (get-board width height)]
    (loop [board init-board cells init-cells]
      (if (> (count cells) 0) 
	(recur (make-cell-alive board (first cells) (first (rest cells))) (rest (rest cells)))
	board
	)	       
      )
    )
  )

(defn get-cell [board x y]
  (nth (nth board y) x)
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

(defn get-next-board-recursive [board return-board width height current-x current-y]
  (loop [my-board board
	 my-return-board return-board
	 my-width width
	 my-height height
	 my-current-x current-x
	 my-current-y current-y]
    (def next-board
	 (cond 
	  (> (get-neighbours my-board my-current-x my-current-y) 3)
	  (make-cell-dead my-return-board my-current-x my-current-y)
	  (> (get-neighbours my-board my-current-x my-current-y) 2)
	  (make-cell-alive my-return-board my-current-x my-current-y)
	  (and (> (get-neighbours my-board my-current-x my-current-y) 1) (cell-alive? my-board my-current-x my-current-y))
 	  (make-cell-alive my-return-board my-current-x my-current-y)
	  :default (make-cell-dead my-return-board my-current-x my-current-y)
	  )
	 )
    (cond
     (< (inc my-current-x) width) (recur my-board next-board my-width my-height (inc my-current-x) my-current-y)
     (< (inc my-current-y) height) (recur my-board next-board my-width my-height 0 (inc my-current-y))
     :default next-board
     )
    )
  )

(defn get-next-board [board]
  (let [width (count (first board))
	height (count board)]
    (get-next-board-recursive board (get-board width height) width height 0 0)
    )
  )
