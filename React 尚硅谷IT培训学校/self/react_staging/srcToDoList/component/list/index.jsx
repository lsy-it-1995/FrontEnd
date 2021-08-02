import React, { Component } from 'react'
import Item from '../item'
import PropTypes from 'prop-types'
import './index.css'

export default class list extends Component {
    static propTypes = {
        todos:PropTypes.array.isRequired,
        updateToDo:PropTypes.func.isRequired,
        deleteToDo:PropTypes.func.isRequired
    }
    render() {
        const {todos, updateToDo, deleteToDo} = this.props
        return (
            <ul className="todo-main">
                {
                    todos.map(todo=>{
                        return <Item key={todo.id} {...todo} updateToDo={updateToDo} deleteToDo={deleteToDo}/>
                    })
                }
            </ul>
        )
    }
}
