package xiao.rxjava2test.ui.operators;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import xiao.rxjava2test.R;
import xiao.rxjava2test.utils.AppConstant;

public class BufferExActivity extends AppCompatActivity {
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
     *
     */
    private void doSomeWork() {
        Observable<List<String>> observable = getObservable().buffer(3, 1);
        // 3 means,  it takes max of three from its start index and create list
        // 1 means, it jumps one step every time
        // so the it gives the following list
        // 1 - 1, 2, 3
        // 2 - 2, 3, 4
        // 3 - 3, 4, 5
        // 4 - 4, 5
        // 5 - 5
        observable.subscribe(getObserver());
    }

    private Observer<? super List<String>> getObserver() {
        return new Observer<List<String>>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e(TAG, " onSubscribe : " + d.isDisposed());
            }

            @Override
            public void onNext(List<String> stringList) {
                tvOut.append(" onNext size : " + stringList.size());
                tvOut.append(AppConstant.LINE_SEPARATOR);
                Log.e(TAG, " onNext : size :" + stringList.size());
                for (String value : stringList) {
                    tvOut.append(" value : " + value);
                    tvOut.append(AppConstant.LINE_SEPARATOR);
                    Log.e(TAG, " : value :" + value);
                }
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

    private Observable<String> getObservable() {
        return Observable.just("1", "2", "3", "4","5");
    }
}
