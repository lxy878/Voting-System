import React, {useState} from "react";
import {useDispatch} from "react-redux"
import {login} from "../state/user/userAction"
export default function Entry(){
    const [name, setName] = useState("")
    const dispatch = useDispatch()
    const onClick = (e)=>{
        dispatch(login(name))
        e.preventDefault()
    }
    return (<>
        <div>Name: <input type="text" value={name} onChange={(e)=>setName(e.target.value)} placeholder="Enter alphanumerical characters"/></div>
        <div><input type="button" value="Enter" onClick={onClick}/></div>
    </>)
}