
import { useContext } from 'react'
import './App.css'
import Header from './Component/Header'
import { Outlet, useLocation } from 'react-router-dom'
import { UserContext } from './Context/UserContext'
import Entry from './Component/Entry'
const App=()=> {
    const {user,setUser}=useContext(UserContext)
    const {pathname}=useLocation()

    
  return (
    <>
    
        <Header/>
        <Outlet></Outlet>
        {!user&&pathname=='/'&&<Entry/>}
    </>
  )
}

export default App
