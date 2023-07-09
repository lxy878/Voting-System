import React, {useState, useEffect} from "react";
import {NavLink} from "react-router-dom"
import {useSelector, useDispatch} from "react-redux"
import { saveQuestion, getAllQuestions } from "../state/question/questionAction";
import { showDateTime } from "../common/DateTimeForm";
import {Button, Container, Form, ListGroup} from 'react-bootstrap'

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

    return (<Container>
    <div><NavLink to="/">Home</NavLink></div>
    <h3>Create A Question</h3>
    <Form>
        <Form.Group>
        <Form.Label>Content:</Form.Label>
        <Form.Control type="text" onChange={(e)=>setContent(e.target.value)}/>
        </Form.Group>
        <Button onClick={onClick}>Save</Button>
    </Form>
    
    <h3>My Questions: {questions.length}</h3>
    <ListGroup>
        {questions.map(q=><ListGroup.Item key={q.id}><div>{q.content}</div><div>Created {showDateTime(q.createTimestamp)}</div></ListGroup.Item>)}
    </ListGroup>
    </Container>)
}