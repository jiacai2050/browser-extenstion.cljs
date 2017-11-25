(ns hello-world.background)

(enable-console-print!)

(println "Hello from cljs, This is background JS")

;; in chrome extension environment ?
(when (goog.object/getValueByKeys js/window "chrome" "browserAction")
  (.addListener js/chrome.browserAction.onClicked
                (fn [_]
                  (js/alert "Write browser extension in ClojureScript!"))))
