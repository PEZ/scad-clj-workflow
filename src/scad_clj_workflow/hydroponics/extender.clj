(ns scad-clj-workflow.hydroponics.extender
  (:require [scad-clj.model :as m]
            [scad-clj-workflow.helpers :refer [render]]))

(def opening-diameter 55)
(def outside-diameter 60)
(def curve-radius 10)
(def extender-height 20)

(defn round-shape [diameter]
  (let [circle (m/circle curve-radius)
        circles (for [x [-1 1]
                      y [-1 1]]
                  (as-> [x y 0] $
                      (map (partial * (- (/ diameter 2) curve-radius)) $)
                      (m/translate $ circle)))]
    (apply m/hull circles)))

(render (->> (m/difference (round-shape outside-diameter)
                           (round-shape opening-diameter))
             (m/extrude-linear {:height extender-height
                                :center true
                                :scale 0.9})))
