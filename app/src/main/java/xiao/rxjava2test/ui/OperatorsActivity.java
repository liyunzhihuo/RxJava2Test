package xiao.rxjava2test.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import xiao.rxjava2test.R;
import xiao.rxjava2test.ui.operators.DisposableExampleActivity;
import xiao.rxjava2test.ui.operators.MapExampleActivity;
import xiao.rxjava2test.ui.operators.ZipExampleActivity;

public class OperatorsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.operators_act);
    }

    public void aboutMap(View view) {
        startActivity(new Intent(OperatorsActivity.this, MapExampleActivity.class));
    }
    public void aboutZip(View view) {
        startActivity(new Intent(OperatorsActivity.this, ZipExampleActivity.class));
    }
    public void aboutDisposable(View view) {
        startActivity(new Intent(OperatorsActivity.this,DisposableExampleActivity.class));
    }
}
