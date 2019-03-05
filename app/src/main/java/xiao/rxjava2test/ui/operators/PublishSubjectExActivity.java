package xiao.rxjava2test.ui.operators;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;
import xiao.rxjava2test.R;
import xiao.rxjava2test.utils.AppConstant;

public class PublishSubjectExActivity extends AppCompatActivity {
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
        PublishSubject<Integer> source = PublishSubject.create();

        source.subscribe(getFirstObserver());

        source.onNext(1);
        source.onNext(2);
        source.onNext(3);
        source.onComplete();

        source.subscribe(getSecondObserver());

        source.onNext(4);
        source.onComplete();
    }

    private Observer<Integer> getSecondObserver() {
        return new Observer<Integer>() {

            @Override
            public void onSubscribe(Disposable d) {
                tvOut.append(" Second onSubscribe : isDisposed :" + d.isDisposed());
                Log.d(TAG, " Second onSubscribe : " + d.isDisposed());
                tvOut.append(AppConstant.LINE_SEPARATOR);
            }

            @Override
            public void onNext(Integer value) {
                tvOut.append("Second onNext : value : " + value);
                tvOut.append(AppConstant.LINE_SEPARATOR);
                Log.e(TAG, "Second onNext : value : " + value);
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

    private Observer<Integer> getFirstObserver() {
        return new Observer<Integer>() {

            @Override
            public void onSubscribe(Disposable d) {
                Log.e(TAG, "First onSubscribe : " + d.isDisposed());
            }

            @Override
            public void onNext(Integer value) {
                tvOut.append("First onNext : value : " + value);
                tvOut.append(AppConstant.LINE_SEPARATOR);
                Log.e(TAG, "First onNext : value : " + value);
            }

            @Override
            public void onError(Throwable e) {
                tvOut.append("First onError : " + e.getMessage());
                tvOut.append(AppConstant.LINE_SEPARATOR);
                Log.e(TAG, "First onError : " + e.getMessage());
            }

            @Override
            public void onComplete() {
                tvOut.append("First onComplete");
                tvOut.append(AppConstant.LINE_SEPARATOR);
                Log.e(TAG, "First onComplete");
            }
        };
    }
}
