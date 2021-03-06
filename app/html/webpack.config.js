'use strict'

const {join, resolve} = require('path')
const webpack = require('webpack')
const glob = require('glob')

const HtmlWebpackPlugin = require('html-webpack-plugin')
const ExtractTextPlugin = require('extract-text-webpack-plugin')
const CommonsChunkPlugin = require('webpack/lib/optimize/CommonsChunkPlugin')
const OptimizeCSSPlugin = require('optimize-css-assets-webpack-plugin')
const CleanWebpackPlugin = require('clean-webpack-plugin')

const extractCSS = new ExtractTextPlugin({
    filename: 'assets/css/[name].css',
    allChunks: true
})

const entries = {}
const chunks = []
glob.sync('./src/pages/**/app.js').forEach(path => {
    const chunk = path.split('./src/pages/')[1].split('/app.js')[0]
    entries[chunk] = path
    chunks.push(chunk)
})

const config = {
    entry: entries,
    output: {
        path: resolve(__dirname, './html_dist'),
        filename: 'assets/js/[name].js',
        publicPath: process.env.NODE_ENV === 'production'?'./../':'/'
    },
    resolve: {
        extensions: ['.js', '.vue'],
        alias: {
            assets: join(__dirname, '/src/assets'),
            components: join(__dirname, '/src/components'),
            root: join(__dirname, 'node_modules')
        }
    },
    module: {
        rules: [
            {
                test: /\.vue$/,
                loader: 'vue-loader',
                options: {
                    loaders: {
                        css: ['css-hot-loader'].concat(ExtractTextPlugin.extract({
                            use: 'css-loader',
                            fallback: 'style-loader'
                        })),
                        postcss: ['css-hot-loader'].concat(ExtractTextPlugin.extract({
                            use: ['css-loader', 'postcss-loader'],
                            fallback: 'style-loader'
                        }))
                    }
                }
            },
            {
                test: /\.js$/,
                use: 'babel-loader',
                exclude: /node_modules/
            },
            {
                test: /\.css$/,
                use: ['css-hot-loader'].concat(ExtractTextPlugin.extract({
                    use: ['css-loader', 'postcss-loader'],
                    fallback: 'style-loader'
                }))
            },
            {
                test: /\.html$/,
                use: [{
                    loader: 'html-loader',
                    options: {
                        root: resolve(__dirname, 'src'),
                        attrs: ['img:src', 'link:href']
                    }
                }]
            },
            {
                test: /\.(eot|ttf|woff|woff2)(\?.+)?$/,
                use: [{
                    loader: 'file-loader',
                    options: {
                        // useRelativePath: process.env.NODE_ENV === "production",
                        // outputPath:'font',
                        publicPath:'../../../',
                        name: 'assets/font/[name].[hash:7].[ext]'
                    }
                }]
            },
            {
                test: /\.(png|jpg|jpeg|gif|svg|svgz)(\?.+)?$/,
                exclude: /favicon\.png$/,
                use: [{
                    loader: 'url-loader',
                    options: {
                        limit: 10000,
                        name: 'assets/img/[name].[hash:7].[ext]'
                    }
                }]
            }
        ]
    },
    plugins: [
        new CleanWebpackPlugin('html_dist'),
        new webpack.optimize.ModuleConcatenationPlugin(),
        new CommonsChunkPlugin({
            name: 'vendors',
            filename: 'assets/js/vendors.js',
            chunks: chunks,
            minChunks: chunks.length
        }),
        extractCSS
    ],
    devServer: {
        host: '127.0.0.1',
        port: 8010,
        historyApiFallback: false,
        noInfo: true,
        proxy: {
            '/api': {
                target: 'http://127.0.0.1:8080',
                changeOrigin: true,
                pathRewrite: {'^/api': ''}
            }
        },
        open: true,
        openPage: 'add/index.html'
    },
    devtool: '#eval-source-map'
}

glob.sync('./src/pages/**/*.html').forEach(path => {
    const chunk = path.split('./src/pages/')[1].split('/app.html')[0]
    const filename = chunk + '.html'
    const htmlConf = {
        filename: filename,
        template: path,
        inject: 'body',
        favicon: './src/assets/img/logo.png',
        hash: process.env.NODE_ENV === 'production',
        chunks: ['vendors', chunk]
    }
    config.plugins.push(new HtmlWebpackPlugin(htmlConf))
})

module.exports = config

if (process.env.NODE_ENV === 'production') {
    module.exports.devtool = '#source-map'
    // http://vue-loader.vuejs.org/en/workflow/production.html
    module.exports.plugins = (module.exports.plugins || []).concat([
        new webpack.DefinePlugin({
            'process.env': {
                NODE_ENV: '"production"'
            }
        }),
        // new webpack.optimize.UglifyJsPlugin({
        //     compress: {
        //         warnings: false
        //     },
        //     sourceMap: false
        // }),
        new OptimizeCSSPlugin()
    ])
}
