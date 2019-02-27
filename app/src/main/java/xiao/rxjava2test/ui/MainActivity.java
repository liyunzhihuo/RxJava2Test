package xiao.rxjava2test.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import xiao.rxjava2test.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startSimple(View view) {
        startActivity(new Intent(MainActivity.this, SimpleActivity.class));
    }

    public void startOperators(View view) {
        startActivity(new Intent(MainActivity.this, OperatorsActivity.class));
    }
}
