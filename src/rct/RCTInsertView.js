/**
 * @file RCTInsertView
 * @date 2019/5/30 21:05
 * @author ZWW
 * @lastModify ZWW 2019/5/30 21:05
 */
import React, {PureComponent} from 'react';
import {Dimensions, requireNativeComponent} from 'react-native';

const AdInsertView = requireNativeComponent('RCTAdInsertView');
export default class RCTInsertView extends PureComponent {
    render(): React.ReactNode {
        return <AdInsertView style={{width: Dimensions.get('window').width, height: 200}}
                             {...this.props}/>;
    }
}
