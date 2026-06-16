import {  useEffect, useState } from "react"
import type { Book } from "../model/Book"
import {  getBooks } from "../server/book"
import { Outlet, useLocation} from "react-router-dom"
import OneBook from "./OneBook"
import Stack from "@mui/material/Stack"
import Snackbar from "@mui/material/Snackbar"

const BookDisplay = () => {
  

    const [book, setBooks] = useState<Book[]>([])
    const [open,setOpen]=useState(false)
    
    
                const func = async () => {
                    try{
                        const res=await getBooks()
                        setBooks(res.data)
                    }
                    catch{
                        setOpen(true)
                     }
                    }
                     useEffect(() => {
                    
                    func()


                     }, [])


    return <Stack flexWrap={"wrap"} direction={"row"} gap={4}>
        

        {book.map(x => <div key={x.id}>
            <OneBook book={x}/></div>)}
        
         <Outlet></Outlet>
 
         <Snackbar 
             open={open}
             autoHideDuration={3000}
             onClose={()=>setOpen(false)}
             message="שגיאת שרת"
             anchorOrigin={{vertical:"top",horizontal:"center"}}
           />
      </Stack>

}

export default BookDisplay


