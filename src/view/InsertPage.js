/**
 * @file 插屏广告
 * @date 2019/5/30 9:07
 * @author ZWW
 * @lastModify ZWW 2019/5/30 9:07
 */
import React, {Component} from 'react';
import {Dimensions, Text, View} from 'react-native';
import {RCTInsertView} from '../rct'

const {width, height} = Dimensions.get('window');

export default class InsertPage extends Component<Props> {
    constructor(props) {
        super(props);
        this.state = {
            isGetInsert: false,
        }
    }
    componentDidMount(): void {
        this.setState({isGetInsert:true})
    }
    componentWillUnmount(): void {
        this.setState({isGetInsert:false})
    }

    render() {
        return (
            <View style={{flex:1,}}>
                <View style={{flex:1,alignItems: "center", justifyContent: "center"}}>
                    <Text style={{color: "#00f", fontSize: 20}}>插屏广告</Text>
                </View>
                <RCTInsertView style={{width: width, height: 300}} isGetInsert={this.state.isGetInsert}
                               onInsertClose={()=>alert('插屏广告关闭')}
                />
            </View>

        );
    }
}
