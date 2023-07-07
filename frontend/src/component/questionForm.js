import React, {useState} from "react";
import {NavLink} from "react-router-dom"
import {useSelector, useDispatch} from "react-redux"
import { saveQuestion } from "../state/question/questionAction";

export default function QuestionForm(){
    const [question, setQuestion] = useState("")
    const user = useSelector(state=>state.userReducer.user)
    const dispatch = useDispatch()
    
    const onClick = (e)=>{
        e.preventDefault()
        const data = {userId: user.id, question}
        dispatch(saveQuestion(data))
    }
    return (<>
    <div><NavLink to="/">Home</NavLink></div>
    <div>
        <div>Question: <input type="text" onChange={(e)=>setQuestion(e.target.value)}/></div>
        <div><button onClick={onClick}>Save</button></div>
    </div>
        
    </>)
}