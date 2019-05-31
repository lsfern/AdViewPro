/**
 * @file 路由管理
 * @date 2019/5/31 11:07
 * @author ZWW
 * @lastModify ZWW 2019/5/31 11:07
 */
"use strict";
import React, {Component} from "react";
import {Actions, Router, Scene} from "react-native-router-flux";
import {AppState} from "react-native";
import HomePage from './view/HomePage';
import BannerPage from './view/BannerPage';
import SpreadPage from './view/SpreadPage';
import InsertPage from './view/InsertPage';
import VideoPage from './view/VideoPage';
import NativePage from './view/NativePage';
class routers extends Component {
    constructor(props) {
        super(props);
        this.state = {
            currentAppState: AppState.currentState,
        }
    }
    shouldComponentUpdate() {
        return false;
    }

    /**
     * Android 返回键
     * @returns {boolean} - true：不可点击
     */
    backAndroidHandler() {
        const scene = Actions.currentScene; //获取当前路由
        if(scene == 'spreadPage' || scene =='spreadPage'){//屏蔽开屏广告Back键
            return true;
        }
    }

    render() {
        return (
            <Router backAndroidHandler={this.backAndroidHandler}>
                <Scene key="root" hideNavBar >
                    <Scene key="homePage" component={HomePage} hideNavBar/>
                    <Scene key="bannerPage" component={BannerPage} hideNavBar/>
                    <Scene key="spreadPage" component={SpreadPage} hideNavBar/>
                    <Scene key="insertPage" component={InsertPage} hideNavBar/>
                    <Scene key="videoPage" component={VideoPage} hideNavBar/>
                    <Scene key="nativePage" component={NativePage} hideNavBar/>
                </Scene>
            </Router>
        );
    }
}
export default routers
