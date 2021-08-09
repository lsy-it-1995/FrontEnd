import React, { Component } from 'react'
import Count from './container/count'
import Person from './container/person'
export default class app extends Component {
    render() {
        return (
            <div>
                <Count/>
                <hr />
                <Person />
            </div>
        )
    }
}
