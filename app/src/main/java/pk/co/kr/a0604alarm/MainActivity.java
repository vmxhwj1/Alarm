package pk.co.kr.a0604alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button)findViewById(R.id.button);
        btn.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                //알람이 동작할 시간 만들기
                Calendar cal = Calendar.getInstance();
                cal.setTimeInMillis(System.currentTimeMillis());
                cal.add(Calendar.SECOND, 30);

                //알람 매니저 생성
                AlarmManager am = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
                Intent intent = new Intent(MainActivity.this, AlarmReceiver.class);
                PendingIntent sender = PendingIntent.getBroadcast(
                        MainActivity.this,0,intent,0);

                //알람 등록
                //첫번째는 옵션으로 sleep 모드일 때 앱을 깨누는 동작이고
                //두번째가 알림이 동작할 시간
                //세번째가 알람이 오면 동작할 intent 입니다.
                //am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), sender);
                am.setInexactRepeating(AlarmManager.RTC_WAKEUP,
                        cal.getTimeInMillis(),AlarmManager.INTERVAL_DAY, sender);

            }
        });
    }
}
