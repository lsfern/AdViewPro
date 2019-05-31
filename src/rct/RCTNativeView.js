/**
 * @file RCTNativeView
 * @date 2019/5/30 19:03
 * @author ZWW
 * @lastModify ZWW 2019/5/30 19:03
 */
import React, {PureComponent} from 'react';
import {Dimensions, requireNativeComponent} from 'react-native';

const AdNativeView = requireNativeComponent('RCTAdNativeView');
export default class RCTVideoView extends PureComponent {
    render(): React.ReactNode {
        return <AdNativeView style={{width: Dimensions.get('window').width, height: 200}}
                            {...this.props}/>;
    }
}
