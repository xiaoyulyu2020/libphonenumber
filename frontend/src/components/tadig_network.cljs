(ns components.tadig-network  
  (:require [re-frame.core :as re-frame]
            [ajax.core :as ajax]))

(defn error-handler [{:keys [status status-text]}]
  (.log js/console (str "something bad happened: " status " " status-text)))

(defn fetch-tadig-network []
    (ajax/GET "http://localhost:8080/get-tadig-network-mappings"
      {:error-handler error-handler
       :handler (fn [response]
                  (re-frame/dispatch [:events/update-body :body response]))}))