(ns todo.app
      (:require [reagent.core :as r])
      (:require [reagent.dom :as dom])
      )

(defn init []
      (println "App initiated!"))

(println "Code reloaded")

(defn Application []
      "Hello World!")

(dom/render [Application] (js/document.getElementById "app"))