import React, { Component } from 'react'
import axios from 'axios'

export default class App extends Component {
    getStudent = () =>{
        axios.get('http://localhost:5000/students').then(
            response =>{console.log('success', response.data);},
            error => {console.log('fail', error);}
        )
    }
    render() {
        return (
            <div>
                <button onClick={this.getStudent}>Click for student</button>
            </div>
        )
    }
}
