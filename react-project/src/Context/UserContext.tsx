import { createContext, useState, type ReactNode } from "react";
import type { Users } from "../model/Users";


interface userContextType {
  user: null | Users, setUser: (u: Users) => void
}

export const UserContext = createContext<userContextType>({ user: null, setUser: (s: Users) => { } })

const userProvider = ({ children }: { children: ReactNode }) => {

  const [user, setUser] = useState<Users | null>(null);


  return (<UserContext.Provider value={{ user, setUser }}>{children}</UserContext.Provider>)



}
export default userProvider