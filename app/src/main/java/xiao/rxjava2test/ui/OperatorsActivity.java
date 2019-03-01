package xiao.rxjava2test.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import xiao.rxjava2test.R;
import xiao.rxjava2test.base.BaseActivity;
import xiao.rxjava2test.ui.operators.BufferExActivity;
import xiao.rxjava2test.ui.operators.DisposableExActivity;
import xiao.rxjava2test.ui.operators.FilterExActivity;
import xiao.rxjava2test.ui.operators.IntervalExActivity;
import xiao.rxjava2test.ui.operators.MapExActivity;
import xiao.rxjava2test.ui.operators.ReduceExActivity;
import xiao.rxjava2test.ui.operators.ReplayExActivity;
import xiao.rxjava2test.ui.operators.ScanExActivity;
import xiao.rxjava2test.ui.operators.SkipExActivity;
import xiao.rxjava2test.ui.operators.TakeExActivity;
import xiao.rxjava2test.ui.operators.TimerExActivity;
import xiao.rxjava2test.ui.operators.ZipExActivity;

public class OperatorsActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.operators_act);
    }

    public void aboutMap(View view) {
        startToOther(MapExActivity.class);
    }

    public void aboutZip(View view) {
        startToOther(ZipExActivity.class);
    }

    public void aboutDisposable(View view) {
        startToOther(DisposableExActivity.class);
    }

    public void aboutTake(View view) {
        startToOther(TakeExActivity.class);
    }

    public void aboutTimer(View view) {
        startToOther(TimerExActivity.class);
    }

    public void aboutInterval(View view) {
        startToOther(IntervalExActivity.class);
    }

    public void aboutReduce(View view) {
        startToOther(ReduceExActivity.class);
    }

    public void aboutBuffer(View view) {
        startToOther(BufferExActivity.class);
    }
    public void aboutFilter(View view) {
        startToOther(FilterExActivity.class);
    }
    public void aboutSkip(View view) {
        startToOther(SkipExActivity.class);
    }
    public void aboutScan(View view) {
        startToOther(ScanExActivity.class);
    }
    public void aboutReplay(View view) {
        startToOther(ReplayExActivity.class);
    }
}
