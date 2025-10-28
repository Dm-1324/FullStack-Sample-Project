// main.js

import { createApp } from 'vue'
import App from './App.vue'

// Vuetify setup
import 'vuetify/styles'
import { createVuetify } from 'vuetify'
import * as components from 'vuetify/components'
import * as directives from 'vuetify/directives'

// 🔑 CORRECTED IMPORT: Use the correct path for the CSS file for side effects
import '@mdi/font/css/materialdesignicons.css'

// 🔑 NEW IMPORT: Import the icon set and aliases from Vuetify's utilities
import { mdi, aliases } from 'vuetify/iconsets/mdi'

// Create Vuetify instance
const vuetify = createVuetify({
  components,
  directives,
  // Configure MDI icons
  icons: {
    defaultSet: 'mdi',
    aliases,
    sets: { mdi },
  },
  // Basic theme customization (optional, but good practice)
  theme: {
    themes: {
      light: {
        colors: {
          primary: '#3F51B5', // Indigo
          secondary: '#607D8B', // Blue Grey
        },
      },
    },
  },
})

// Create and mount the app
createApp(App).use(vuetify).mount('#app')
