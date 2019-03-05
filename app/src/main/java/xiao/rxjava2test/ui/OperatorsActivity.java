package xiao.rxjava2test.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Switch;

import xiao.rxjava2test.R;
import xiao.rxjava2test.base.BaseActivity;
import xiao.rxjava2test.ui.operators.AsyncSubjectExActivity;
import xiao.rxjava2test.ui.operators.BehaviorSubjectExActivity;
import xiao.rxjava2test.ui.operators.BufferExActivity;
import xiao.rxjava2test.ui.operators.ConcatExActivity;
import xiao.rxjava2test.ui.operators.DebounceExActivity;
import xiao.rxjava2test.ui.operators.DeferExActivity;
import xiao.rxjava2test.ui.operators.DelayExActivity;
import xiao.rxjava2test.ui.operators.DisposableExActivity;
import xiao.rxjava2test.ui.operators.DistinctExActivity;
import xiao.rxjava2test.ui.operators.FilterExActivity;
import xiao.rxjava2test.ui.operators.IntervalExActivity;
import xiao.rxjava2test.ui.operators.LastOperatorExActivity;
import xiao.rxjava2test.ui.operators.MapExActivity;
import xiao.rxjava2test.ui.operators.MergeExActivity;
import xiao.rxjava2test.ui.operators.PublishSubjectExActivity;
import xiao.rxjava2test.ui.operators.ReduceExActivity;
import xiao.rxjava2test.ui.operators.ReplayExActivity;
import xiao.rxjava2test.ui.operators.ReplaySubjectExActivity;
import xiao.rxjava2test.ui.operators.ScanExActivity;
import xiao.rxjava2test.ui.operators.SkipExActivity;
import xiao.rxjava2test.ui.operators.SwitchMapActivity;
import xiao.rxjava2test.ui.operators.TakeExActivity;
import xiao.rxjava2test.ui.operators.TakeUtilActivity;
import xiao.rxjava2test.ui.operators.TakeWhileActivity;
import xiao.rxjava2test.ui.operators.ThrottleFirstExActivity;
import xiao.rxjava2test.ui.operators.ThrottleLastExActivity;
import xiao.rxjava2test.ui.operators.TimerExActivity;
import xiao.rxjava2test.ui.operators.WindowExActivity;
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

    public void aboutConcat(View view) {
        startToOther(ConcatExActivity.class);
    }

    public void aboutMerge(View view) {
        startToOther(MergeExActivity.class);
    }

    public void aboutDefer(View view) {
        startToOther(DeferExActivity.class);
    }

    public void aboutDistinct(View view) {
        startToOther(DistinctExActivity.class);
    }

    public void aboutLastOperator(View view) {
        startToOther(LastOperatorExActivity.class);
    }

    public void aboutReplaySubject(View view) {
        startToOther(ReplaySubjectExActivity.class);
    }

    public void aboutPublishSubject(View view) {
        startToOther(PublishSubjectExActivity.class);
    }

    public void aboutBehaviorSubject(View view) {
        startToOther(BehaviorSubjectExActivity.class);
    }

    public void aboutAsyncSubject(View view) {
        startToOther(AsyncSubjectExActivity.class);
    }

    public void aboutThrottleFirst(View view) {
        startToOther(ThrottleFirstExActivity.class);
    }

    public void aboutThrottleLast(View view) {
        startToOther(ThrottleLastExActivity.class);
    }

    public void aboutDebounce(View view) {
        startToOther(DebounceExActivity.class);
    }

    public void aboutWindow(View view) {
        startToOther(WindowExActivity.class);
    }

    public void aboutDelay(View view) {
        startToOther(DelayExActivity.class);
    }

    public void aboutSwitchMap(View view) {
        startToOther(SwitchMapActivity.class);
    }

    public void aboutTakeWhile(View view) {
        startToOther(TakeWhileActivity.class);
    }

    public void aboutTakeUtil(View view) {
        startToOther(TakeUtilActivity.class);
    }
}
