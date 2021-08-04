import React, { Component } from 'react'
import PubSub from 'pubsub-js'
// import axios from 'axios'

export default class Search extends Component {
    
    search = async() =>{
        const{keyWordElement:{value:keyWord}}  = this
        PubSub.publish('atguigu', {isFirst:false, isLoading:true})

        // axios.get(`/api1/search/users?q=${keyWord}`).then(
        //     response =>{
        //         PubSub.publish('atguigu', {isLoading:false, users:response.data.items})
        //     },
        //     error => {
        //         PubSub.publish('atguigu', {isLoading:false, err:error.message})
        //     }
        // )
        // fetch(`/api1/search/users?q=${keyWord}`).then(
        //     response=>{
        //         console.log('success',);
        //         return response.json()
        //     },
        //     error =>{
        //         console.log('fail', error);
        //         return new Promise(()=>{})
        //     }
        // ).then(
        //     response => {console.log('success to receive', response);},
        //     err => {console.log('fail to receive', err);}
        // )
        try {
            const response = await fetch(`/api1/search/users?q=${keyWord}`)
            const data = await response.json()
            PubSub.publish('atguigu', {isLoading:false, users:data.items})
        } catch (error) {
            PubSub.publish('atguigu', {isLoading:false, err:error.message})
        }
    }
    render() {
        return (
            <section className="jumbotron">
            <h3 className="jumbotron-heading">Search Github Users</h3>
            <div>
                <input ref={c => this.keyWordElement = c}type="text" placeholder="enter the name you search"/>&nbsp;
                <button onClick={this.search}>Search</button>
            </div>
            </section>
        )
    }
}
