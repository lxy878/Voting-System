import './App.css';
import React from "react"
import {Route, BrowserRouter as Router, Routes} from "react-router-dom"
import Entry from './component/entry';
import Home from "./component/home"
import Checker from './component/checker';
import QuestionForm from './component/questionForm'
import Voting from './component/voting'
import ViewVotes from './component/viewVotes'
function App() {
  return (
    <Router>
      <Checker/>
      <Routes>
        <Route path='/entry' element={<Entry/>}/>
        <Route path='/' element={<Home/>}/>
        <Route path='/addQuestion' element={<QuestionForm />}/>
        <Route path='/goVote' element={<Voting/>}/>
        <Route path='/viewVotes' element={<ViewVotes/>}/>
      </Routes>
    </Router>
  );
}

export default App;
