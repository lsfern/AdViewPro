/**
 * @file 原生广告
 * @date 2019/5/30 16:04
 * @author ZWW
 * @lastModify ZWW 2019/5/30 16:04
 */
import React, {Component} from 'react';
import {Dimensions, Image, NativeModules, Text, TouchableOpacity, View} from 'react-native';
import {RCTNativeView} from '../rct'

const {width, height} = Dimensions.get('window');
const posId = "NATIVEchzdqc0qachn";
let adNativeModule = NativeModules.AdNativeModule;
export default class NativePage extends Component<Props> {
    constructor(props) {
        super(props);
        this.state = {
            isGetNative: false,
            adId: null,
            data: {},
        }
    }

    componentDidMount(): void {
        this.setState({isGetNative: true})
    }

    componentWillUnmount(): void {
        this.setState({isGetNative: false});
        //释放原生广告强引用
        adNativeModule.resetAdViewNative();
    }

    render() {
        return (
            <View style={{flex: 1,}}>
                {
                    this.state.data ? <View style={{flex: 1, alignItems: "center", justifyContent: "center"}}>
                        <TouchableOpacity onPress={() => adNativeModule.report(this.state.data.adId)}>
                            <View style={{flexDirection: 'row', alignItems: 'center'}}>
                                <Image resizeMethod={'scale'} resizeMode={'contain'} style={{width: 100, height: 100}}
                                       source={{uri: this.state.data.adIcon}}/>
                                <View>
                                    <Text style={{color: "#f00", fontSize: 20}}>{this.state.data.title}</Text>
                                    <Text style={{color: "#00f", fontSize: 20}}>{this.state.data.description}</Text>
                                </View>
                            </View>
                        </TouchableOpacity>
                        <Image resizeMethod={'scale'} resizeMode={'contain'} style={{width: width, height: 200}}
                               source={{uri: this.state.data.adImage}}/>
                    </View> : <Text style={{color: "#00f", fontSize: 20}}>正在加载中...</Text>
                }

                <RCTNativeView style={{width: width, height: 300}} isGetNative={this.state.isGetNative}
                               posId={posId}
                               onGetDataSuccess={data => this.setState({data: data.nativeEvent})}
                />
            </View>

        );
    }
}
