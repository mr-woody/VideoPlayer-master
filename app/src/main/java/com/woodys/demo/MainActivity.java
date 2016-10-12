package com.woodys.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.player.media.IjkVideoView;
import com.player.player.IjkViderPlayer;
import com.player.utils.PlayerManage;

import tv.danmaku.ijk.media.player.IMediaPlayer;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "viderPlayer";

    private final String url1 = "http://svideo.spriteapp.com/video/2016/0703/7b5bc740-4134-11e6-ac2b-d4ae5296039d_wpd.mp4";
    //这个地址是错误的
    private final String url2 = "http://weibo.com/p/23044451f0e5c4b762b9e1aa49c3091eea4d94";
    private IjkViderPlayer viderPlayer;

    private IjkViderPlayer viderPlayer2;
    public FrameLayout videoLayout;
    public ImageView videoCover;
    public ImageView videPlay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viderPlayer = (IjkViderPlayer) findViewById(R.id.ijk_videoplayer);

        videoLayout = (FrameLayout) findViewById(R.id.fl_video);
        videoCover = (ImageView) findViewById(R.id.iv_video_cover);
        videPlay = (ImageView) findViewById(R.id.iv_video_play);

        initPlayer();
        initPlayer2();


        videPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viderPlayer2.isPlaying()) {
                    return;
                }
                videoLayout.removeAllViews();
                videoLayout.addView(viderPlayer2);
                videoLayout.setVisibility(View.VISIBLE);
                viderPlayer2.playVideo(url1, "视频地址");
            }
        });

    }

    private void initPlayer() {
        // init player

        //初始化相关参数(必须放在Play前面)
        viderPlayer.setIsNeedBatteryListen(true);
        viderPlayer.setIsNeedNetChangeListen(true);

        viderPlayer.setOnStartListener(new IjkViderPlayer.OnStartListener() {
            @Override
            public void onStart(IjkVideoView ijkVideoView) {
                viderPlayer.playVideo(url1, "视频地址");
            }
        });


/*

        //播放完成监听
        mnViderPlayer.setOnCompletionListener(new MNViderPlayerNew.OnCompletionListener() {

            @Override
            public void onCompletion(IMediaPlayer mediaPlayer) {
                Log.i(TAG, "播放完成----");
            }
        });
*/

        //网络监听
        viderPlayer.setOnNetChangeListener(new IjkViderPlayer.OnNetChangeListener() {
            @Override
            public void onWifi(IjkVideoView mediaPlayer) {
            }

            @Override
            public void onMobile(IjkVideoView mediaPlayer) {
                Toast.makeText(MainActivity.this, "请注意,当前网络状态切换为3G/4G网络", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNoAvailable(IjkVideoView mediaPlayer) {
                Toast.makeText(MainActivity.this, "当前网络不可用,检查网络设置", Toast.LENGTH_LONG).show();
            }
        });

    }


    /**
     * 初始化播放器
     */
    private void initPlayer2() {
        viderPlayer2 = PlayerManage.getSuperManage().initialize(this);
        // init player
        //初始化相关参数(必须放在Play前面)
        viderPlayer2.setIsNeedBatteryListen(true);
        viderPlayer2.setIsNeedNetChangeListen(true);

        //播放完成监听
        viderPlayer2.setOnCompletionListener(new IjkViderPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(IMediaPlayer mediaPlayer) {
                ViewGroup view = (ViewGroup) viderPlayer2.getParent();//找到videoitemview的父类，然后remove
                if (view != null && view.getChildCount() > 0) {
                    view.removeAllViews();
                    view.setVisibility(View.INVISIBLE);
                }
            }
        });

        //网络监听
        viderPlayer2.setOnNetChangeListener(new IjkViderPlayer.OnNetChangeListener() {
            @Override
            public void onWifi(IjkVideoView mediaPlayer) {
            }

            @Override
            public void onMobile(IjkVideoView mediaPlayer) {
                Toast.makeText(MainActivity.this, "请注意,当前网络状态切换为3G/4G网络", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNoAvailable(IjkVideoView mediaPlayer) {
                Toast.makeText(MainActivity.this, "当前网络不可用,检查网络设置", Toast.LENGTH_LONG).show();
            }
        });

    }

    public void btn01(View view) {
        viderPlayer.playVideo(url1, "视频地址");
        viderPlayer2.playVideo(url1, "视频地址");
    }

    public void btn02(View view) {
        viderPlayer.playVideo(url2, "错误连接！");
        viderPlayer2.playVideo(url2, "错误连接！");

    }


    @Override
    public void onBackPressed() {
        if (viderPlayer.isFullScreen()) {
            viderPlayer.setOrientationPortrait();
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        viderPlayer.pauseVideo();
        viderPlayer2.pauseVideo();
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        //一定要记得销毁View
        if(viderPlayer != null){
            viderPlayer.destroyVideo();
            viderPlayer = null;
        }
        if(viderPlayer2 != null){
            viderPlayer2.destroyVideo();
            viderPlayer2 = null;
        }
        super.onDestroy();
    }
}
