package io.virtualapp.delegate;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.util.Log;
import com.lody.virtual.client.hook.delegate.ComponentDelegate;
import com.taobao.android.dexposed.DexposedBridge;
import com.taobao.android.dexposed.XC_MethodHook;


public class MyComponentDelegate implements ComponentDelegate {
    private static final String TAG="HOOK";

    @Override
    public void beforeApplicationCreate(Application application) {
        //放狗
        Log.w(TAG,"beforeApplicationCreate:开始放狗啦！");
        try {


            class ThreadMethodHook extends XC_MethodHook {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                    Thread t = (Thread) param.thisObject;
                    Log.i(TAG, "thread:" + t + ", started..");
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                    Thread t = (Thread) param.thisObject;
                    Log.i(TAG, "thread:" + t + ", exit..");
                }
            }

            DexposedBridge.hookAllConstructors(Thread.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                    Thread thread = (Thread) param.thisObject;
                    Class<?> clazz = thread.getClass();
                    if (clazz != Thread.class) {
                        Log.d(TAG, "found class extend Thread:" + clazz);
                        DexposedBridge.findAndHookMethod(clazz, "run", new ThreadMethodHook());
                    }
                    Log.d(TAG, "Thread: " + thread.getName() + " class:" + thread.getClass() +  " is created.");
                }
            });
            DexposedBridge.findAndHookMethod(Thread.class, "run", new ThreadMethodHook());

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
