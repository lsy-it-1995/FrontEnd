import countUI from '../../component/Count'
import {connect} from 'react-redux' 
import {createIncrementAction,
        createDecrementAction, 
        createIncrementAsyncAction} from '../../redux/count_action'
function mapStateToProps(state){
    return {count:state}
}
function mapDispatchToProps(dispatch){
    return {
        increment:data=>dispatch(createIncrementAction(data)),
        decrement:data=>dispatch(createDecrementAction(data)),
        asyncIncrement:(data, time)=>dispatch(createIncrementAsyncAction(data, time))
    }
}
export default connect(mapStateToProps, mapDispatchToProps)(countUI)