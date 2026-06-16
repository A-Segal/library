import Box from "@mui/material/Box"
import  image from "../assets/librarynice.png";
import { useContext } from "react";
import { UserContext } from "../Context/UserContext";


 

 const Home = () => {

  const {user,setUser}=useContext(UserContext)

  return (
   <Box
     sx={{
      marginTop:"-40px",
      backgroundImage:`url(${image})`,
      backgroundRepeat: "no-repeat",
      backgroundSize: "cover",
      height: "700px",
     }}
   >

 <h1 >welcome to :{user?.firstName+" "+user?.lastName}</h1>
 <h2>we happy to meet you again!!</h2>
   </Box>
  )    
}
export default Home



 