package com.faceattributes.monitor;

import android.app.ActivityManager;
import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CPUMonitor implements Runnable {
    private volatile static CPUMonitor instance = null;
    private ScheduledExecutorService scheduler;
    private long freq;
    private StringBuilder sb;
    private int pid;

    private CPUMonitor() {
        scheduler = Executors.newSingleThreadScheduledExecutor();
    }

    public static CPUMonitor getInstance() {
        if (instance == null) {
            synchronized (CPUMonitor.class) {
                if (instance == null) {
                    instance = new CPUMonitor();
                }
            }
        }
        return instance;
    }

    public void init(Context context, long freq) {
        this.freq = freq;
        pid = android.os.Process.myPid();
    }

    public void start() {
        scheduler.scheduleWithFixedDelay(this, 0L, freq, TimeUnit.MILLISECONDS);
    }

    @Override
    public void run() {
        sampleCPU();
    }

    private double sampleCPU() {
        try {
            Process p = Runtime.getRuntime().exec("top -n 1 -p "+pid);
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream(), "utf-8"));
            String result;
            while ((result=br.readLine())!=null) {
                Log.d("CPUMonitor", result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
