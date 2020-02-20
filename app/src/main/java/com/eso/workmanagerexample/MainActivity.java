package com.eso.workmanagerexample;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import static com.eso.workmanagerexample.DemoWorker.KEY_WORKER;

public class MainActivity extends AppCompatActivity {

    Button mButton;
    TextView mTextView;
    public static final String KEY_COUNT_VALUE="key_count";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final Data data=new Data.Builder()
                .putInt(KEY_COUNT_VALUE,1750)
                .build();

        Constraints constraints = new Constraints.Builder()
                .setRequiresCharging(true).build();

        final OneTimeWorkRequest oneTimeWorkRequest = new OneTimeWorkRequest.Builder(DemoWorker.class)
                .setInputData(data)
                .setConstraints(constraints)
                .build();

        mButton = findViewById(R.id.button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WorkManager.getInstance().enqueue(oneTimeWorkRequest);
            }
        });
        mTextView = findViewById(R.id.textView);
        WorkManager.getInstance().getWorkInfoByIdLiveData(oneTimeWorkRequest.getId()).observe(this, new Observer<WorkInfo>() {
            @Override
            public void onChanged(WorkInfo workInfo) {
                if (workInfo != null){
                    mTextView.setText(workInfo.getState().name());
                    if (workInfo.getState().isFinished()){
                        Data data1 = workInfo.getOutputData();
                        String message = data1.getString(KEY_WORKER);
                        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
