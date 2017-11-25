(ns hello-world.content)

(enable-console-print!)

(println "Hello from cljs, This is content JS")

;; in brower environment?
(when (.-body js/document)
  (set! (.. js/document
            -body
            -style
            -backgroundColor)
        "green")

  (set! (.. js/document
            -body
            -innerHTML)
        "<h1 align='center'>You got an extension written in ClojureScript!😄</h1>"))
