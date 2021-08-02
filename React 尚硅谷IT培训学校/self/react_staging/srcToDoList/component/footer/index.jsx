import React, { Component } from 'react'
import './index.css'

export default class footer extends Component {
    handleCheckAll = (event) =>{
        this.props.checkAllBox(event.target.checked)
    }
    handleClearAllDone = () =>{
        this.props.clearAllDone()
    }
    render() {
        const {todos} = this.props
    
        const doneCount = todos.reduce((prev, cur)=> prev + (cur.done?1:0), 0)
        const total = todos.length
        return (
            <div>
                <div className="todo-footer">
                    <label>
                        <input type="checkbox" checked={doneCount === total && total !== 0? true: false} onChange={this.handleCheckAll}/>
                    </label>
                    <span>
                        <span>已完成{doneCount}</span> / 全部{total}
                    </span>
                    <button onClick={this.handleClearAllDone} className="btn btn-danger">清除已完成任务</button>
                </div>
            </div>
        )
    }x
}
