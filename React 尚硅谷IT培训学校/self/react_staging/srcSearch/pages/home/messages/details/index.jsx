import React, { Component } from 'react'
import qs from 'querystring'
const DetailData = [
    {id:'01', content:'hi'},
    {id:'02', content:'hi2'},
    {id:'03', content:'hi3'}
]
export default class details extends Component {
    render() {
        // const {id, title} = this.props.match.params
        const {search} = this.props.location
        const {id, title} = qs.parse(search.slice(1))
        const findResult = DetailData.find((dataObj) =>{
            return dataObj.id === id
        })
        return (
            <ul>
                <li>ID: {id}</li>
                <li>TItle: {title}</li>
                <li>Content: {findResult.content}</li>
            </ul>
        )
    }
}
