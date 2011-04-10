(ns GameOfLife.draw)

(defn draw-row [row]
  (def return-string
       (interpose
	" "
	(loop [cells row, string ""]
	  (if (> (count cells) 0)
	    (recur (rest cells) (str
				 (if (first cells) "x" ".")
				 string))
	string
	)
	  )
	)
       )
  ;(def return-string (interpose " " (repeat (count row) ".")))
  (apply str (reverse (doall return-string)))
  )

(defn draw-board [board]
  (loop [board-in board, string ""]
    (if (> (count board-in) 0)
      (recur (rest board-in)
	     (str
	      string
	      (draw-row (first board-in))
	      (if (> (count (rest board-in)) 0) \newline "")
	      )
	     )
      string
      )
    )
  )