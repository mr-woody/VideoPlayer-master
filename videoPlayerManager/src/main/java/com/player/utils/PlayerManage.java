package com.player.utils;

import android.content.Context;

import com.player.player.IjkViderPlayer;

/**
 *
 * 类描述：获取唯一的视频控制器
 *
 * @author woody
 * @time 2016-10-07
 */
public class PlayerManage {
    public static PlayerManage videoPlayViewManage;
    private IjkViderPlayer videoPlayView;

    private PlayerManage() {

    }

    public static PlayerManage getSuperManage() {
        if (videoPlayViewManage == null) {
            videoPlayViewManage = new PlayerManage();
        }
        return videoPlayViewManage;
    }

    public IjkViderPlayer initialize(Context context) {
        if (videoPlayView == null) {
            videoPlayView = new IjkViderPlayer(context);
        }
        return videoPlayView;
    }
}
