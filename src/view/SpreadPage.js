/**
 * @file 开屏广告
 * @date 2019/5/30 9:07
 * @author ZWW
 * @lastModify ZWW 2019/5/30 9:07
 */
import React, {Component} from 'react';
import {Dimensions, View, Text} from 'react-native';
import {RCTSpreadView} from '../rct'
import {Actions} from "react-native-router-flux";

const {width, height} = Dimensions.get('window');

export default class SpreadPage extends Component<Props> {
    constructor(props) {
        super(props);
        this.state = {
            isGetSpread: false,
        }
    }

    componentDidMount(): void {
        this.setState({isGetSpread: true})
    }

    componentWillUnmount(): void {
        this.setState({isGetSpread: false})
    }

    render() {
        return (
            <RCTSpreadView style={{width: width, height: height}} isGetSpread={this.state.isGetSpread}
                           onSpreadClose={() => Actions.reset('homePage')}
            />
        );
    }
}
