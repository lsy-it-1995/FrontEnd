import React, { Component } from 'react'
import axios from 'axios'

export default class Search extends Component {
    
    search = () =>{
        // const {value} = this.keyWordElement
        // console.log(value) 
        const{keyWordElement:{value:keyWord}}  = this
        
        this.props.UpdateAppState({isFirst:false, isLoading:true})

        axios.get(`/api1/search/users?q=${keyWord}`).then(
            response =>{
                this.props.UpdateAppState({isLoading:false, users:response.data.items})
            },
            error => {
                this.props.UpdateAppState({isLoading:false, err:error.message})
            }
        )
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
