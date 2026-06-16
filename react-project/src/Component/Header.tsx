import { Link} from "react-router-dom"
import { useContext, useState } from "react";
import { UserContext } from "../Context/UserContext";
import Drawer from "@mui/material/Drawer";
import Button from "@mui/material/Button";



const Header=()=> {
           const [open,setOpen] = useState(false);
           const {user,setUser} = useContext(UserContext);
         
         
  return (
    <>

      {user?.status&& <Button  id="menuButton" onClick={()=>setOpen(true)} >תפריט ניהול</Button>}
      {user&&!user?.status&& <Button id="menuButton" onClick={()=>setOpen(true)} >תפריט משתמש️️</Button>}
      {!user&& <Button  id="menuButton" onClick={()=>setOpen(true)}>לאתר➡️</Button>}
    <Drawer 
        anchor="right"
        open={open}
        onClose={()=>setOpen(false)} 
    >

       
          {user&&<Link id="link" to={"BookDisplay"} onClick={()=>setOpen(false)} >  הצגת הספרים</Link>}
          {!user?.status&&user&&<Link id="link" to={"Lend"} onClick={()=>setOpen(false)}>  השאלת ספר</Link>}
          {!user?.status&&user&&<Link id="link" to={"ShowMyLends"} onClick={()=>setOpen(false)}>ההשאלות שלי </Link>}
          {!user&&<Link id="link" to={"login"}onClick={()=>setOpen(false)}> כניסה </Link>}
          {!user&&<Link id="link" to={"SignUp"}onClick={()=>setOpen(false)}> הרשמה </Link>}
          {<Link id="link" to={"About"}onClick={()=>setOpen(false)}>  אודות האתר</Link>}
          {user&&user.status&&<Link id="link" to={"AddBook"} onClick={()=>setOpen(false)}>  להוספת ספר חדש</Link>}
         
      </Drawer>
      
    </>
  )
}

export default Header