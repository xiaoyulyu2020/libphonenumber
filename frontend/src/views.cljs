(ns views
  (:require [subs]
            [events]
            [components.unique-countries :refer [fetch-unique-countries]]
            [components.tadig-network :refer [fetch-tadig-network]]
            [re-frame.core :as re-frame]))

(defn text-input []
  [:div.field
   [:label.label "label"]
   [:div.control
    [:input.input {:on-change #(re-frame/dispatch [:events/update-body (-> % .-target .-value)])
                   :type "text" :placeholder "Text input"}]]])

(defn select-input []
  [:div.field
   [:label.label "label"]
   [:div.control
    [:div.select
     [:select
      [:option "Please select"]
      [:option "option"]]]]])

(defn result-contents []
  (let [body (re-frame/subscribe [:subs/get-body])]
    [:div.field
     [:label.label "Result:"]
      [:ul (map #(vector :li %) @body)]]))

(defn main-panel []
  [:div.section
   [text-input "body"]
   [select-input]
   [:button.button.is-primary {:on-click #(re-frame/dispatch [::events/save-form])} "submit:"]
   [:div.section
    [:div.section [:button.button.is-primary {:on-click fetch-unique-countries} "Show all countries below:"]
     [:button.button.is-primary {:on-click fetch-tadig-network} "Show tadig with their network mappings below:"]]
    [result-contents]]])