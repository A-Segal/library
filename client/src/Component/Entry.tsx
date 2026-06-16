


import Box from "@mui/material/Box"
import  image from "../assets/librarynice.png";
import Stack from "@mui/material/Stack";




 const Entry = () => {
  return (
   <Box
            sx={{
              marginTop:"-40px",
              backgroundImage:`url(${image})`,
              backgroundRepeat: "no-repeat",
              backgroundSize: "cover",
              height: "700px",
              opacity: 0.9
            }}
       >

    
        <h1>welcome to the BigLibrary</h1>
        <Stack gap={4} >
        <p id="EntryP">
        we invite you to join us</p>

        <p id="EntryP">and enjoy from a wide range of options </p>


        </Stack>
   </Box>
  )    
}
export default Entry



 