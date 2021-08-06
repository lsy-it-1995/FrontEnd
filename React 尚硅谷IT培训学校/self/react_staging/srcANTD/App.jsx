import React, { Component } from 'react'
import {Button} from 'antd'
import {WechatOutlined} from '@ant-design/icons'
export default class App extends Component {
    render() {
        return (        
            <div>
                <Button type="primary">Primary button</Button> 
                <WechatOutlined />
            </div>
        )
    }
}