(ns openscad-experiments.pcb-bracket
  (:require [scad-clj.model :as m]
            [openscad-experiments.helpers :refer [render]]))

;; Experiments for mounting brackets using hull

(defn holes [rs height]
  (let [points [[10 10]
                [-10 10]
                [-10 -10]
                [10 -10]]]
    (map #(->> (m/cylinder rs height)
               (m/translate %)
               (m/with-fn 32)) points)))

(render (m/union
         (m/difference (apply m/union (holes 1 2))
                       (apply m/union (holes 0.5 3)))
         (m/difference (apply m/hull (holes 5 4))
                       (->> (holes 4 4)
                            (apply m/hull)
                            (m/translate [0 0 1])))))