(ns scad-clj-workflow.example
  (:require [scad-clj.model :as m]
            [scad-clj.scad :refer [write-scad]]))

(do
  (def smoothness 100)
  (def house-length-x 100)
  (def house-length-y 40)
  (def house-height 20)
  (def house-color [2/3 1/2 2/5 1])

  (def roof-base-z (/ house-height 2))
  (def roof-top-z (+ roof-base-z 15))
  (def roof-top-t 1)
  (def roof-color [1/4 1/4 1/4 1])

  (def name-plate-w 210)
  (def name-plate-h 40)
  (def plate-t 1)
  (def plate-color [0 0 0 1/4])

  (def text-s (/ name-plate-h 2))
  (def text-t 2)
  (def text-color [1 3/4 1/3 1]))

(defn render [x]
  (spit "render.scad"
        (write-scad (->> x
                         (m/translate [0 0 (/ house-height 2)])))))

(defn name-plate-base []
  (->> (m/minkowski
        (m/cube name-plate-w name-plate-h plate-t)
        (m/cylinder (/ name-plate-h 10) 1/10000000000))
       (m/color plate-color)))

(defn name-label []
  (->> (m/text "Calva ♥️ scad-clj" :size text-s :halign "center" :valign "center")
       (m/color text-color)
       (m/translate [0 0 text-t])))

(defn name-plate []
  (->> (m/union (name-plate-base)
                (name-label))
       (m/rotatec [(/ Math/PI 6) 0 0])
       (m/translate [11
                     (+ house-length-y (/ name-plate-h 2))
                     0])))

(defn house-body []
  (->> (m/cube house-length-x house-length-y house-height)
       (m/color house-color)))

(defn roof-base []
  (->> (m/cube house-length-x house-length-y 1)
       (m/translate [0 0 roof-base-z])))

(defn roof-top []
  (->> (m/cube house-length-x roof-top-t roof-top-t)
       (m/translate [0 0 roof-top-z])))

(defn roof []
  (->> (roof-base)
       (m/hull (roof-top))
       (m/color roof-color)))

(defn model []
  (->> (m/union (house-body)
                (roof)
                (name-plate))
       (m/with-fn smoothness)))

(comment
  (render (model)))
