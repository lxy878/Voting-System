import React, { useEffect } from "react";
import { NavLink } from "react-router-dom";
import {useDispatch, useSelector} from "react-redux"
import { getAllNotVoted, getAllVoted, saveVote} from "../state/question/questionAction";
import { showDateTime } from "../common/DateTimeForm";
import {Badge, Button, Container, ListGroup} from 'react-bootstrap'

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

    return (<Container>
    <NavLink to="/">Home</NavLink>
    <h3>Questions for Voting: {questions.length}</h3>
    <ListGroup>
        {questions.map(q=><ListGroup.Item key={q.id}>
            <div>{q.content}</div> <div>create at {showDateTime(q.createTimestamp)}</div>
            <Button qid={q.id} value="true" onClick={onClick}>Yes</Button>{' '}
            <Button qid={q.id} value="false" onClick={onClick}>No</Button>
        </ListGroup.Item>)}
    </ListGroup>
    <h2>My Voted Questions: {votedQuestions.length}</h2>
    <ListGroup>
        {votedQuestions.map(q=><ListGroup.Item key={q.id.questionId}>
            <Badge>{q.vote? "Yes" : "No"}</Badge> {q.votedQuestion.content}
        </ListGroup.Item>)}
    </ListGroup>
    </Container>)
}