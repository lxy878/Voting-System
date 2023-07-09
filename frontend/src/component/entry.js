import React, {useState} from "react";
import {useDispatch} from "react-redux"
import {login} from "../state/user/userAction"
import {Button, Col, Container, Form, Row}from "react-bootstrap"
export default function Entry(){
    const [name, setName] = useState("")
    const dispatch = useDispatch()
    const onClick = (e)=>{
        dispatch(login(name))
        e.preventDefault()
    }
    return (<Container>
        <Row><Col md="auto">
            <Form>
                <Form.Group className="mb-3" controlId="formBasicPassword">
                    <Form.Label>Name: </Form.Label>
                    <Form.Control type="text" value={name} 
                    onChange={(e)=>setName(e.target.value)}/>
                    <Form.Text className="text-muted"> Alphanumerical Characters Only</Form.Text>
                </Form.Group>
                <Button onClick={onClick} variant="primary">Login/Register</Button>
            </Form>
        </Col></Row>
    </Container>)
}