package xiao.rxjava2test.ui.operators;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import io.reactivex.MaybeObserver;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import xiao.rxjava2test.R;
import xiao.rxjava2test.utils.AppConstant;

public class ReduceExActivity extends AppCompatActivity {
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
     *使用Reduce计算所有数字的和
     *
     * */
    private void doSomeWork() {
        getObservable()
                .reduce(new BiFunction<Integer, Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer, Integer integer2) throws Exception {
                        return integer + integer2;
                    }
                })
                .subscribe(getObserver());
    }

    private MaybeObserver<Integer> getObserver() {
        return new MaybeObserver<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e(TAG, " onSubscribe ");
            }

            @Override
            public void onSuccess(Integer value) {
                Log.e(TAG, " onSuccess : value : " + value);
                tvOut.append(" onSuccess : value : " + value);
                tvOut.append(AppConstant.LINE_SEPARATOR);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, " onError : e : " + e.getMessage());
                tvOut.append(" onError : e : " + e.getMessage());
                tvOut.append(AppConstant.LINE_SEPARATOR);
            }

            @Override
            public void onComplete() {
                Log.e(TAG, " onComplete ");
                tvOut.append(" onComplete ");
                tvOut.append(AppConstant.LINE_SEPARATOR);
            }
        };
    }

    private Observable<Integer> getObservable() {
        return Observable.just(1, 2);
    }

}
