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
import io.reactivex.disposables.Disposable;
import io.reactivex.observables.ConnectableObservable;
import io.reactivex.subjects.PublishSubject;
import xiao.rxjava2test.R;
import xiao.rxjava2test.utils.AppConstant;

public class ReplayExActivity extends AppCompatActivity {
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

    /* Using replay operator, replay ensure that all observers see the same sequence
     * of emitted items, even if they subscribe after the Observable has begun emitting items
     */
    private void doSomeWork() {
        PublishSubject<Integer> source = PublishSubject.create();
        ConnectableObservable<Integer> connectableObservable = source.replay(3);
        connectableObservable.connect();

        connectableObservable.subscribe(getFirstObserver());
        source.onNext(1);
        source.onNext(2);
        source.onNext(3);
        source.onNext(4);
        source.onNext(5);

        /*
         * it will emit 2, 3, 4 as (count = 3), retains the 3 values for replay
         */
        connectableObservable.subscribe(getSecondObserver());
    }

    private Observer<? super Integer> getFirstObserver() {
        return new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e(TAG, "First onSubscribe : " + d.isDisposed());
            }

            @Override
            public void onNext(Integer value) {
                tvOut.append("First onNext : ");
                tvOut.append(AppConstant.LINE_SEPARATOR);
                tvOut.append("First value : " + value);
                tvOut.append(AppConstant.LINE_SEPARATOR);
                Log.e(TAG, "First onNext ");
                Log.e(TAG, "First value : " + value);
            }

            @Override
            public void onError(Throwable e) {
                tvOut.append(" onError : " + e.getMessage());
                tvOut.append(AppConstant.LINE_SEPARATOR);
                Log.e(TAG, " onError : " + e.getMessage());
            }

            @Override
            public void onComplete() {
                tvOut.append("First onComplete");
                tvOut.append(AppConstant.LINE_SEPARATOR);
                Log.e(TAG, "First onComplete");
            }
        };
    }

    private Observer<? super Integer> getSecondObserver() {
        return new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e(TAG, "Second onSubscribe : " + d.isDisposed());
            }

            @Override
            public void onNext(Integer value) {
                tvOut.append("Second onNext : ");
                tvOut.append(AppConstant.LINE_SEPARATOR);
                tvOut.append("Second value : " + value);
                tvOut.append(AppConstant.LINE_SEPARATOR);
                Log.e(TAG, "Second onNext ");
                Log.e(TAG, "Second value : " + value);
            }

            @Override
            public void onError(Throwable e) {
                tvOut.append("Second onError : " + e.getMessage());
                tvOut.append(AppConstant.LINE_SEPARATOR);
                Log.e(TAG, "Second onError : " + e.getMessage());
            }

            @Override
            public void onComplete() {
                tvOut.append("Second onComplete");
                tvOut.append(AppConstant.LINE_SEPARATOR);
                Log.e(TAG, "Second onComplete");
            }
        };
    }
}
