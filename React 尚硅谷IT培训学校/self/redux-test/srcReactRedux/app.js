import React, { Component } from 'react'
import Count from './container/count'
import store from './redux/store'

export default class app extends Component {
    render() {
        return (
            <div>
                <Count store={store}/>
            </div>
        )
    }
}
