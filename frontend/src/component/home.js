import React from "react";
import {useSelector} from "react-redux"
import { NavLink } from "react-router-dom";
import { Container, Navbar, Nav} from "react-bootstrap";
export default function Home(){
    const user = useSelector(state=>state.userReducer.user)
    return (<Container>
        <h2>Welcome, {user.name}</h2>
        <Navbar>
            <Nav className="flex-column">
                <Nav.Link href="."><NavLink to="/addQuestion">Create New Question</NavLink></Nav.Link>
                <Nav.Link href="."><NavLink to="/goVote">View Questions And Vote</NavLink></Nav.Link>
                <Nav.Link href="."><NavLink to='/viewVotes'>View Results</NavLink></Nav.Link>
            </Nav>
        </Navbar>
    </Container>)
}