import axios from "axios"
import { base_url } from "../config"

export const LendThisBook = async (data:Object) => {
  const res = await axios.post(`${ base_url}/lend/addLend`,data)
    return res


}
export const MyLends = async (userId:number) => {
  const res = await axios.get(`${ base_url}/lend/getLends/${userId}`)
  
  
    return res
}

