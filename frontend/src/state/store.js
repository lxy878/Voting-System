import { configureStore } from '@reduxjs/toolkit'
import {applyMiddleware} from "redux"
import thunk from "redux-thunk"
import userReducer from './user/userReducer'
import questionReducer from './question/questionReducer'
let logger = ()=>(next)=>(action)=>{
    next(action)
}

export default configureStore(
  {reducer: {userReducer, questionReducer}},
  {},
  applyMiddleware(logger, thunk)
)