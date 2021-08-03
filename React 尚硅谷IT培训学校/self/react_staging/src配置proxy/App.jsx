import React, { Component } from 'react'
import axios from 'axios'

export default class App extends Component {
    getStudent = () =>{
        axios.get('http://localhost:3000/api1/students').then(
            response =>{console.log('success', response.data);},
            error => {console.log('fail', error);}
        )
    }
    getCar = () =>{
        axios.get('http://localhost:3000/api2/cars').then(
            response =>{console.log('success', response.data);},
            error => {console.log('fail', error);}
        )
    }
    render() {
        return (
            <div>
                <button onClick={this.getStudent}>Click for student</button>
                <button onClick={this.getCar}>Click for car</button>
            </div>
        )
    }
}
