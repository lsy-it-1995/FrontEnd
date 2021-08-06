import React, { Component } from 'react'
import Detail from './details'
import {Link, Route} from 'react-router-dom'
export default class messages extends Component {
    state = {
        messageArr:[
            {id:'01', title:'message01'},
            {id:'02', title:'message02'},
            {id:'03', title:'message03'},
        ]
    }
   
    render() {
        const {messageArr} = this.state
        return (
            <div>
                <ul>
                {
                    messageArr.map((messageObj)=>{
                        return (
                            <li key={messageObj.id}>
                                {/* <Link to={`/home/messages/details/${messageObj.id}/${messageObj.title}`}>{messageObj.title}</Link> */}
                                {/* <Link to={`/home/messages/details/?id=${messageObj.id}&title=${messageObj.title}`}>{messageObj.title}</Link> */}
                                <Link replace to={{pathname:'/home/messages/details', state:{id:messageObj.id, title:messageObj.title}}}>{messageObj.title}</Link>
                            </li>
                        )
                    })
                }
                </ul>
                <hr />
                {/* <Route path="/home/messages/details/:id/:title" component={Detail} /> */}
                {/* <Route path="/home/messages/details" component={Detail} /> */}
                <Route path="/home/messages/details" component={Detail} />
               
            </div>
        )
    }
}
