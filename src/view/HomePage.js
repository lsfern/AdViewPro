/**
 * @file 首页
 * @date 2019/5/30 9:17
 * @author ZWW
 * @lastModify ZWW 2019/5/30 9:17
 */
import React, {Component} from 'react';
import {Button, Dimensions, StyleSheet, View} from 'react-native';
import {Actions} from "react-native-router-flux";

export default class HomePage extends Component<Props> {
    constructor(props) {
        super(props);
    }

    _getDiffMes = (type) => {
        if (type === 0) {
            Actions.bannerPage();
        }
        if (type === 1) {
            Actions.spreadPage();
        }
        if (type === 2) {
            Actions.insertPage();
        }
        if (type === 3) {
            Actions.videoPage();
        }
        if (type === 4) {
            Actions.nativePage();
        }
    };

    render() {
        return (
            <View style={{margin: 10}}>
                <View style={{marginTop: 10}}>
                    <Button
                        onPress={() => this._getDiffMes(0)}
                        title="获取横幅广告"
                        color="#841584"
                    />
                </View>
                <View style={{marginTop: 10}}>
                    <Button
                        onPress={() => this._getDiffMes(1)}
                        title="获取开屏广告"
                        color="#841584"
                    />
                </View>
                <View style={{marginTop: 10}}>
                    <Button
                        onPress={() => this._getDiffMes(2)}
                        title="获取插屏广告"
                        color="#841584"
                    />
                </View>
                <View style={{marginTop: 10}}>
                    <Button
                        onPress={() => this._getDiffMes(3)}
                        title="获取视频广告"
                        color="#841584"
                    />
                </View>
                <View style={{marginTop: 10}}>
                    <Button
                        onPress={() => this._getDiffMes(4)}
                        title="获取原生广告"
                        color="#841584"
                    />
                </View>
            </View>
        );

    }
}
