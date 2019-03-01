package xiao.rxjava2test.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import xiao.rxjava2test.R;
import xiao.rxjava2test.base.BaseActivity;
import xiao.rxjava2test.ui.observer.CompletableObserverExActivity;
import xiao.rxjava2test.ui.observer.FlowableExActivity;
import xiao.rxjava2test.ui.observer.MaybeObserverExActivity;
import xiao.rxjava2test.ui.observer.SimpleActivity;
import xiao.rxjava2test.ui.observer.SingleObserverExActivity;

public class ObserverTypeActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.observer_type_act);

    }

    public void startSimple(View view) {
        startToOther(SimpleActivity.class);
    }

    public void aboutSingleObserver(View view) {
        startToOther(SingleObserverExActivity.class);
    }

    public void aboutCompletableObserver(View view) {
        startToOther(CompletableObserverExActivity.class);
    }

    public void aboutFlowable(View view) {
        startToOther(FlowableExActivity.class);
    }

    public void aboutMaybe(View view) {
        startToOther(MaybeObserverExActivity.class);
    }


}
