// import countUI from '../../component/Count'
import React, {Component} from 'react'
import {connect} from 'react-redux' 
import {Increment,
        IncrementAsync, 
        Decrement} from '../../redux/actions/count'

class Count extends Component {

    increment = ()=>{
        const {value} = this.selectNumber
        this.props.Increment(value*1)
    }
    decrement = () =>{
        const {value} = this.selectNumber
        this.props.Decrement(value*1)
    }
    incrementOdd = () =>{
        const {value} = this.selectNumber
        if(this.props.count %2 !== 0){
            this.props.Increment(value*1)
        }
    }
    incrementAsync = () =>{
        const {value} = this.selectNumber
        this.props.AsyncIncrement(value*1, 500)
    }
    render() {
        return (
            <div>
                <h4>Current value:{this.props.count} and person counts it {this.props.personsCount}</h4>
                <select ref={c=>this.selectNumber=c}>
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                </select> &nbsp;
                <button onClick={this.increment}>+</button>&nbsp;
                <button onClick={this.decrement}>-</button>&nbsp;
                <button onClick={this.incrementOdd}>add if odd</button>&nbsp;
                <button onClick={this.incrementAsync}>add async</button>&nbsp;
            </div>
        )
    }
}
        
export default connect(
    state => ({
        count:state.count,
        personsCount:state.persons.length}), 
    {
        Increment,
        Decrement,
        IncrementAsync 
    }
)(Count)

