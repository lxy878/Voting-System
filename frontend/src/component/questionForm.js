import React, {useState, useEffect} from "react";
import {NavLink} from "react-router-dom"
import {useSelector, useDispatch} from "react-redux"
import { saveQuestion, getAllQuestions } from "../state/question/questionAction";
import { showDateTime } from "../common/DateTimeForm";
export default function QuestionForm(){
    const [content, setContent] = useState("")
    const user = useSelector(state=>state.userReducer.user)
    const questions = useSelector(state=>state.questionReducer.questions)
    const dispatch = useDispatch()
    
    const onClick = (e)=>{
        e.preventDefault()
        const data = {userId: user.id, content}
        dispatch(saveQuestion(data))
    }

    useEffect(()=>{
        dispatch(getAllQuestions('/'+user.id))
    }, [])

    return (<>
    <div><NavLink to="/">Home</NavLink></div>
    <div>
        <div>Question: <input type="text" onChange={(e)=>setContent(e.target.value)}/></div>
        <div><button onClick={onClick}>Save</button></div>
    </div>
    <div>
        <h3>My Questions: {questions.length}</h3>
        <ul>
            {questions.map(q=><li key={q.id}>{q.content}, Created {showDateTime(q.createTimestamp)}</li>)}
        </ul>
    </div>
    </>)
}