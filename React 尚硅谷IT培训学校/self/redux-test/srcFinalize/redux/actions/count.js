import {INCREMENT, DECREMENT} from '../constant'
export const Increment = data => ({type:INCREMENT, data})
export const Decrement = data => ({type:DECREMENT, data})
export const IncrementAsync = (data, time) => {
    return (dispatch) => {
        setTimeout(()=>{
            dispatch(Increment(data))
        }, time)
    }
}