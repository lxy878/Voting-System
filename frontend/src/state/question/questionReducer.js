let init_state = {questions:[]}

export default function QuestionReducer(state=init_state, action){
    switch(action.type){
        case "GetAll":
            return {...state, questions: action.questions}
        default:
            return state
    }

}