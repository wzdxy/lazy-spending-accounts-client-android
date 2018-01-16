# lazy-spending-accounts-client-android
极懒记账 APP 的客户端

## 运行
### 1.Html 页面使用 webpack 构建
```bash
npm i 
npm run build
```
## 2. npm script
```js
"build": "cross-env NODE_ENV=production webpack --progress --hide-modules && npm run cp", // 打包并复制
"cp": "ncp html_dist ./../src/main/assets/html" // dist 复制到安卓 assets 路径
```

## 服务端 repo
[wzdxy/lazy-spending-accounts-server](https://github.com/wzdxy/lazy-spending-accounts-server)
