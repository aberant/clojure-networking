(ns server.udp
  (:import (java.net InetAddress DatagramPacket DatagramSocket)))

(def udp-server (ref nil))

(def port 3333)

(defn localhost [] (. InetAddress getLocalHost))

(defn message [text]
  (new DatagramPacket (. text getBytes) (. text length) (localhost) port ))

(defn send-message [text]
  (.send @udp-server (message text)))

(defn create-udp-server []
  (DatagramSocket. port))

(defn start-udp-server []
     (dosync (ref-set udp-server (create-udp-server))))

(defn stop-udp-server []
     (.close @udp-server))
