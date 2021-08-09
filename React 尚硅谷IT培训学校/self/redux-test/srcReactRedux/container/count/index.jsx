import countUI from '../../component/Count'
import {connect} from 'react-redux' 
import {createIncrementAction,
        createDecrementAction, 
        createIncrementAsyncAction} from '../../redux/count_action'

export default connect(
    state => ({count:state}), 
    {
        increment:createIncrementAction,
        decrement:createDecrementAction,
        asyncIncrement:createIncrementAsyncAction 
    }
)(countUI)

