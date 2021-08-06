import React, { Component } from 'react'
import {Route, Redirect, Switch} from 'react-router-dom'
import About from './pages/about'
import Home from './pages/home'
import MyNavLink from './component/MyNavLink'
import Header from './component/header'
export default class App extends Component {
    render() {
        return (
            <div>
                <div>
                    <div className="row">
                    <div className="col-xs-offset-2 col-xs-8">
                        <Header />
                    </div>
                    </div>
                    <div className="row">
                    <div className="col-xs-2 col-xs-offset-2">
                        <div className="list-group">
                        {/* <a className="list-group-item" href="./about.html">About</a>
                        <a className="list-group-item active" href="./home.html">Home</a> */}
                        <MyNavLink to="/about">About</MyNavLink>
                        <MyNavLink to="/home">Home</MyNavLink>
                        </div>
                    </div>
                    <div className="col-xs-6">
                        <div className="panel">
                        <div className="panel-body">
                            <Switch>
                                <Route path="/about" component={About}/>
                                <Route path="/home" component={Home}/>
                                <Redirect to="/about"/>
                            </Switch>
                        </div>
                        </div>
                    </div>
                    </div>
                </div>
            </div>
        )
    }
}