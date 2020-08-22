package net.decenternet.technicalexam.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import net.decenternet.technicalexam.R;
import net.decenternet.technicalexam.ui.tasks.TasksActivity;

public class MainActivity extends AppCompatActivity {

    private ImageView ivBrandingLogo;
    public static Boolean theCatalyst = true;
    private static int TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivBrandingLogo = (ImageView) findViewById(R.id.ivBrandingLogo);
        launchTaskActivity();
    }

    public void launchTaskActivity(){

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(MainActivity.this, TasksActivity.class);
                startActivity(i);
                finish();
            }
        }, TIME_OUT);

    }

}