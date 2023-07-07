import axios from "axios";

export function saveQuestion(data){
    return function(dispatch){
        axios.post('/question/create', 
        JSON.stringify(data),
        {headers: {'Content-Type':'application/json'}})
        .then((resp)=>{
            console.log(resp.data)
            dispatch(getAllQuestions('/'+data.userId))
        }).catch(err=>{
            console.log(err.response.data)
        })
    }
}

export function getAllQuestions(uid=""){
    return function(dispatch){
        axios.get(`/question/all${uid}`)
        .then((resp)=>{
            console.log(resp.data)
            const questions = resp.data
            dispatch({type: 'GetAll', questions})
            
        }).catch(err=>{
            console.log(err.response.data)
        })
    }
}