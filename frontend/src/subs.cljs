(ns subs
   (:require [re-frame.core :as re-frame]))

(re-frame/reg-sub
 ::database
 (fn [db]
   (:database db)))

(re-frame/reg-sub
 ::get-body
 (fn [db _]
   (get-in db [:database :body] "")))