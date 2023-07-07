import React, { useEffect } from "react";
import { NavLink } from "react-router-dom";
import {useDispatch, useSelector} from "react-redux"
import { getAllNotVoted, getAllVoted, saveVote} from "../state/question/questionAction";
import { showDateTime } from "../common/DateTimeForm";
export default function Voting(){
    const dispatch = useDispatch()
    const {questions, votedQuestions} = useSelector(state=>state.questionReducer)
    const user = useSelector(state=>state.userReducer.user)
    const uid = user.id
    useEffect(()=>{
        dispatch(getAllNotVoted({uid}))
        dispatch(getAllVoted({uid}))
    }, [])

    const onClick = (e)=>{
        e.preventDefault()
        const vote = e.target.value
        const qid = e.target.getAttribute("qid")
        dispatch(saveVote({vote, qid, uid}))
    }

    return (<>
    <NavLink to="/">Home</NavLink>
    <h2>Questions for Voting: {questions.length}</h2>
    <div>
        {questions.map(q=><div key={q.id}>
            {q.content} create at {showDateTime(q.createTimestamp)}
            <button qid={q.id} value="true" onClick={onClick}>Yes</button>
            <button qid={q.id} value="false" onClick={onClick}>No</button>
        </div>)}
    </div>
    <h2>Voted Questions: {votedQuestions.length}</h2>
    <div>
        {votedQuestions.map(q=><div key={q.id.questionId}>{q.votedQuestion.content} {q.vote? "Yes" : "No"}</div>)}
    </div>
    </>)
}