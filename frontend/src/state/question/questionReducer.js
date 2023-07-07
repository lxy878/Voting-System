
let init_state = {questions:[], votedQuestions: []}

export default function QuestionReducer(state=init_state, action){
    switch(action.type){
        case "GetAllNotVoted":
        case "GetAll":
            return {...state, questions: action.questions}
        case "GetAllVoted":
            return {...state, votedQuestions: action.votedQuestions}
        default:
            return state
    }

}