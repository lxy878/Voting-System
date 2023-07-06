let initial_state = {user: {
    id: -1,
    name: ""
}}

const UserReducer = (state=initial_state, action)=>{
    switch(action.type){
        case 'Login':
            return {...state, user: action.user}
        default:
            return state
    }
}

export default UserReducer