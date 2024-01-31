(ns main
  (:require [reagent.dom :as rdom]
            [re-frame.core :as re-frame]
            [events]
            [views]))

(defn ^:dev/after-load mount-root []
  (re-frame/clear-subscription-cache!)
  (let [root (.getElementById js/document "app")]
    (rdom/unmount-component-at-node root)
    (rdom/render [views/main-panel] root)))

(defn main! []
  (re-frame/dispatch-sync [:events/initialize-db])
  (mount-root))