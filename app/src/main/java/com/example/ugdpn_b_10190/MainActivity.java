package com.example.ugdpn_b_10190;

import static com.example.ugdpn_b_10190.MyApplication.CHANNEL_1_ID;
import static com.example.ugdpn_b_10190.MyApplication.CHANNEL_2_ID;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.installations.time.SystemClock;

public class MainActivity extends AppCompatActivity {
    private NotificationManagerCompat notificationManager;
    private EditText editTextTitle;
    private EditText editTextMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notificationManager = NotificationManagerCompat.from(this);

        editTextTitle = findViewById(R.id.edit_text_title);
        editTextMessage = findViewById(R.id.edit_text_message);
    }

    public void sendOnChannel1(View v){
        String title = editTextTitle.getText().toString();
        String message = editTextMessage.getText().toString();

        Intent activityIntent = new Intent( this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this,0,activityIntent,0);

        Intent broadcastIntent = new Intent( this,NotificationReceiver.class);
        broadcastIntent.putExtra("toastMessage",message);
        PendingIntent actionIntent = PendingIntent.getBroadcast(this,0,broadcastIntent,PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_baseline_looks_one_24)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setColor(Color.BLUE)
                .setContentIntent(contentIntent)
                .setAutoCancel(true)
                .setOnlyAlertOnce(true)
                .addAction(R.mipmap.ic_launcher, "Toast", actionIntent)
                .build();

        notificationManager.notify(1,notification);
    }

    public void sendOnChannel2(View v){
        String title = editTextTitle.getText().toString();
        String message = editTextMessage.getText().toString();
        final int progressMax = 100;

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_2_ID)
                .setSmallIcon(R.drawable.ic_baseline_looks_two_24)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .build();

        notificationManager.notify(2,notification);

        final NotificationCompat.Builder notification.build = new NotificationCompat.Builder(this, CHANNEL_2_ID)
                .setSmallIcon(R.drawable.ic_baseline_looks_two_24)
                .setContentTitle("Download")
                .setContentText("Download in progress")
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .setOngoing(true)
                .setOnlyAlertOnce(true)
                .setProgress(progressMax, 0, false);

        notificationManager.notify(2,notification.build());

        new Thread(new Runnable(){
            @Override
            public void run(){
                SystemClock.sleep( 2000 );
                for(int progress = 0; progress <= progressMax; progress +- 10){
                    notification.setProgress(progressMax, progress, false);
                    notificationManager.notify(2, notification.build());
                }
                notification.setContextText("Download Finished")
                        .setProgress(0,0,false)
                        .setOnGoing(false);
                notificationManager.notify(2,notification.build());
            }
        }).start();

        groupNotification{
            Notification notification1 = new NotificationCompat.Builder(this, CHANNEL_2_ID)
                    .setSmallIcon(R.drawable.ic_baseline_looks_two_24)
                    .setContentTitle(title1)
                    .setContentText(message1)
                    .setPriority(NotificationCompat.Priority_LOW)
                    .setGroup("example_group")
                    .build();

            Notification notification1 = new NotificationCompat.Builder(this, CHANNEL_2_ID)
                    .setSmallIcon(R.drawable.ic_baseline_looks_two_24)
                    .setContentTitle(title2)
                    .setContentText(message2)
                    .setPriority(NotificationCompat.Priority_LOW)
                    .setGroup("example_group")
                    .build();

            Notification notification1 = new NotificationCompat.Builder(this, CHANNEL_2_ID)
                    .setSmallIcon(R.drawable.ic_baseline_looks_two_24)
                    .setStyle(new NotificationCompat.InboxStyle()
                            .addLine(title2 + - - + message1)
                            .addLine(title1 + - - + message2)
                            .setBigContentTitle("2 New Messages")
                            .setSummaryText("user@example.com"))
                    .setPriority(NotificationCompat.PRIORITY_LOW)
                    .setGroup("example_group")
                    .setGroupAlertBehavior(NotificationCompat.GROUP_ALERT_CHILDREN)
                    .setGroupSummary(true)
                    .build();

                    SystemClock.sleep(2000);
                    notificationManager.notify(2, notification1);
                    SystemClock.sleep(2000);
                    notificationManager.notify(3, notification2);
                    SystemClock.sleep(2000);
                    notificationManager.notify(4, summaryNotification);
        }
    }


}