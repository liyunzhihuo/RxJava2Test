package xiao.rxjava2test.ui;


import android.os.Bundle;
import android.view.View;

import xiao.rxjava2test.R;
import xiao.rxjava2test.base.BaseActivity;
import xiao.rxjava2test.ui.operators.ReduceExActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startObservers(View view) {
        startToOther(ObserverTypeActivity.class);
    }

    public void startOperators(View view) {
        startToOther(OperatorsActivity.class);
    }

    public void startReduce(View view) {
        startToOther(ReduceExActivity.class);
    }

}
