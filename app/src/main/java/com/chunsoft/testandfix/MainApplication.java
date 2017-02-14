package com.chunsoft.testandfix;

import android.app.Application;

import com.alipay.euler.andfix.patch.PatchManager;

/**
 * Developer：chunsoft on 2017/2/13 00:05
 * Email：chun_soft@qq.com
 * Content：
 */

public class MainApplication extends Application{
    public static PatchManager mPatchManager;

    @Override
    public void onCreate() {
        super.onCreate();

        //  初始化patch管理类
        mPatchManager = new PatchManager(this);

        //  初始化patch版本
        mPatchManager.init("1.0");
        //  String appVersion = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
        //  mPatchManager.init(appVersion);

        //  加载已经添加到PatchManager中的patch
        mPatchManager.loadPatch();
    }
}
