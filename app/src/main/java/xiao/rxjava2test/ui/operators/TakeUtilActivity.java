package xiao.rxjava2test.ui.operators;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import xiao.rxjava2test.R;
import xiao.rxjava2test.utils.AppConstant;

public class TakeUtilActivity extends AppCompatActivity {
    private String TAG = this.getClass().getSimpleName();

    private TextView tvOut;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.example_act);
        initUI();
    }

    private void initUI() {
        Button btnDoSomething = findViewById(R.id.btn_do_something);
        btnDoSomething.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doSomeWork();
            }
        });
        tvOut = findViewById(R.id.tv_out);
    }

    private void doSomeWork() {
        Observable<Long> timerObservable = Observable.timer(5, TimeUnit.SECONDS);
        timerObservable.subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Long aLong) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                String print = " Timer completed";
                tvOut.append(print);
                tvOut.append(AppConstant.LINE_SEPARATOR);
                Log.d(TAG, print);
            }
        });

        getStringObservable()
                .zipWith(Observable.interval(0, 1, TimeUnit.SECONDS), new BiFunction<String, Long, String>() {
                    @Override
                    public String apply(String s, Long aLong) throws Exception {
                        return s;
                    }
                })
                //将接收来自Strings observable的项目，直到timerObservable不开始发送数据。
                .takeUntil(timerObservable)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver());
    }

    protected Observable<String> getStringObservable() {
        return Observable.just("Alpha", "Beta", "Cupcake", "Doughnut", "Eclair", "Froyo", "GingerBread",
                "Honeycomb", "Ice cream sandwich");
    }

    protected Observer<? super String> getObserver() {
        return new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, " onSubscribe : " + d.isDisposed());
            }

            @Override
            public void onNext(String value) {
                tvOut.append(" onNext : value : " + value);
                tvOut.append(AppConstant.LINE_SEPARATOR);
                Log.d(TAG, " onNext value : " + value);
            }

            @Override
            public void onError(Throwable e) {
                tvOut.append(" onError : " + e.getMessage());
                tvOut.append(AppConstant.LINE_SEPARATOR);
                Log.d(TAG, " onError : " + e.getMessage());
            }

            @Override
            public void onComplete() {
                tvOut.append(" onComplete");
                tvOut.append(AppConstant.LINE_SEPARATOR);
                Log.d(TAG, " onComplete");
            }
        };
    }
}
