package io.virtualapp.delegate;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.util.Log;
import com.lody.virtual.client.hook.delegate.ComponentDelegate;
import com.lody.whale.xposed.XC_MethodHook;
import com.lody.whale.xposed.XposedHelpers;

import java.io.File;


public class MyComponentDelegate implements ComponentDelegate {
    private static final String TAG="HOOK";

    @Override
    public void beforeApplicationCreate(Application application) {
        //放狗
        Log.w(TAG,"beforeApplicationCreate:开始放狗啦！");
        try {
            XposedHelpers.findAndHookConstructor(File.class,String.class,new XC_MethodHook() {

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                    Object fileName = param.args[0];
                    Log.d(TAG, "open file "+fileName);
                }
            });


            Class<?>  aClass = Class.forName("com.xubei.shop.ui.module.login.presenter.LoginPresenter", true, application.getClassLoader());

            XposedHelpers.findAndHookMethod(aClass, "userInfoIndex",String.class,String.class,String.class,new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {

                    Object authorization = param.args[0];
                    Object phone = param.args[2];

                    Log.i(TAG, "业务系统用户登陆成功 phone:"+phone+" authorization:"+authorization);
                    super.beforeHookedMethod(param);
                }
            });


        }
        catch (Exception e) {
            Log.e(TAG,"beforeApplicationCreate:放狗异常啦！",e);
        }
    }


    @Override
    public void afterApplicationCreate(Application application) {
        Log.w(TAG,"afterApplicationCreate:放完狗啦！");
    }

    @Override
    public void beforeActivityCreate(Activity activity) {
        Log.w(TAG,"beforeActivityCreate:汪！");

    }

    @Override
    public void beforeActivityResume(Activity activity) {
        Log.w(TAG,"beforeActivityResume:汪！");

    }

    @Override
    public void beforeActivityPause(Activity activity) {
        Log.w(TAG,"beforeActivityPause:汪！");

    }

    @Override
    public void beforeActivityDestroy(Activity activity) {
        Log.w(TAG,"beforeActivityDestroy:汪！");

    }

    @Override
    public void afterActivityCreate(Activity activity) {
        Log.w(TAG,"afterActivityCreate:汪！");

    }

    @Override
    public void afterActivityResume(Activity activity) {
        Log.w(TAG,"afterActivityResume:汪！");

    }

    @Override
    public void afterActivityPause(Activity activity) {
        Log.w(TAG,"afterActivityPause:汪！");

    }

    @Override
    public void afterActivityDestroy(Activity activity) {
        Log.w(TAG,"afterActivityDestroy:汪！");

    }

    @Override
    public void onSendBroadcast(Intent intent) {
        Log.w(TAG,"onSendBroadcast:汪！");

    }
}
