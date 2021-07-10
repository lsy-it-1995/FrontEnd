import './App.css';
import React from 'react';
import Jobs from './Jobs';

const mockjob = [
  {title: "software developer", company: "google"},
  {title: "software s developer", company: "abc"},
  {title: "software ss developer", company: "def"}
]

const JOB_API_URL = "http://localhost:3001/jobs";

async function fetchjob(updateCb){
  const res = await fetch(JOB_API_URL);
  const json = await res.json();
  updateCb(json);
}


function App() {

  const [joblist, updatejobs] = React.useState([]);

  React.useEffect(()=>{
      fetchjob(updatejobs);
  },[])

  return (
    <div className="App">
      <Jobs jobs={joblist} />
    </div>
  );
}

export default App;
