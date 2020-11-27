//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.serenegiant.utils;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Handler.Callback;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * 1.创建Handler
 * 2.创建HandlerThread，返回HandlerThread的Handler
 **/
public class HandlerThreadHandler extends Handler {
    private static final String TAG = "HandlerThreadHandler";

    public static final HandlerThreadHandler createHandler() {
        return createHandler("HandlerThreadHandler");
    }

    public static final HandlerThreadHandler createHandler(String name) {
        HandlerThread thread = new HandlerThread(name);
        thread.start();
        return new HandlerThreadHandler(thread.getLooper());
    }

    public static final HandlerThreadHandler createHandler(@Nullable Callback callback) {
        return createHandler("HandlerThreadHandler", callback);
    }

    public static final HandlerThreadHandler createHandler(String name, @Nullable Callback callback) {
        HandlerThread thread = new HandlerThread(name);
        thread.start();
        return new HandlerThreadHandler(thread.getLooper(), callback);
    }

    private HandlerThreadHandler(@NonNull Looper looper) {
        super(looper);
    }

    private HandlerThreadHandler(@NonNull Looper looper, @Nullable Callback callback) {
        super(looper, callback);
    }
}
