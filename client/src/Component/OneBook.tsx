
import * as React from 'react';
import { styled } from '@mui/material/styles';
import Card from '@mui/material/Card';
import CardHeader from '@mui/material/CardHeader';
import CardContent from '@mui/material/CardContent';
import CardActions from '@mui/material/CardActions';
import Collapse from '@mui/material/Collapse';
import IconButton, {type IconButtonProps } from '@mui/material/IconButton';
import Typography from '@mui/material/Typography';
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';
import type { Book } from '../model/Book';
import { UserContext } from '../Context/UserContext';
import {  useLocation } from 'react-router-dom';
import { getCommentById } from '../server/book';
import type { Lend } from '../model/Lend';
import { LendThisBook } from '../server/Lend';
import Snackbar from '@mui/material/Snackbar';
import type {  Comment } from '../model/Comment';





interface ExpandMoreProps extends IconButtonProps {
  expand: boolean;
}
type BookProps={
  book:Book
}
const ExpandMore = styled((props: ExpandMoreProps) => {
  const { expand, ...other } = props;
  return <IconButton {...other} />;
})(({ theme }) => ({
  marginLeft: 'auto',
  transition: theme.transitions.create('transform', {
    duration: theme.transitions.duration.shortest,
  }),

}));

const  RecipeReviewCard:React.FC<BookProps>=({book})=> {
 






    const {pathname}=useLocation();
    const {user ,setUser} = React.useContext(UserContext);
    const [comment, setComment] = React.useState<Comment[]>([])
    const [lends, setLends] = React.useState<Lend[]>([])
    const[open,setOpen]=React.useState(false)




    const getComments = async (bookId: number) => {
      
        try {
            
            const res=await getCommentById(bookId)
            setComment(res.data)
        
             
        }
        catch { 
            setOpen(true)            
        }
    }

const toLendBookById = async (book: Book) => {
      
        try {
            const data={book:book,users:user,lendingDate:new Date()}
            const res=await LendThisBook(data)
            setLends(res.data)
            
        }
        catch { 
            setOpen(true)
            
        }
    }
    




  const [expanded, setExpanded] = React.useState(false);

  const handleExpandClick = () => {
    setExpanded(!expanded);
  };

  return (<>
    <Card sx={{width:"250px"}}>
      <CardHeader sx={{backgroundImage:`url(${book.image})`,backgroundRepeat: "no-repeat",
      backgroundSize: "cover",
       height: "200px",}}/>
      <CardContent>
        
          

        <Typography variant="h6" sx={{ color: 'text.secondary' }}>
          {book.title}
        </Typography>
        
      </CardContent>
      <CardActions disableSpacing>
        



                    {(pathname=='/BookDisplay'&&
                        <button onClick={() => getComments(book.id)}>הצג תגובות</button>)}
                         {(pathname=='/Lend'&&
                        <button onClick={() => toLendBookById(book)}> להשאלת הספר</button>)}
                        


       

        <ExpandMore
          expand={expanded}
           onClick={handleExpandClick}
          aria-expanded={expanded}
          aria-label="show more"
        >
           
          <ExpandMoreIcon />
          
        </ExpandMore>
      </CardActions>
      {comment.map(x=><Typography id="comment" key={x.id}>{x.content}</Typography>)}
      <Collapse in={expanded} timeout="auto" unmountOnExit>
        <CardContent>
          
          <Typography>
          שם המחברת: {book.author}
          </Typography>
          <Typography>
            {book.pageCount}:מספר עמודים
          </Typography>
          <Typography>
           תקציר:{book.summery}
          </Typography>
          <Typography>
            
          </Typography>
          
        </CardContent>
      </Collapse>
        </Card>
        <Snackbar 
             open={open}
             autoHideDuration={3000}
             onClose={()=>setOpen(false)}
             message="שגיאת שרת"
             anchorOrigin={{vertical:"top",horizontal:"center"}}
           />
    
    </>
  )
}
export default RecipeReviewCard