import React, { Component } from 'react'
import Search from './component/Search'
import List from './component/List'

export default class App extends Component {
    state = {
        users:[],
        isFirst: true,
        isLoading: false,
        err: ''
    }

    UpdateAppState = (stateObj) =>{
        this.setState(stateObj)
    }
    render() {
        return (
            <div>
                <div className="container">
                    <Search UpdateAppState={this.UpdateAppState}/>
                    <List {...this.state}/>
                </div>
            </div>
        )
    }
}
