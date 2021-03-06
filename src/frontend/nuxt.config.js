export default {
  // Disable server-side rendering: https://go.nuxtjs.dev/ssr-mode
  ssr: false,

  // Target: https://go.nuxtjs.dev/config-target
  target: 'static',

  // Global page headers: https://go.nuxtjs.dev/config-head
  head: {
    title: 'Web APP',
    htmlAttrs: {
      lang: 'en'
    },
    meta: [
      { charset: 'utf-8' },
      { name: 'viewport', content: 'width=device-width, initial-scale=1' },
      { hid: 'description', name: 'description', content: '' }
    ],
    script:[
      {src:'https://code.iconify.design/1/1.0.7/iconify.min.js'}
    ],
    link: [
      { rel: 'icon', type: 'image/x-icon', href: '/favicon.ico' }
    ]
  },

  // Global CSS: https://go.nuxtjs.dev/config-css
  css: [
  ],

  // Plugins to run before rendering page: https://go.nuxtjs.dev/config-plugins
  plugins: [
    '~/plugins/axios',
    '~/plugins/pretty-checkbox',
    '~/plugins/bs.client'
  ],

  // Auto import components: https://go.nuxtjs.dev/config-components
  components: true,

  // Modules for dev and build (recommended): https://go.nuxtjs.dev/config-modules
  buildModules: [
  ],

  // Modules: https://go.nuxtjs.dev/config-modules
  modules: [
    // https://go.nuxtjs.dev/axios
    '@nuxtjs/axios',
    '@nuxtjs/auth',
  ],

  // Axios module configuration: https://go.nuxtjs.dev/config-axios
  axios:{
    baseURL:'http://localhost:8080',
    credentials:true
  },

  auth: {
    redirect: {
      login: '/login',
      logout:'/',
      callback: '/login',
      home: '/home'
    },
    strategies: {
      local: {
        token: {
          required: false,
          type: false
        },
        endpoints: {
          login: { url: '/api/session/login', method: 'post' },
          logout: { url: '/api/session/logout', method: 'get' },
          user: { url: '/api/session/user', method: 'get' }
        }
      }
    }
  },
  
  generate:{
    dir:'/home/felipe/Documentos/projetos/CVC Transferencias/src/main/resources/static/'
  },
  

  // Build Configuration: https://go.nuxtjs.dev/config-build
  build: {
  }
}
