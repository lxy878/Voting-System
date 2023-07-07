import React, { useEffect } from "react";
import { NavLink } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";
import { getAllQuestions } from "../state/question/questionAction";
import { showDateTime } from "../common/DateTimeForm";
export default function ViewVotes(){
    const questions = useSelector(state=>state.questionReducer.questions)
    const dispatch = useDispatch()

    useEffect(()=>{
        dispatch(getAllQuestions())
    },[])
    
    return (<>
    
    <NavLink to="/">Home</NavLink>
    <table>
        <thead><tr>
            <th>Question</th>
            <th>Created</th>
            <th>Total Vote</th> 
            <th>Yes</th>
            <th>No</th>
        </tr></thead>
        <tbody>
        {questions.map(q=><tr key={q.id}>
            <td>{q.content}</td>
            <td>{showDateTime(q.createTimestamp)}</td>
            <td>{q.totalVotes}</td>
            <td>{q.percentageOfYes*100}%</td>
            <td>{q.percentageOfNo*100}%</td>
            </tr>)}
        </tbody>
    </table>
    </>)
}