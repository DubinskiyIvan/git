package com.example.myapplication;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.IBinder;

import static java.lang.Thread.sleep;

public class service extends android.app.Service {

    MyTask task;

    public void onCreate() {
        super.onCreate();
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        PendingIntent pendingIntent = intent.getParcelableExtra(serviceActivity.PENDING_INTENT_KEY);
        task = new MyTask(pendingIntent);
        startWork();
        return super.onStartCommand(intent, flags, startId);
    }

    public void onDestroy() {
        super.onDestroy();
        task.stop();
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    void startWork(){
        Thread thread = new Thread(task,"T1");
        thread.start();
    }


    private class MyTask implements Runnable{

        private boolean exit;
        private PendingIntent pendingIntent;

        MyTask(PendingIntent pendingIntent) {
            this.pendingIntent = pendingIntent;
        }

        @Override
        public void run() {
            talkToCreator(new Intent(), serviceActivity.COUNTER_START);

            exit = false;

            final int VERY_MUCH = 100000;
            for(int i=VERY_MUCH;i>0 && !exit;i--){

                talkToCreator(new Intent().putExtra(serviceActivity.COUNTER_ANSWER_KEY, i),
                        serviceActivity.COUNTER_ANSWER);

                try{
                    sleep(3000);
                } catch (InterruptedException e){
                    e.printStackTrace();
                    break;
                }
            }

            talkToCreator(new Intent(), serviceActivity.COUNTER_FINISH);
        }
        void stop(){
            exit = true;
        }

        void talkToCreator(Intent intent, int code){
            try {
                pendingIntent.send(service.this, code, intent);
            }
            catch (PendingIntent.CanceledException e){
                e.printStackTrace();
            }
        }
    }
}
