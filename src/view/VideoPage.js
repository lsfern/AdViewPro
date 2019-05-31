/**
 * @file 视频广告
 * @date 2019/5/30 9:16
 * @author ZWW
 * @lastModify ZWW 2019/5/30 9:16
 */
import React, {Component} from 'react';
import {Dimensions, StyleSheet, Text, View} from 'react-native';
import {RCTSpreadView, RCTVideoView} from '../rct'
import {Actions} from "react-native-router-flux";

const {width, height} = Dimensions.get('window');

export default class BannerPage extends Component<Props> {
    constructor(props) {
        super(props);
        this.state = {
            isGetVideo: false,
        }
    }

    componentDidMount(): void {
        this.setState({isGetVideo: true})
    }

    componentWillUnmount(): void {
        this.setState({isGetVideo: false})
    }

    render() {
        return (
            <View style={{flex:1,}}>
                <View style={{flex:1,alignItems: "center", justifyContent: "center"}}>
                    <Text style={{color: "#00f", fontSize: 20}}>视频广告</Text>
                </View>
                <RCTVideoView style={{width: width, height: 200}} isGetVideo={this.state.isGetVideo}
                />
            </View>

        );
    }
}
