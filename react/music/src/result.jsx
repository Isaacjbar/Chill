import { useContext } from "react"
import { NotesContext } from "./NotesContext"
export function DisplayResult() {
    const {note} = useContext(NotesContext)
    return (
        <>
        <p key={note.id}>Result: {note.note}</p>
        </>
    )
}