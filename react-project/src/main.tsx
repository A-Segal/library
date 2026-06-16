import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import App from './App.tsx'
import { RouterProvider } from 'react-router-dom'
import routs from './routs.tsx'

createRoot(document.getElementById('root')!).render(
  <RouterProvider router={routs} />
  // <StrictMode>
  //   <App />
  // </StrictMode>,
)
