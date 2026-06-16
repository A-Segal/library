import axios from 'axios';
import { base_url } from '../config';


export const addUser = async (data: Object) => {
  const res = await axios.post(`${base_url}/users/addUser`, data)
  return res

}

export const getUserByPassword = async (data: Object) => {



  const res = await axios.post(`${base_url}/users/getUserByPassword`, data)
  return res


}

export const updateUser = async (data: Object, UserId: number) => {



  const res = await axios.put(`${base_url}/users/update/${UserId}`, data)
  return res



}