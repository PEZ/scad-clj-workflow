(ns scad-clj-workflow.sierpinski
  (:require [scad-clj.model :as m]
            [scad-clj-workflow.helpers :refer [render]]))

(def tetra-points [[(/ 1 (Math/sqrt 3)), 0, 0]
                   [(/ -1 (* 2 (Math/sqrt 3))), 1/2, 0]
                   [(/ -1 (* 2 (Math/sqrt 3))), -1/2, 0]
                   [0, 0, (Math/sqrt 2/3)]])

(defn tetra []
  (m/polyhedron tetra-points [[0, 1, 2]
                              [2, 1, 3]
                              [0, 2, 3]
                              [0, 3, 1]]))

(defn- level-scale [level]
  (Math/pow 2 (dec level)))

(defn- scale-point [point level]
  (let [scale (level-scale level)]
    (map (partial * scale) point)))

(defn sierpinski [level]
  (if (zero? level)
    (tetra)
    (let [scales   (map #(scale-point % level) tetra-points)
          children (map #(m/translate % (sierpinski (dec level))) scales)]
      (apply m/union children))))

(render (sierpinski 5))
