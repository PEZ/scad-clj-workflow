(ns openscad-experiments.helpers
  (:require [scad-clj.scad :refer [write-scad]]))

(defn render [what]
  (spit "render.scad" (write-scad what)))
