/**
 * @file RCTSpreadView
 * @date 2019/5/29 18:32
 * @author ZWW
 * @lastModify ZWW 2019/5/29 18:32
 */
import React, {PureComponent} from 'react';
import {Dimensions, requireNativeComponent} from 'react-native';

const {width, height} = Dimensions.get('window');
const AdSpreadView = requireNativeComponent('RCTAdSpreadView');
export default class RCTSpreadView extends PureComponent {
    render(): React.ReactNode {
        return <AdSpreadView style={{width: width, height: height}}
                              {...this.props}/>;
    }
}
