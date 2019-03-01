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
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import xiao.rxjava2test.R;
import xiao.rxjava2test.utils.AppConstant;

public class TimerExActivity extends AppCompatActivity {
    private String TAG = this.getClass().getSimpleName();

    private TextView tvOut;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.example_act);
        initUI();
    }



    private void initUI() {
        Button btnMap = findViewById(R.id.btn_do_something);
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doSomeWork();
            }
        });
        tvOut = findViewById(R.id.tv_out);
    }

    /*
     *使用Timer操作符来进行延时操作
     *
     * */
    private void doSomeWork() {
        Log.e(TAG, "doSomeWork time : " + System.currentTimeMillis());
        tvOut.append("doSomeWork");
        tvOut.append(AppConstant.LINE_SEPARATOR);
        tvOut.append("time : " + System.currentTimeMillis());
        tvOut.append(AppConstant.LINE_SEPARATOR);
        getObservable()
                // Run on a background thread
                .subscribeOn(Schedulers.io())
                // Be notified on the main thread
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver());
    }

    private Observer<? super Long> getObserver() {
        return new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e(TAG, "onSubscribe time :" + System.currentTimeMillis());
            }

            @Override
            public void onNext(Long aLong) {
                Log.e(TAG, "onNext time :" + System.currentTimeMillis());
                tvOut.append("onNext value " + aLong);
                tvOut.append(AppConstant.LINE_SEPARATOR);
                tvOut.append("time : " + System.currentTimeMillis());
                tvOut.append(AppConstant.LINE_SEPARATOR);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError e :" + e.getMessage());
                tvOut.append("onError e : " + e.getMessage());
                tvOut.append(AppConstant.LINE_SEPARATOR);
            }

            @Override
            public void onComplete() {
                Log.e(TAG, "onComplete ");
                tvOut.append("onComplete ");
                tvOut.append(AppConstant.LINE_SEPARATOR);
            }
        };
    }

    private Observable<Long> getObservable() {
        return Observable.timer(2, TimeUnit.SECONDS);
    }


}
