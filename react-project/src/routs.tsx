import { createBrowserRouter, Navigate } from "react-router-dom";
import App from "./App";
import Login from "./Component/Login";
import Lend from "./Component/Lend";
import SignUp from "./Component/SignUp";
import BookDisplay from "./Component/BookDisplay";
import ShowMyLends from "./Component/ShowMyLends";
import UserProvider,{ UserContext } from "./Context/UserContext";
import Home from "./Component/Home";
import AddBook from "./Component/AddBook";
import {  useContext, type ReactNode } from "react";
import About from "./Component/about";


const RouteRoles = ({ roles, children }: { roles: boolean[], children: ReactNode }) => {
    const { user } = useContext(UserContext)

    if (!user)
        return <Navigate to="" />
    if (!roles .includes( user.status))
        return <Navigate to="" />


    return <>{children}</>

}



const Routes = createBrowserRouter([

    {
        path: "",
        element: <UserProvider> <App /> </UserProvider>,
        children: [{
            path: "login",
            element: <Login />,
           
        },
        {
            path: "Home",
           element: <RouteRoles roles={[true,false]}><Home /></RouteRoles>
        },
        
        {
        path: "SignUp",
        element: <SignUp />
        
        },
        {
            path: "Lend",
            element: <RouteRoles roles={[true,false]}><Lend /></RouteRoles>
        },
        {
            
            path: "About",
           element: <About/>
        },
        
        {
          path:"ShowMyLends",
          element:<RouteRoles roles={[true,false]}><ShowMyLends/></RouteRoles>
        
        },
        {
                    path: "AddBook",
                     element:<RouteRoles roles={[true]}><AddBook /></RouteRoles>
                    
        },
       
        {
            path: "BookDisplay",
               element:<RouteRoles roles={[true,false]}><BookDisplay /></RouteRoles>,
           
        },
        
        


        ],
    }
    


])



export default Routes;