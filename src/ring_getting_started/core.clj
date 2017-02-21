(ns ring-getting-started.core
  (:require [ring.util.response :refer [response]]))

;; Getting Started
(defn handler [request]
  {:status 200,
   :headers {"Content-Type" "text/html"},
   :body "Hello World"})

;; Concepts
; Handlers, Requests, Responses
(defn what-is-my-ip [request]
  {:status 200
   :headers {"Content-Type" "text/plain"}
   :body (:remote-addr request)})

; Middleware
(defn wrap-content-type [handle content-type]
  (fn [request]
    (let [response (handler request)]
      (assoc-in response [:headers "Content-Type"] content-type))))
; apply middleware to an handler
(def sample-middleware-app
  (wrap-content-type what-is-my-ip "text/html"))

;; Creating responses
(def simple-response (response "Ciao Mondo!"))
(defn sample-response [request]
  simple-response)
