import Button from './Button'
const Header = ({ title }) => {
    const onClick = () =>{
        console.log('click')
    }
    return (
        <header className="header">
            {/* inline */}
            {/* <h1 style={{color:'red', backgroundColor:'black'}}>{title}</h1> */}
            {/* <h1 style={headingStyle}>{title}</h1> */}
            <h1>{title}</h1>
            <Button color='green' text='Add' onClick={onClick}/>
        </header>
    )
}
//css in js
const headingStyle = {
    color:'red', 
    backgroundColor:'black'
}
export default Header
