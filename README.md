## 使用 ClojureScript 开发浏览器插件

本项目为使用 ClojureScript 开发浏览器（Firefox/Chrome）插件提供了一个最精简的模板，开发者可再次基础上进行二次开发。这个模板主要有以下特点：

1. 使用 profile 区分不同环境下 JS 脚本（background/content script/option...）
2. 使用 figwheel 进行代码热部署
3. 使用 doo 进行测试

更多信息，可参考文章[《使用 ClojureScript 开发浏览器插件》](http://liujiacai.net/blog/2017/11/22/create-firefox-chrome-extensions-in-clojurescript/)。

本模板所演示的插件功能主要有：

1. 改变任意网页背景为 green，并且设置 body 的 innerHTML 为 `You got an extension written in ClojureScript!`
2. 在 option 页的 console 打印`Hello from cljs, This is option js`
3. 点击 action button 弹出一个 alert 对话框

感兴趣的同学可以下载本项目预先打包好的 [hello-world.crx](./hello-world.crx) 体验，心动不如行动！🍺

## 开发

```shell
# 编译 option page 的 JS
lein with-profile dev-option do clean, figwheel
# 编译 background page 的 JS
lein with-profile +dev-background do clean, figwheel
# 编译 content script
lein with-profile +dev-content do clean, cljsbuild auto
```
使用上面三个命令生成三份 JS 文件后，在 Chrome 的 `chrome://extensions/` 页面勾选 Developer Mode，然后点击「Load unpacked extension...」，这时会弹出文件选择窗，选择本项目的 resources/dev 就可以了。

## 发布

```shell
lein with-profile release do clean, cljsbuild once option background content && \
rm -rf resources/release/background/js/out resources/release/option/js/out resources/release/content/js/out && \
zip -r hello_world.zip resources/release/*
```

## 测试

```shell
lein with-profile test do clean, doo phantom test
```
