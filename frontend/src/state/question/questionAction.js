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
            let message = err.response.data
            alert(message)
            console.log(message)
        })
    }
}

export function saveVote(data){
    const uid = data.uid
    return function(dispatch){
        axios.post('/question/vote', 
        JSON.stringify(data),
        {headers: {'Content-Type':'application/json'}})
        .then((resp)=>{
            console.log(resp.data)
            dispatch(getAllNotVoted({uid}))
            dispatch(getAllVoted({uid}))
        }).catch(err=>{
            let message = err.response.data
            alert(message)
            console.log(message)
        })
    }
}

export function getAllNotVoted(data){
    return function(dispatch){
        axios.get(`/question/goVote/${data.uid}`)
        .then((resp)=>{
            const questions = resp.data 
            dispatch({type: 'GetAllNotVoted', questions})
        }).catch(err=>{
            let message = err.response.data
            alert(message)
            console.log(message)
        })
    
    }
}

export function getAllVoted(data){
    return function(dispatch){
        axios.get(`/question/voted/${data.uid}`)
        .then((resp)=>{
            const votedQuestions = resp.data 
            console.log("voted ",votedQuestions)
            dispatch({type: 'GetAllVoted', votedQuestions})
        }).catch(err=>{
            let message = err.response.data
            alert(message)
            console.log(message)
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
            let message = err.response.data
            alert(message)
            console.log(message)
        })
    }
}