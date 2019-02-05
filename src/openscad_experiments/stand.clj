(ns openscad-experiments.stand
  (:require [scad-clj.model :as m]
            [openscad-experiments.helpers :refer [render]]))

;; Hull is awesome for sort of stuff

(def mid-block (->> (m/cube 6 6 1)
                    (m/translate [0 0 15])))

(defn leg [v]
  (->> (m/cube 5 5 1)
       (m/translate v)
       (m/hull mid-block)))

(render (m/union (leg [15 10 0])
                 (leg [15 -10 0])
                 (leg [-15 10 0])
                 (leg [-15 -10 0])))