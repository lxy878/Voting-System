import axios from "axios";

export function saveQuestion(data){
    return function(dispatch){
        axios.post('/question/create', 
        JSON.stringify(data),
        {headers: {'Content-Type':'application/json'}})
        .then((resp)=>{
            console.log(resp.data)
        }).catch(err=>{
            console.log(err.response.data)
        })
    }
}