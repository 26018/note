"use strict";

const path = require("path");

// 动态配置IP地址
const os = require("os");
function getNetworkIp() {
  let needHost = ""; // 打开的host
  try {
    // 获得网络接口列表
    let network = os.networkInterfaces();
    for (let dev in network) {
      let iface = network[dev];
      for (let i = 0; i < iface.length; i++) {
        let alias = iface[i];
        if (
          alias.family === "IPv4" &&
          alias.address !== "127.0.0.1" &&
          !alias.internal
        ) {
          needHost = alias.address;
        }
      }
    }
  } catch (e) {
    needHost = "localhost";
  }
  return needHost;
}

module.exports = {
  dev: {
    // Paths
    assetsSubDirectory: "static",
    assetsPublicPath: "/",
    // 配置跨域
    proxyTable: {
      "/api": {
        target: "http://82.157.231.20:1026/", //后端接口地址
        changeOrigin: true, //是否允许跨越
        pathRewrite: {
          "^/api": "", //重写,
        },
      },
    },

    // Various Dev Server settings
    host: getNetworkIp(), // can be overwritten by process.env.HOST
    port: 1234, // can be overwritten by process.env.PORT, if port is in use, a free one will be determined
    autoOpenBrowser: false,
    errorOverlay: true,
    notifyOnErrors: true,
    poll: false, // https://webpack.js.org/configuration/dev-server/#devserver-watchoptions-
    devtool: "cheap-module-eval-source-map",
    cacheBusting: true,
    cssSourceMap: true,
  },

  build: {
    index: path.resolve(__dirname, "../dist/index.html"),
    assetsRoot: path.resolve(__dirname, "../dist"),
    assetsSubDirectory: "static",
    assetsPublicPath: "./",
    productionSourceMap: true,
    devtool: "#source-map",
    productionGzip: false,
    productionGzipExtensions: ["js", "css"],
    bundleAnalyzerReport: process.env.npm_config_report,
  },
};
