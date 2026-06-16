import axios from "axios"
import {  base_url } from "../config"

export const getBooks = async () => {
    const res = await axios.get(`${ base_url}/book/getBooks`)
    return res
}
export const getCommentById = async (bookId:number) => {
    const res = await axios.get(`${ base_url}/comment/getCommentsbyBookId/${bookId}`)    
    return res
}
export const addCommentByBookId = async (data:Object) => {
    const res = await axios.post(`${ base_url}/comment/addComment`,data)    
    return res
}
export const addBook = async (data:Object) => {
    const res = await axios.post(`${ base_url}/book/addBook`,data)    
    return res
}
export const deleteThisBook = async (BookId:number) => {
    const res = await axios.delete(`${ base_url}/book/delete/${BookId}`)    
    return res
}





