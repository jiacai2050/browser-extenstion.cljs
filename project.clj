(defproject hello-world "0.1.0-SNAPSHOT"
  :description "A demo used for writing Chrome/Firefox in cljs."
  :url "https://github.com/jiacai2050/browser-extenstion.cljs"
  :min-lein-version "2.7.1"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.9.946"]]
  :profiles {:dev {:dependencies [[figwheel-sidecar "0.5.14"]
                                  [com.cemerick/piggieback "0.2.2"]]
                   :plugins [[lein-figwheel "0.5.14"]
                             [lein-cljsbuild "1.1.7"]
                             [lein-doo "0.1.8"]]
                   :repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}}
             :dev-background {:source-paths ["src/background"]
                              :clean-targets ^{:protect false} ["resources/dev/background/js"] 
                              :cljsbuild {:builds [{:id "dev"
                                                    :figwheel true
                                                    :source-paths ["src/background"]
                                                    :compiler {:output-to "resources/dev/background/js/main.js"
                                                               :output-dir "resources/dev/background/js/out"
                                                               :source-map true
                                                               :optimizations :none
                                                               :main hello_world.background}}]}
                              :figwheel {:server-port 8000
                                         :http-server-root "dev/background"
                                         :server-logfile ".figwheel_background.log"
                                         :repl true}}
             :dev-option {:source-paths ["src/option"]
                          :clean-targets ^{:protect false} ["resources/dev/option/js"]
                          :cljsbuild {:builds [{:id "dev"
                                                :figwheel true
                                                :source-paths ["src/option"]
                                                :compiler {:output-to "resources/dev/option/js/main.js"
                                                           :output-dir "resources/dev/option/js/out"
                                                           :source-map true
                                                           :optimizations :none
                                                           :main hello_world.option}}]}
                          :figwheel {:server-port 8001
                                     :http-server-root "dev/option"
                                     :css-dirs ["resources/dev/option/css"]
                                     :server-logfile ".figwheel_option.log"
                                     :repl true}}
             :dev-content {:source-paths ["src/content"]
                           :clean-targets ^{:protect false} ["resources/dev/content/js"]
                           :cljsbuild {:builds [{:id "dev"
                                                 :source-paths ["src/content"]
                                                 :compiler {:output-to "resources/dev/content/js/main.js"
                                                            :source-map "resources/dev/content/js/main.js.map"
                                                            :output-dir "resources/dev/content/js/out"
                                                            :pretty-print true
                                                            :optimizations :whitespace
                                                            :main hello_world.content}}]}}

             :release {:clean-targets ^{:protect false} ["resources/release/content/js"
                                                         "resources/release/background/js"
                                                         "resources/release/option/js"]
                       :cljsbuild {:builds [{:id "background"
                                             :source-paths ["src/background"]
                                             :compiler {:output-to "resources/release/background/js/main.js"
                                                        :output-dir "resources/release/background/js/out"
                                                        :externs ["externs/chrome_extensions.js" "externs/chrome.js"]
                                                        :optimizations :advanced
                                                        :main hello_world.background}}
                                            {:id "option"
                                             :source-paths ["src/option"]
                                             :compiler {:output-to "resources/release/option/js/main.js"
                                                        :output-dir "resources/release/option/js/out"
                                                        :externs ["externs/chrome_extensions.js" "externs/chrome.js"]
                                                        :optimizations :advanced
                                                        :main hello_world.option}}
                                            {:id "content"
                                             :source-paths ["src/content"]
                                             :compiler {:output-to "resources/release/content/js/main.js"
                                                        :output-dir "resources/release/content/js/out"
                                                        :externs ["externs/chrome_extensions.js" "externs/chrome.js"]
                                                        :optimizations :advanced
                                                        :main hello_world.content}}]}}
             :test {:cljsbuild {:builds [{:id "test"
                                          :source-paths ["test" "src/content" "src/background" "src/option"]
                                          :compiler {:output-to "target/main.js"
                                                     :main hello_world.runner
                                                     :optimizations :none}}]}}}
  :aliases {"option" ["with-profile" "dev-option" "do"
                      ["clean"]
                      ["figwheel" "dev"]]
            "background" ["with-profile" "dev-background" "do"
                          ["clean"]
                          ["figwheel" "dev"]]
            "content" ["with-profile" "dev-content" "do"
                       ["clean"]
                       ["cljsbuild" "auto"]]})
