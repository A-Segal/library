import { useContext, useEffect, useState } from "react"
import type { Book } from "../model/Book"
import type { Users } from "../model/Users"
import { UserContext } from "../Context/UserContext"
import { MyLends } from "../server/Lend"
import type { Lend } from "../model/Lend"
import Snackbar from "@mui/material/Snackbar"
import Stack from "@mui/material/Stack"



const ShowMyLends = () => {

  const { user, setUser } = useContext(UserContext)
  const [myUser, setMyUser] = useState<Users>()
  const [lends, setLends] = useState<Lend[]>([])
  const [book, setBooks] = useState<Book[]>([])
  const [open, setOpen] = useState(false)


  useEffect(() => {
    const func = async (userId: number) => {
      try {
        const resLend = await MyLends(userId)

        setLends(resLend.data)
      }
      catch {
        setOpen(true)
      }



    }
    const id = user ? user.id : 65
    func(id);


  }, [])





  return (
    <Stack id="myLend"sx={{ marginTop: "60px" }}>

      {lends.map(x => <><div id="divLend">{x.bookName}📘</div>
     {x.lendingDate} :תאריך השאלה


      </>)}
      <Stack sx={{ marginTop: "px" }}>⚠️יש להחזיר תוך שלושים יום⚠️</Stack>



      <Snackbar
        open={open}
        autoHideDuration={3000}
        onClose={() => setOpen(false)}
        message="שגיאת שרת"
        anchorOrigin={{ vertical: "top", horizontal: "center" }}
      />
    </Stack>
  )
}

export default ShowMyLends