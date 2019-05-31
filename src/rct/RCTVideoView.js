/**
 * @file RCTVideoView
 * @date 2019/5/30 21:19
 * @author ZWW
 * @lastModify ZWW 2019/5/30 21:19
 */
import React, {PureComponent} from 'react';
import {Dimensions, requireNativeComponent} from 'react-native';

const AdBannerView = requireNativeComponent('RCTAdVideoView');
export default class RCTVideoView extends PureComponent {
    render(): React.ReactNode {
        return <AdBannerView style={{width: Dimensions.get('window').width, height: 200}}
                            {...this.props}/>;
    }
}
