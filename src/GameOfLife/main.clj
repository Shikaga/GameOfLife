(ns GameOfLife.main
  (:use [GameOfLife.core] :reload)
  (:use [GameOfLife.draw] :reload))

(defn -main [& args]
  (def board (get-board-with-cells 10 10 '(1 0 2 1 0 2 1 2 2 2)))

  (while true
    (def board (get-next-board board))
    (println (draw-board board))
    (println "-")
    (. Thread (sleep 1000))
  )
)