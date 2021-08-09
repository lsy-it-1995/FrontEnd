import React, { Component } from 'react'
import {nanoid} from 'nanoid'
import { connect } from 'react-redux'
import {addPerson} from '../../redux/actions/person'
class person extends Component {
    addPerson = () =>{
        const name = this.nameNode.value
        const age = this.ageNode.value*1
        const personObj = {id:nanoid(),name, age}
        this.props.addPerson(personObj)
        this.nameNode.value=''
        this.ageNode.value=''
    }
    render() {
        return (
            <div>
                <h2>The above sum is {this.props.count}</h2>
                <input ref={c=>this.nameNode = c}type="text" placeholder="name" />
                <input ref={c=>this.ageNode = c}type="text" placeholder="age" />
                <button onClick={this.addPerson}>insert</button> 
                <ul>
                    {
                        this.props.personArr.map((obj)=>{
                            return <li key={obj.id}>{obj.name}---{obj.age}</li>
                        })
                    }
                </ul>
            </div>
        )
    }
}

export default connect(
    state => ({
        personArr: state.persons, 
        count: state.count
    }),{addPerson}
)(person)