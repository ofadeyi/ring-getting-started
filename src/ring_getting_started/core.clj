(ns ring-getting-started.core)

;; Getting Started
(defn handler [request]
  {:status 200,
   :headers {"Content-Type" "text/html"},
   :body "Hello World"})