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
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import xiao.rxjava2test.R;
import xiao.rxjava2test.utils.AppConstant;

public class ThrottleLastExActivity extends AppCompatActivity {
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
        getObservable()
                .throttleLast(500, TimeUnit.MILLISECONDS)
                // Run on a background thread
                .subscribeOn(Schedulers.io())
                // Be notified on the main thread
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver());
    }
    private Observer<Integer> getObserver() {
        return new Observer<Integer>() {

            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, " onSubscribe : " + d.isDisposed());
            }

            @Override
            public void onNext(Integer value) {
                tvOut.append(" onNext : ");
                tvOut.append(AppConstant.LINE_SEPARATOR);
                tvOut.append(" value : " + value);
                tvOut.append(AppConstant.LINE_SEPARATOR);
                Log.d(TAG, " onNext ");
                Log.d(TAG, " value : " + value);
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

    private Observable<Integer> getObservable() {
        return Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                // send events with simulated time wait
                Thread.sleep(0);
                emitter.onNext(1); // skip
                emitter.onNext(2); // deliver
                Thread.sleep(505);
                emitter.onNext(3); // skip
                Thread.sleep(99);
                emitter.onNext(4); // skip
                Thread.sleep(100);
                emitter.onNext(5); // skip
                emitter.onNext(6); // deliver
                Thread.sleep(305);
                emitter.onNext(7); // deliver
                Thread.sleep(510);
                emitter.onComplete();
            }
        });
    }
}
