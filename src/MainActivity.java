import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import ua.kpi.comsys.io8325.playground.CoordinateKS;

public class MainActivity extends AppCompatActivity {

    private TextView playgroundTextView;
    private Thread realTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_main);

        playgroundTextView = findViewById(R.id.playground_textView);

        realTime = new Thread(() ->{
            while(true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                updatePlaygroundTextView(testTime());
            }
        });
        realTime.start();
    }


    private void updatePlaygroundTextView(StringBuffer text){
        runOnUiThread(() -> playgroundTextView.setText(text));
    }



    private StringBuffer testTime(){
        CoordinateKS time1 = new CoordinateKS(23, 59, 59);
        CoordinateKS time2 = new CoordinateKS();
        CoordinateKS time3 = new CoordinateKS(new Date());
        CoordinateKS time4 = new CoordinateKS(time3);
        time2.setSeconds(1);

        String add1 = time1.addTime(time2).toString();
        String sub1 = time2.subTime(time1).toString();

        String add2 = time1.addTime(time3, time4).toString();
        String sub2 = time2.subTime(time3, time4).toString();

        return new StringBuffer()
                .append("Playground\n")
                .append("Coordinate 1: ").append(time1).append("\n")
                .append("Coordinate 2: ").append(time2).append("\n")
                .append("Coordinate 3 (real time): ").append(time3).append("\n")
                .append("Coordinate 4 (= 3): ").append(time4).append("\n");
    }
}