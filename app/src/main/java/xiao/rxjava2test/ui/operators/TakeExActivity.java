package xiao.rxjava2test.ui.operators;

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
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import xiao.rxjava2test.R;
import xiao.rxjava2test.utils.AppConstant;

public class TakeExActivity extends AppCompatActivity {
    private String TAG = this.getClass().getSimpleName();

    private TextView tvOut;
    private CompositeDisposable disposables = new CompositeDisposable();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.example_act);
        initUI();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposables.clear();
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
     *使用Take操作符,可以限定发送的数量,接收到指定数量后不再继续接收。
     *
     */
    private void doSomeWork() {
        getObservable()
                // Run on a background thread
                .subscribeOn(Schedulers.io())
                // Be notified on the main thread
                .observeOn(AndroidSchedulers.mainThread())
                .take(3)
                .subscribe(getObserver());
    }

    private Observer<? super String> getObserver() {
        return new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e(TAG, "onSubscribe :" + d.isDisposed());
            }

            @Override
            public void onNext(String s) {
                Log.e(TAG, "onNext");
                tvOut.append(" onNext");
                tvOut.append(AppConstant.LINE_SEPARATOR);
                tvOut.append("s :" + s);
                tvOut.append(AppConstant.LINE_SEPARATOR);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError :" + e.getMessage());
                tvOut.append("onError :" + e.getMessage());
                tvOut.append(AppConstant.LINE_SEPARATOR);
            }

            @Override
            public void onComplete() {
                Log.e(TAG, "onComplete");
                tvOut.append("onComplete");
                tvOut.append(AppConstant.LINE_SEPARATOR);
            }
        };
    }

    private Observable<String> getObservable() {
        return Observable.just("1", "2", "3", "4", "5");
    }


}
