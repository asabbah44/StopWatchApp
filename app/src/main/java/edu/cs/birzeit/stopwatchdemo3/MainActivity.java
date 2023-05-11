package edu.cs.birzeit.stopwatchdemo3;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private int seconds = 0;
    private boolean running = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState != null){
            seconds = savedInstanceState.getInt("SECONDS");
            running = savedInstanceState.getBoolean("RUNNING");
        }

        runTimer();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("ChangeState","Method onPause called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("ChangeState","Method onStop called");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        Toast.makeText(this, "changed", Toast.LENGTH_SHORT).show();
        Log.d("ChangeState","Method onSaveInstanceState called");
        outState.putInt("SECONDS", seconds);
        outState.putBoolean("RUNNING", running);


    }



    private void runTimer(){
        final TextView txtTime = findViewById(R.id.txtTime);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                    int hours = seconds/3600;
                    int minutes = (seconds%3600) / 60;
                    int secs = seconds%60;
                    String time = hours +" : " + minutes + " : " + secs;
                    txtTime.setText(time);
                if(running){
                    seconds++;
                }
                handler.postDelayed(this, 1000);

            }
        });
    }


    public void btnStartOnClick(View view) {
        running = true;
    }

    public void btnStopOnClick(View view) {
        running = false;
    }

    public void btnResetOnClick(View view) {
        running = false;
        seconds = 0;
    }
}
