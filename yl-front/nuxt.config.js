module.exports = {
    plugins: [
        { src: "~/plugins/vue-quill-editor.js", ssr: false },
        { src: '~/plugins/nuxt-swiper-plugin.js', ssr: false }
    ],
    css: [
        'swiper/dist/css/swiper.css',
        'quill/dist/quill.snow.css',
        'quill/dist/quill.bubble.css',
        'quill/dist/quill.core.css'
    ],
    /*
     ** Headers of the page
     */
    head: {
        title: '智慧养老服务平台',
        meta: [
            { charset: 'utf-8' },
            { name: 'viewport', content: 'width=device-width, initial-scale=1' },
            { hid: 'keywords', name: 'keywords', content: '智慧养老服务平台' },
            { hid: 'description', name: 'description', content: '智慧养老服务平台' }
        ],
        link: [
            { rel: 'icon', type: 'image/x-icon', href: '/favicon.ico' }
        ]
    },
    /*
     ** Customize the progress bar color
     */
    loading: { color: '#3B8070' },
    /*
     ** Build configuration
     */
    build: {
        /*
         ** Run ESLint on save
         */
        extend(config, { isDev, isClient }) {
            if (isDev && isClient) {
                config.module.rules.push({
                    enforce: 'pre',
                    test: /\.(js|vue)$/,
                    loader: 'eslint-loader',
                    exclude: /(node_modules)/
                })
            }
        }
    }
}
