(ns pipes.http
  (:require [http.async.client :as c]
            [pipes.types :as p]
            [pipes.builder :as b]))

(defn streaming-http-source
  "Manages an asynchronous HTTP connection and streams string chunks
  from the body of the response."
  [method url & options]
  (b/source1* [client (c/create-client)
               resp   (apply c/stream-seq client method url options)]
    (first (c/string resp))
    (.close client)))
