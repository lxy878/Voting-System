import { configureStore } from '@reduxjs/toolkit'
import {applyMiddleware} from "redux"
import thunk from "redux-thunk"
import userReducer from './user/userReducer'
let logger = ()=>(next)=>(action)=>{
    next(action)
}

export default configureStore(
  {reducer: {userReducer}},
  {},
  applyMiddleware(logger, thunk)
)