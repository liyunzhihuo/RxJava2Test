package xiao.rxjava2test.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import xiao.rxjava2test.R;
import xiao.rxjava2test.utils.AppConstant;

public class SimpleActivity extends AppCompatActivity {
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

    /*
     *一个接一个地发出两个值的简单例子
     */
    private void doSomeWork() {
        getObservable()
                // Run on a background thread
                .subscribeOn(Schedulers.io())
                // Be notified on the main thread
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver());
    }

    private Observer<String> getObserver() {
        return new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e(TAG, "onSubscribe " + d.isDisposed());
            }

            @Override
            public void onNext(String value) {
                Log.e(TAG, "onNext value = " + value);
                tvOut.append(" onNext : value : " + value);
                tvOut.append(AppConstant.LINE_SEPARATOR);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError  " + e.getMessage());
                tvOut.append(" onError :   " + e.getMessage());
                tvOut.append(AppConstant.LINE_SEPARATOR);
            }

            @Override
            public void onComplete() {
                Log.e(TAG, "onComplete");
                tvOut.append(" onComplete ");
                tvOut.append(AppConstant.LINE_SEPARATOR);
            }
        };
    }

    private Observable getObservable() {
        return Observable.just("Cricket", "Football");
    }
}
