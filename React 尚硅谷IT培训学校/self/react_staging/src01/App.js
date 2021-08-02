import React from 'react'
import Welcome from './components/Welcome/Welcome.js'
import Hello from './components/Hello/Hello.js'

export default class App extends React.Component{
    render(){
        return (
            <div>
                <Hello />
                <Welcome />
            </div>
        )
    }
}