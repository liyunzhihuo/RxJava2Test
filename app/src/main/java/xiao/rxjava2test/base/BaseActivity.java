package xiao.rxjava2test.base;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;


public class BaseActivity extends AppCompatActivity {

    public void startToOther(Class targetClass) {
        startActivity(new Intent(this, targetClass));
    }
}
