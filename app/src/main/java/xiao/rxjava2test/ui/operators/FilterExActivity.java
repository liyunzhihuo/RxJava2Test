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
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Predicate;
import xiao.rxjava2test.R;
import xiao.rxjava2test.utils.AppConstant;

public class FilterExActivity extends AppCompatActivity {
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
     *通过使用filter运算符仅发出符合条件的值
     * */
    private void doSomeWork() {
        Observable.just(1, 2, 3, 4, 5)
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
                        return integer % 2 == 0;
                    }
                })
                .subscribe(getObserver());
    }

    private Observer<? super Integer> getObserver() {
        return new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e(TAG, " onSubscribe : " + d.isDisposed());
            }

            @Override
            public void onNext(Integer value) {
                tvOut.append(" onNext : ");
                tvOut.append(AppConstant.LINE_SEPARATOR);
                tvOut.append(" value : " + value);
                tvOut.append(AppConstant.LINE_SEPARATOR);
                Log.e(TAG, " onNext ");
                Log.e(TAG, " value : " + value);
            }

            @Override
            public void onError(Throwable e) {
                tvOut.append(" onError : " + e.getMessage());
                tvOut.append(AppConstant.LINE_SEPARATOR);
                Log.e(TAG, " onError : " + e.getMessage());
            }

            @Override
            public void onComplete() {
                tvOut.append(" onComplete");
                tvOut.append(AppConstant.LINE_SEPARATOR);
                Log.e(TAG, " onComplete");
            }
        };
    }
}
