import React, { Component } from 'react'
import {Route, Switch, Redirect} from 'react-router-dom'
import Messages from './messages'
import News from './news'
import MyNavLink from '../../component/MyNavLink'

export default class home extends Component {
    render() {
        return (
            <div>
                <h2>Home组件内容</h2>
                <div>
                    <ul className="nav nav-tabs">
                        <li>
                            <MyNavLink replace to="/home/news">News</MyNavLink>
                        </li>
                        <li>
                            <MyNavLink replace to="/home/messages">Message</MyNavLink>
                        </li>
                    </ul>
                    <Switch>
                        <Route path="/home/news" component={News} />
                        <Route path="/home/messages" component={Messages} />
                        <Redirect to="/home/news" />
                    </Switch>
                </div>
            </div>
        )
    }
}
