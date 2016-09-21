package com.loveplusplus.update.sample;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.loveplusplus.update.AppUtils;
import com.loveplusplus.update.UpdateChecker;

public class MainActivity extends AppCompatActivity {


    public static final int NOTIFICATION_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn1 = (Button) findViewById(R.id.button1);
        Button btn2 = (Button) findViewById(R.id.button2);

        btn1.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                UpdateChecker.checkForDialog(MainActivity.this);
            }
        });
        btn2.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                UpdateChecker.checkForNotification(MainActivity.this);
            }
        });


        TextView textView = (TextView) findViewById(R.id.textView1);

        textView.setText("当前版本信息: versionName = " + AppUtils.getVersionName(this) + " versionCode = " + AppUtils.getVersionCode(this));
    }


    public void sendBasicNotification(View view) {


        /**
         * 点击通知栏跳转的意图 ，可以是url 、Activity ...
         */
        Intent resultIntent = new Intent(MainActivity.this, NotificationActivity.class);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(MainActivity.this, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);


        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_stat_notification)
                /**
                 * 点击导航条，控制跳转按钮
                 */
                .setContentIntent(resultPendingIntent)
                //点击后消失
                .setAutoCancel(true)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_icon))

                .setContentTitle("BasicNotifications Sample")
                .setContentText("Time to learn about notifications!")
                .setSubText("Tap to view documentation about notifications.");


//        TaskStackBuilder stackBuilder = TaskStackBuilder.create(MainActivity.this);
//        stackBuilder.addParentStack(NotificationActivity.class);
//        stackBuilder.addNextIntent(resultIntent);

        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
    }


}
