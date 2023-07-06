import React, {useEffect} from "react";
import { useNavigate } from "react-router-dom";
import {useSelector} from "react-redux"
export default function Checker(){
    const navigate =  useNavigate()
    let user = useSelector((state)=> state.userReducer.user)
    
    useEffect(()=>{
        if(user.name === "") navigate("/entry")
        else navigate('/')
    },[user])
    return (<></>)
}