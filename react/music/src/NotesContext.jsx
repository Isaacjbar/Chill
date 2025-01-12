import { useState, useEffect, createContext } from "react";
import { Notes as data } from "./notes";

export const NotesContext = createContext();

export function NotesContextProvider(props) {
    const [note,setNote] = useState([]);
    useEffect(()=>{
        setNote(data[0]);
    },[])
    function changeNote(){
        setNote(data[Math.floor(Math.random()*6)])
    }
    return (
        <NotesContext.Provider value={{note,changeNote}}>{props.children}</NotesContext.Provider>
    )
}