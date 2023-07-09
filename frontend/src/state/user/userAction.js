import axios from 'axios'
export function login(name){

    return function(dispatch){
        axios.post(`/user/entry`, 
        JSON.stringify({name}), 
        {headers:{"Content-Type": "application/json"}})
        .then((resp)=>{
            dispatch({type: 'Login', user: resp.data})
        })
        .catch((err)=>{
            let message = err.response.data
            alert(message)
            console.log(message)
        })
    }
}