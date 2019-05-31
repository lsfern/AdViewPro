/**
 * @file 横幅广告
 * @date 2019/5/30 9:07
 * @author ZWW
 * @lastModify ZWW 2019/5/30 9:07
 */
import React, {Component} from 'react';
import {Dimensions, StyleSheet} from 'react-native';
import {RCTBannerView} from '../rct'

const {width, height} = Dimensions.get('window');

export default class BannerPage extends Component<Props> {
    constructor(props) {
        super(props);
        this.state = {
            isGetBanner: false,
        }
    }
    componentDidMount(): void {
        this.setState({isGetBanner:true})
    }
    componentWillUnmount(): void {
        this.setState({isGetBanner:false})
    }

    render() {
        return (
            <RCTBannerView style={{width: width, height: 200}} isGetBanner={this.state.isGetBanner}
                           onBannerClose={()=>alert('banner广告关闭')}
            />
        );
    }
}
