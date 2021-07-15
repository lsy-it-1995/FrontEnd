import PropTypes from 'prop-types'
import Header from './components/Header'
// import React from 'react'
function App() {
  
  return (
    <div className="container">
      <Header />
    </div>
  );
}
Header.defaultProps = {
  title: 'Task Tracker',
}
// if you want to write in class
// class App extends React.Component{
//   render(){
//     return <h1>Hello from a class</h1>
//   }
// }
Header.propTypes = {
  title: PropTypes.string.isRequired,
}
export default App;