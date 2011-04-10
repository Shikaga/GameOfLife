(ns game-of-life.main
  (:use [game-of-life.core] :reload)
  (:use [game-of-life.draw] :reload))

(defn -main [& args]
  (def board (get-board-with-cells 10 10 '(1 0 2 1 0 2 1 2 2 2)))

  (while true
    (def board (get-next-board board))
    (println (draw-board board))
    (println "-")
    (. Thread (sleep 1000))
  )
)
