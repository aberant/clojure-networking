(import '(java.io IOException)
        '(java.net InetAddress DatagramPacket DatagramSocket SocketException))

(defn localhost [] (. InetAddress getLocalHost))

(def udp-server (ref nil))

(defn message [text]
  (new DatagramPacket (. text getBytes) (. text length) (localhost) 3333))

(defn send-message [text]
  (. send udp-server (message text)))

(defn create-udp-server []
  (DatagramSocket. 3333))

(defn start-udp-server []
     (dosync (ref-set udp-server (create-udp-server))))

(defn stop-udp-server []
     (.close @udp-server))

(defn test-crap []
  (start-udp-server)
  (send-message "hello")
  (stop-udp-server))
