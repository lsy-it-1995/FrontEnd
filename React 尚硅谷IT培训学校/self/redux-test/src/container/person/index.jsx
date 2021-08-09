import React, { Component } from 'react'
import {nanoid} from 'nanoid'
export default class person extends Component {
    addPerson = () =>{
        const name = this.nameNode.value
        const age = this.ageNode.value
        const personObj = {id:nanoid(),name, age}
        console.log(personObj)
    }
    render() {
        return (
            <div>
                <input ref={c=>this.nameNode = c}type="text" placeholder="name" />
                <input ref={c=>this.ageNode = c}type="text" placeholder="age" />
                <button onClick={this.addPerson}>insert</button> 
                <ul>
                    <li>sdafdsf</li>
                </ul>
            </div>
        )
    }
}
