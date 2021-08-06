import React, { Component } from 'react'
import {withRouter} from 'react-router-dom'

class header extends Component {
    back = () =>{
        this.props.history.goBack()
    }
    forward = () =>{
        this.props.history.goForward()
    }
    go = () =>{
        this.props.history.go(2)
    }
    render() {
        return (
            <div>
                <div className="page-header"><h2>React Router Demo</h2></div>
                <button onClick={this.back}>Back</button>&nbsp;
                <button onClick={this.forward}>Forward</button>&nbsp;
                <button onClick={this.go}>Go</button>&nbsp;
            </div>
        )
    }
}
export default withRouter(header)