(ns mini.replicant
  (:require [replicant.core :as r-core]
            [replicant.dom :as r-dom]))

(defonce !el
  (atom (js/document.getElementById "app")))

(defn dispatch!
  "Dispatch event data outside of Replicant views"
    ;; TODO: Reimplement with public API once Replicant has one
  [e data]
  (let [el @!el]
    (if (and r-core/*dispatch* el)
      (if (get-in @r-dom/state [el :rendering?])
        (js/requestAnimationFrame #(r-core/*dispatch* e data))
        (r-core/*dispatch* e data))
      (throw (js/Error. "Cannot dispatch custom event data without a global event handler. Call replicant.core/set-dispatch!")))))