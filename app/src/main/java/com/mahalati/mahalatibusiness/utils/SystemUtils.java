package com.mahalati.mahalatibusiness.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.util.Log;

import java.util.List;

public class SystemUtils {

    public static boolean isAppAlive(Context context, String packageName){
        ActivityManager activityManager = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> processInfos = activityManager.getRunningAppProcesses();
        for(int i = 0; i < processInfos.size(); i++){
            if(processInfos.get(i).processName.equals(packageName)){
                System.out.println("business NotificationLaunch..."+ String.format("the %s is running, isAppAlive return true", packageName));
                return true;
            }
        }
        System.out.println("business NotificationLaunch..."+ String.format("the %s is not running, isAppAlive return false", packageName));
        return false;
    }
}