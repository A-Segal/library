
import { useForm } from 'react-hook-form'
import { yupResolver } from '@hookform/resolvers/yup';
import * as yup from "yup";
import { addUser } from '../server/Users';
import { UserContext } from '../Context/UserContext';
import { useContext, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import TextField from '@mui/material/TextField';
import Stack from '@mui/material/Stack';
import Button from '@mui/material/Button';
import SendIcon from '@mui/icons-material/Send';
import Snackbar from '@mui/material/Snackbar';



export const schema = yup.object({

  firstName: yup.string().matches(/[a-z]+/, 'Is not in correct format').required(),
  lastName: yup.string().matches(/[a-z]+/, 'Is not in correct format').required(),
  userName: yup.string().required(),
  password: yup.string().max(4).required(),
  tz: yup.string().required(),
  phoneNumber: yup.string().max(10).matches(/[0-9]+/, 'Is not in correct format').required(),
  mail: yup.string().email().required(),



}).required();




const SignUp = () => {

  const { setUser, user } = useContext(UserContext);
  const navigate = useNavigate()
  const [open, setOpen] = useState(false)
  const { register, handleSubmit, formState: { errors } } = useForm({
    resolver: yupResolver(schema),

  });
  const onSubmit = async (data: Object) => {
    try {
      const res = await addUser(data);
      setUser(res?.data);
      { res && navigate('/Home') }
    }
    catch {
      setOpen(true)
    }
  }



  return (

    <form onSubmit={handleSubmit(onSubmit)} >
      <Stack gap={2} id="input">

        <TextField id="outlined-basic" label="firstName" variant="outlined" {...register("firstName", { required: "enter firstName" })}
          placeholder='firstName' />
        {errors.firstName && <p >{errors.firstName.message}</p>}



        <TextField id="outlined-basic" label="lastName" variant="outlined" {...register("lastName", { required: "enter lastName" })}
          placeholder='lastName' />
        {errors.lastName && <p >{errors.lastName.message}</p>}




        <TextField id="outlined-basic" label="password" variant="outlined" {...register("password", { required: "enter password" })}
          placeholder='password' />
        {errors.password && <p >{errors.password.message}</p>}



        <TextField id="outlined-basic" label="userName" variant="outlined" {...register("userName", { required: "enter userName" })}
          placeholder='userName' />
        {errors.userName && <p >{errors.userName.message}</p>}



        <TextField id="outlined-basic" label="tz" variant="outlined" {...register("tz", { required: "enter tz" })}
          placeholder='tz' />
        {errors.tz && <p >{errors.tz.message}</p>}




        <TextField id="outlined-basic" label="phoneNumber" variant="outlined" {...register("phoneNumber", { required: "enter phoneNumber" })}
          placeholder='phoneNumber' />
        {errors.phoneNumber && <p >{errors.phoneNumber.message}</p>}



        <TextField id="outlined-basic" label="mail" variant="outlined" {...register("mail", { required: "enter mail" })}
          placeholder='mail' />
        {errors.mail && <p >{errors.mail.message}</p>}



        <Button type="submit" variant="contained" endIcon={<SendIcon />}>send</Button>

      </Stack>

      <Snackbar
        open={open}
        autoHideDuration={3000}
        onClose={() => setOpen(false)}
        message="שגיאת שרת"
        anchorOrigin={{ vertical: "top", horizontal: "center" }}
      />
    </form>


  )
}

export default SignUp




