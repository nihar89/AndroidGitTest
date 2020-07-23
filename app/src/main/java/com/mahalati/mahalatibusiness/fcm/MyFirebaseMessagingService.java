package com.mahalati.mahalatibusiness.fcm;

import android.app.KeyguardManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.PowerManager;

import androidx.core.app.NotificationCompat;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.mahalati.mahalatibusiness.ActivitySplash;
import com.mahalati.mahalatibusiness.R;
import com.mahalati.mahalatibusiness.utils.CommonUtils;
import com.mahalati.mahalatibusiness.utils.SystemUtils;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "business";

    public static String str_demo = "demo";

    NotificationManager notificationManager;

    Handler handler = new Handler();

    public MyFirebaseMessagingService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        notificationManager = (NotificationManager) MyFirebaseMessagingService.this.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    @Override
    public void onNewToken(String token) {
        super.onNewToken(token);

        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        System.out.println("business Refreshed token...." + refreshedToken);

        sendRegistrationToServer(refreshedToken);
    }

    private void sendRegistrationToServer(String token) {
        CommonUtils.setStringSharedPref(this, CommonUtils.SF_DEVICE_TOKEN, token);
    }

    /**
     * Called when message is received.
     *
     * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
     */
    // [START receive_message]
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            System.out.println("business firebase data payload..." + remoteMessage.getData());
        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            System.out.println("business firebase  Notification payload...." + remoteMessage.getNotification().getBody());
        }

        createNotification(remoteMessage.getData().get("title"), remoteMessage.getData().get("body"), remoteMessage.getData().get("encode_id"));

        PowerManager pm = (PowerManager) this.getSystemService(Context.POWER_SERVICE);
        boolean isScreenOn = Build.VERSION.SDK_INT >= 20 ? pm.isInteractive() : pm.isScreenOn(); // check if screen is on
        if (isScreenOn == false) {
            PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK | PowerManager.ON_AFTER_RELEASE | PowerManager.ACQUIRE_CAUSES_WAKEUP, "myApp:notificationLock");
            wl.acquire(10000); //set your time in milliseconds
            PowerManager.WakeLock wl_cpu = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "myApp:notificationLock");
            wl_cpu.acquire(10000);
        }

        if (remoteMessage.getData().get("body").equals(getResources().getString(R.string.notification_update_package))) {
            str_demo = remoteMessage.getData().get("body");
            CommonUtils.setStringSharedPref(this, CommonUtils.SF_NOTIFICATION, getResources().getString(R.string.notification_update_package));
            CommonUtils.triggerRebirth1(this);
        } else if (remoteMessage.getData().get("body").equals("تم تغيير باقة حسابكم")) {
            str_demo = remoteMessage.getData().get("body");
            CommonUtils.setStringSharedPref(this, CommonUtils.SF_NOTIFICATION, getResources().getString(R.string.notification_update_package));
            CommonUtils.triggerRebirth1(this);
        } else if (remoteMessage.getData().get("body").equals("Your user package changed")) {
            str_demo = remoteMessage.getData().get("body");
            CommonUtils.setStringSharedPref(this, CommonUtils.SF_NOTIFICATION, getResources().getString(R.string.notification_update_package));
            CommonUtils.triggerRebirth1(this);
        }

        KeyguardManager myKM = (KeyguardManager) this.getSystemService(Context.KEYGUARD_SERVICE);
        if (myKM.inKeyguardRestrictedInputMode()) {
            //it is locked
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //Do something after 10000ms
                    if (remoteMessage.getData().get("encode_id") != null && !remoteMessage.getData().get("encode_id").equalsIgnoreCase("")) {
                        if (SystemUtils.isAppAlive(getApplicationContext(), "com.mahalati.mahalatibusiness")) {
                            Intent intent = new Intent(getApplicationContext(), ActivitySplash.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                            startActivity(intent);
                        } else {
                            Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.mahalati.mahalatibusiness");
                            launchIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
                            startActivity(launchIntent);
                        }
                    }
                }
            }, 10000);
        } else {
            //it is not locked
            if (remoteMessage.getData().get("encode_id") != null && !remoteMessage.getData().get("encode_id").equalsIgnoreCase("")) {

                if (SystemUtils.isAppAlive(this, "com.mahalati.mahalatibusiness")) {
                    Intent intent = new Intent(this, ActivitySplash.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intent);
                } else {
                    Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.mahalati.mahalatibusiness");
                    launchIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
                    startActivity(launchIntent);
                }
            }
        }
        }

    private NotificationManager notifManager;

    /**
     * @param aTitle
     * @param aMessage
     */
    public void createNotification(String aTitle, String aMessage, String encodeid) {

        final int NOTIFY_ID = 1234;

        Intent intent;
        PendingIntent pendingIntent;

        NotificationCompat.Builder builder;

        if (notifManager == null) {
            notifManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel notificationChannel = new NotificationChannel(getResources().getString(R.string.default_notification_channel_id),
                    getString(R.string.app_name), NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.setDescription("Lavender Business Notification");
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.GREEN);
            notificationChannel.enableVibration(true);
            notificationChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});

            AudioAttributes attributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();

            StringBuilder sb = new StringBuilder();
            sb.append("android.resource://");
            sb.append(getPackageName());
            sb.append("/");
            sb.append(R.raw.notification);
            notificationChannel.setSound(Uri.parse(sb.toString()), attributes);
            notificationManager.createNotificationChannel(notificationChannel);

            intent = new Intent(this, ActivitySplash.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

            builder = new NotificationCompat.Builder(this, getString(R.string.default_notification_channel_id));
            builder.setContentTitle(aTitle)  // required
                    .setSmallIcon(getNotificationIcon()) // required
                    .setContentText(aMessage)  // required
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setAutoCancel(false)
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setSound(Uri.parse(sb.toString()))
                    .setContentIntent(pendingIntent)
                    .setTicker(aMessage)
                    .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                    .setVibrate(new long[0]);

            Notification notification = builder.build();
            notifManager.notify(NOTIFY_ID, notification);
        } else {
            sendNotificationDemo(aMessage, encodeid);
        }
    }

    public int getNotificationIcon() {
        boolean whiteIcon = (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP);
        return whiteIcon ? R.drawable.ic_notification_image : R.drawable.ic_notification;
    }

    private void sendNotificationDemo(String messageBody, String encodeid) {

        Intent intent = new Intent(this, ActivitySplash.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent, 0);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(getNotificationIcon())
                .setVibrate(new long[0])
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentTitle(getString(R.string.app_name))
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1 /* ID of notification */, notificationBuilder.build());
    }
}