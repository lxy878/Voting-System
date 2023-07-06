import axios from 'axios'
import userReducer from './userReducer'
export function login(name){

    return function(dispatch){
        axios.post(`/user/entry`, 
        JSON.stringify({name}), 
        {headers:{"Content-Type": "application/json"}})
        .then((resp)=>{
            dispatch({type: 'Login', user: resp.data})
        })
        .catch((err)=>{
            console.log(err.response.data)
        })
    }
}