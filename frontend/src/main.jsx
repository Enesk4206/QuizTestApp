import { createRoot } from 'react-dom/client'
import './index.css'
import App from './App.jsx'
import { BrowserRouter as Router} from "react-router-dom"
import React from 'react'
import { AuthProvider } from './util/AuthContext.jsx'

createRoot(document.getElementById('root')).render(
 <React.StrictMode>
    <Router>
      <AuthProvider>
        <App/>
      </AuthProvider>
    </Router>
 </React.StrictMode>
)
