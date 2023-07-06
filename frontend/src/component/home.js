import React from "react";
import {useSelector} from "react-redux"
import { NavLink } from "react-router-dom";
export default function Home(){
    const user = useSelector(state=>state.userReducer.user)
    return (<>
    <h2>Welcome, {user.name}</h2>
    <div><NavLink to="/addQuestion">Create new question</NavLink></div>
    <div><NavLink to="/goVote">View questions and vote</NavLink></div>
    <div><NavLink to='/viewVotes'>View results</NavLink></div>
    </>)
}