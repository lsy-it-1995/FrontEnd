
import Task from './Task'
const Tasks = ({ tasks, onDelete, onToggle }) => {
    
    return (
        <div>
            {/* key={task.id}  to key={index} same thing*/}
            {tasks.map((task, index) => (
                <Task key={task.id} 
                      task={task} 
                      onDelete={onDelete} 
                      onToggle={onToggle}/>))}
        </div>
    )
}

export default Tasks
