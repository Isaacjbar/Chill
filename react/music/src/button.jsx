import { useContext } from "react"
import { NotesContext } from "./NotesContext"
export function ButtonPlay() {
  const {note} = useContext(NotesContext)
  return (
    <>
      <button onClick={()=>{
        console.log("Suena: "+note.note);
        
      }}>Play</button>
    </>
  );
}
export function ButtonNote() {
  const {changeNote} = useContext(NotesContext)
  return (
    <>
      <button onClick={changeNote}>Nota</button>
    </>
  );
}
