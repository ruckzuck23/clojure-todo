(ns todo.app
      (:require [reagent.core :as r])
      (:require [reagent.dom :as dom])
      )

(defn init []
      (println "App initiated!"))

(defonce counters (r/atom []))

(defonce tasks (r/atom [{:content "example" :done? false}]))

(defn add-task [s]
      (swap! tasks conj (hash-map :content s, :done? false)))

(defn drop-nth [coll i]
      (vec (concat (take i coll) (drop (inc i) coll))))

(defn task-input [value]
      [:input {:type "text"
               :value @value
               :on-change #(reset! value (-> % .-target .-value))
               :placeholder "type task here"}])

(defn add-task-button []
       (let [val (r/atom "")]
            (fn []
                [:div
                  [task-input val]
                  " " [:button
                  {:onClick (fn [] (add-task @val))}
                  "Add Task"]])) )

(defn App []
      [:div
       [:h1 "Tasks"]
       (doall
            (for [[i task] (map vector (range) @tasks)]
                  [:div {:key (str i)}
                   (task :content)
                   " " [:button
                        {:onClick (fn [] (swap! tasks update i (fn [task] (update task :done? #(not %)))))}
                        "✓"]
                    " " [:button
                         {:onClick (fn [] (swap! tasks drop-nth i))}
                         "X"]]))
                  [add-task-button (r/atom "")]
                  ])


(dom/render [App] (js/document.getElementById "app"))