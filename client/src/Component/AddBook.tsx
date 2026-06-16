
import {useForm}  from 'react-hook-form'
import { yupResolver } from '@hookform/resolvers/yup';
import * as yup from "yup";
import { useState } from 'react';
import { addBook } from '../server/book';
import type { Book } from '../model/Book';
import { useNavigate } from 'react-router-dom';
import TextField from '@mui/material/TextField';
import Stack from '@mui/material/Stack';
import Snackbar from '@mui/material/Snackbar';
import SendIcon from '@mui/icons-material/Send';
import Button from '@mui/material/Button';



export const schema2 = yup.object({
    
    
    
    title:  yup.string(),
    author:  yup.string(),
    image:  yup.string(),
    summery:  yup.string(),
    pageCount:  yup.number(),
    CategoryName: yup.string(),
    
    
}).required();


const AddBook:React.FC=()=> {
              const navigate=useNavigate()
            const [open,setOpen]=useState(false)
            const [Book,setBook]=useState<Book>()
      
     const {register,handleSubmit,formState:{errors}}=useForm({
      resolver: yupResolver(schema2),
      });
     const onSubmit=async(data:Object)=>{
      try{
       const res=addBook(data);
       
       setBook((await res).data);
       navigate(-1)
      }
      catch{
        setOpen(true)
      }
     }


  

  return (
   
    <form onSubmit={handleSubmit(onSubmit)} >
       
      <Stack gap={3} id="input" >
      <TextField id="outlined-basic" label="title" variant="outlined" {...register("title",{required:"enter title"})}
      placeholder='title'/>
      {errors.title&&<p >{errors.title.message}</p>}
      


      <TextField id="outlined-basic" label="author" variant="outlined" {...register("author",{required:"enter author"})}
       placeholder='author'/>
      {errors.author&&<p >{errors.author.message}</p>}
      

      <TextField id="outlined-basic" label="image" variant="outlined" {...register("image",{required:"enter image"})}
         placeholder='image'/>
       {errors.image&&<p >{errors.image.message}</p>}



       <TextField id="outlined-basic" label="summery" variant="outlined" {...register("summery",{required:"enter summery"})}
        placeholder='summery'/>
       {errors.summery&&<p >{errors.summery.message}</p>}



       <TextField id="outlined-basic" label="pageCount" variant="outlined" {...register("pageCount",{required:"enter pageCount"})}
        placeholder='pageCount'/>
       {errors.pageCount&&<p >{errors.pageCount.message}</p>}


     <TextField id="outlined-basic" label="CategoryName" variant="outlined" {...register("CategoryName",{required:"enter CategoryName"})}
       placeholder='CategoryName'/>
       {errors.CategoryName&&<p >{errors.CategoryName.message}</p>}




       <Button id="sendB"type="submit" variant="contained" endIcon={<SendIcon />}>send</Button>
      
       </Stack>
       <Snackbar 
         open={open}
         autoHideDuration={3000}
         onClose={()=>setOpen(false)}
         message="שגיאת שרת"
         
         
         anchorOrigin={{vertical:"top",horizontal:"center"}}
       />
      </form>
      
    
  )
}

export default AddBook