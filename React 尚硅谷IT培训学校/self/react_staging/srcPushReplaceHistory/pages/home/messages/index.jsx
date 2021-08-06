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
    showReplace = (id, title) =>{
        // this.props.history.replace(`/home/messages/details/${id}/${title}`)
        // this.props.history.replace(`/home/messages/details/?id=${id}&title=${title}`)
        this.props.history.replace(`/home/messages/details`,{id, title})
        
    }
    showPush = (id, title) =>{
        // this.props.history.push(`/home/messages/details/${id}/${title}`)
        // this.props.history.push(`/home/messages/details/?id=${id}&title=${title}`)
        this.props.history.push(`/home/messages/details`, {id, title})
    }
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
                                <Link to={{pathname:'/home/messages/details', state:{id:messageObj.id, title:messageObj.title}}}>{messageObj.title}</Link>
                                &nbsp;<button onClick={()=>this.showPush(messageObj.id, messageObj.title)}>push</button>
                                &nbsp;<button onClick={()=>this.showReplace(messageObj.id, messageObj.title)}>replace</button>
                                
                            </li>
                        )
                    })
                }
                </ul>
                <hr />
                {/* <Route path="/home/messages/details/:id/:title" component={Detail} /> */}
                {/* <Route path="/home/messages/details" component={Detail} /> */}
                <Route path="/home/messages/details" component={Detail} />
                <button onClick={this.back}>Back</button>&nbsp;
                <button onClick={this.forward}>Forward</button>&nbsp;
                <button onClick={this.go}>Go</button>&nbsp;
            </div>
        )
    }
}
