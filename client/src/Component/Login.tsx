
import {useForm}  from 'react-hook-form'
import { getUserByPassword } from '../server/Users';
import { UserContext } from '../Context/UserContext';
import { useContext, useState } from 'react';
import {  useNavigate } from 'react-router-dom';
import TextField from '@mui/material/TextField';
import Stack from '@mui/material/Stack';
import Button from '@mui/material/Button';
import Snackbar from '@mui/material/Snackbar';

interface FormValues{
  userName:string,
  password:string
}

const Login:React.FC=()=>{
    const [loading, setLoading] = useState(false);
    const [open,setOpen]=useState(false)
    const {user ,setUser} = useContext(UserContext);
    const {register,handleSubmit,formState:{errors}}=useForm<FormValues>();
    const navigate=useNavigate()

    const onSubmit=async(data:FormValues)=>{
              setLoading(true)
         try{
                const myUser=await getUserByPassword(data)
                setUser(myUser?.data) 
                {myUser?.data.userName==data.userName?navigate('/Home'):setOpen(true)}
          }
         catch{
                setOpen(true)
          }
           
          setLoading(false)
     }



  return (
    

      <form onSubmit={handleSubmit(onSubmit) } id="formLogin" >
       
      <Stack gap={5} id="input1">

      <TextField id="outlined-basic" label="userName" variant="outlined" {...register("userName",{required:"enter userName"})}
         placeholder='userName'/>
        {errors.userName&&<p >{errors.userName.message}</p>}

      <TextField id="outlined-basic" label="password" variant="outlined" {...register("password",{required:"enter password"})}
         placeholder='password'/>
       {errors.password&&<p >{errors.password.message}</p>}

      <Button type="submit"
          
          loading={loading}
          variant="contained"
          
         
        >
          send
      </Button>

      </Stack>
      <Snackbar 
         open={open}
         autoHideDuration={3000}
         onClose={()=>setOpen(false)}
         message="you are not exist!!   
         are you guest in our website?join us"
         anchorOrigin={{vertical:"top",horizontal:"center"}}
       />


  
      </form>

  )};


export default Login








  


