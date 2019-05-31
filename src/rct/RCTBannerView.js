/**
 * @file RCTBannerView
 * @date 2019/5/28 18:02
 * @author ZWW
 * @lastModify ZWW 2019/5/28 18:02
 */
import React, {PureComponent} from 'react';
import {Dimensions, requireNativeComponent} from 'react-native';

const AdBannerView = requireNativeComponent('RCTAdBannerView');
export default class RCTBannerView extends PureComponent {
    render(): React.ReactNode {
        return <AdBannerView style={{width: Dimensions.get('window').width, height: 200}}
                              {...this.props}/>;
    }
}
