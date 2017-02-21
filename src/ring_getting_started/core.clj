(ns ring-getting-started.core
  (:require [ring.util.response :refer [response content-type]]
            [ring.middleware.content-type :refer [wrap-content-type]]
            [ring.middleware.params :refer [wrap-params]]))

;; Getting Started
(defn handler [request]
  {:status  200,
   :headers {"Content-Type" "text/html"},
   :body    "Hello World"})


;; Concepts
; Handlers, Requests, Responses
(defn what-is-my-ip [request]
  {:status  200
   :headers {"Content-Type" "text/plain"}
   :body    (:remote-addr request)})

; Middleware
(defn wrap-content-type-custom [handle content-type]
  (fn [request]
    (let [response (handler request)]
      (assoc-in response [:headers "Content-Type"] content-type))))

; apply middleware to an handler
(def sample-middleware-app
  (wrap-content-type-custom what-is-my-ip "text/html"))


;; Creating responses
; Simple response
(def simple-response (response "Ciao Mondo!"))
(defn sample-response [request]
  simple-response)

; Response composition
(def response-with-content-type
  (content-type
    (response "Hello World")
    "text/plain"))

(defn conposed-response [request]
  response-with-content-type)


;; Content Types
(defn built-in-content-type [request]
  {:uri    "/test-content-types"
   :status 200,
   :body   "Hola!"})

(def using-ring-middleware-1
  (wrap-content-type built-in-content-type
                     {:mime-types {"ola" "text/x-ola"}}))


;; Parameters
(defn built-in-params [request]
  {:uri    "/test-params"
   :status 200,
   :body   (str (:params request))})

(def using-ring-middleware-2
  (wrap-params built-in-params))