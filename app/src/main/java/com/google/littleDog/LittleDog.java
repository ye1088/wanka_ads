package com.google.littleDog;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.google.utils.DoMain;
import com.huanju.sdk.ad.ssp_sdk.HjAdManager;
import com.huanju.sdk.ad.ssp_sdk.listener.HjAdListener;
import com.umeng.analytics.MobclickAgent;

/**
 * Created by appchina on 2017/2/21.
 */

public class LittleDog {

    /**
     * 玩咖通用ID

     应用 179
     横幅 1990
     插屏 1991
     开屏 1992
     */

    static final String ADPID = "1705100002";   // 广告id
    static final String INTER_ID = "1991";   // 广告id
    static final String BANNER_ID = "1990";   // 广告id
    static final boolean ASK_SPLASH_AD = true; //  是否要有开屏广告
    static final boolean ASK_INTER_AD = true;   // 是否要有插屏广告
    static boolean ASK_BANNER_AD = true;  // banner 广告是不是已经显示了


    private static Context mContext;

    public static void onCreate(Context context){
        mContext = context;
        DoMain.initPara(context);
//        init(context);
        init_ad(context);
//        vide_o_a_ds(context);
//        show_vide0("aaaa");
//        showSplash(context);
    }

    public static void init(Context context) {
    }

    private static long old_time;

    // 视频广告 初始化
    public static void vide_o_a_ds(final Context context){

        old_time = System.currentTimeMillis();



    }

    private static boolean vide0_first = true;
    private static boolean isVide0Showed = true;
    // 展示 视频  这里面的 参数 和判断是针对 我的世界的,不同的游戏,可能要做不同的处理
    public static void show_vide0(String url_flag){

        if (vide0_first){
            vide0_first = false;
            return;
        }


        if (!"resource_packs/skins/models/mobs.json".equals(url_flag)) return;
        if (!((System.currentTimeMillis()-old_time)>18000) ) return;
        if (!isVide0Showed){
            // 播放视频
            isVide0Showed = true;
        }else {
            isVide0Showed = false;
        }



    }
//
//    /**
//     * 开屏广告
//     * @param context
//     */
//    public static void showSplash(Context context){
//        if (ASK_SPLASH_AD){
//            Log.e(ADPID,"ASK_SPLASH_AD");
//            FrameLayout flayout = new FrameLayout(context);
//            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
//            ((Activity)context).addContentView(flayout,layoutParams);
//            final SplashAdView splashAD = new SplashAdView(context, flayout, ADPID,
//                    new SplashAdListener() {
//                        @Override
//                        public void onSplashAdShow() {
//                            isSplashShowed = true;
//                            MobclickAgent.onEvent(mContext,"splash_show");
//                        }
//
//                        @Override
//                        public void onSplashAdClick() {
//                            MobclickAgent.onEvent(mContext,"splash_click");
//                        }
//
//                        @Override
//                        public void onSplashAdFailed(String s) {
//                            Log.e(ADPID,s);
//                        }
//
//                        @Override
//                        public void onSplashAdDismiss() {
//
//                        }
//                    });
//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    splashHint(splashAD);
//                }
//            },10000);
//
//        }
//    }


    public static void init_ad(Context context){



//        initBanner((Activity) context);



    }


    /**
     *  banner 广告
     * @param context
     */
    public static void initBanner(Activity context){
        /************** 将以下内容添加到onCreate方法中 **********************/
//        LinearLayout view = new LinearLayout(this);
        // ASK_BANNER_AD 这个标志位 来控制banner广告要不要显示
        if (ASK_BANNER_AD){
            return;
        }
        ASK_BANNER_AD = true;

    }
    public static void onResume(final Context context){

        MobclickAgent.onResume(context);
        initBanner((Activity) context);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                show_ad(context);

            }
        },7000);

    }

    public static void onPause(Context context){

        MobclickAgent.onPause(context);
    }


    public static void show_ad(Context context){

        HjAdManager.showInsertAdView((Activity) context, DoMain.initial_ads_id,new HjAdListener() {

            @Override
            public void onDisplayAd() {
                MobclickAgent.onEvent(mContext,"inter_show");

                Log.d("hjwanka", "插屏展示!");
            }

            @Override
            public void onCloseAd(int closeType) {
                Log.d("hjwanka", "插屏关闭!"+"，closeType:"+closeType);
            }

            @Override
            public void onClickAd(int click_type) {
                MobclickAgent.onEvent(mContext,"inter_click");
                Log.d("hjwanka", "插屏被点击!");
            }

            @Override
            public void onAdError(String msg, int code) {
                Log.d("hjwanka", "插屏加载失败!"+"，msg:"+msg+",code："+code);
            }
        });




    }

}
