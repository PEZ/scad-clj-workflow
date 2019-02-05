(ns openscad-experiments.watering-plug
  (:require [scad-clj.model :as m]
            [openscad-experiments.helpers :refer [render]]))

;; Small plug for a 21mm watering pipe (plants)

(render
 (let [top (->> (m/cylinder (/ 21.5 2) 0.1)
                (m/translate [0 0 -20])
                (m/with-fn 48))
       bottom (->> (m/cylinder (/ 15 2) 0.1)
                   (m/with-fn 48))]

   (m/hull top bottom)))

