import React, { Component } from 'react'
import Header from './component/header'
import List from './component/list'
import Footer from './component/footer'
import './App.css'

export default class App extends Component {
    state = {todos:[
        {id:'001', name:'eating', done:true},
        {id:'002', name:'sleeping', done:true},
        {id:'003', name:'coding', done:false},
    ]}
    addTodo = (todoObj) =>{
        const {todos} = this.state
        const newTodos = [todoObj, ...todos]
        this.setState({todos:newTodos})
    }
    updateToDo = (id, done) =>{
        const {todos} = this.state
        const newTodos = todos.map((todoObj) =>{
            if(todoObj.id === id){
                return {...todoObj, done}
            }else 
                return todoObj
        })
        this.setState({todos:newTodos})
    }
    deleteToDo = (id) =>{
        const {todos} = this.state
        const newTodos = todos.filter((todoObj) =>{
            return todoObj.id !== id
        })
        this.setState({todos:newTodos})
    }
    checkAllToDo = (done) =>{
        const {todos} = this.state
        const newToDos = todos.map((todoObj)=>{
            return {...todoObj, done}
        })
        this.setState({todos:newToDos})
    }
    clearAllDone = () =>{
        const {todos} = this.state
        const newTodo = todos.filter((todoObj)=>{
            return !todoObj.done
        })
        this.setState({todos:newTodo})
    }
    render() {
        const {todos} = this.state
        return (
            <div>
                <div className="todo-container">
                    <div className="todo-wrap">
                    <Header addTodo={this.addTodo}/>
                    <List todos={todos} updateToDo={this.updateToDo} deleteToDo={this.deleteToDo}/> 
                    <Footer todos={todos} checkAllBox={this.checkAllToDo} clearAllDone ={this.clearAllDone}/>
                    </div>
                </div>
            </div>
        )
    }
}
