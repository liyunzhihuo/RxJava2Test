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
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import xiao.rxjava2test.R;
import xiao.rxjava2test.utils.AppConstant;

public class WindowExActivity extends AppCompatActivity {
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
     *使用window运算符的示例 - >它定期将Observable中的项目细分为Observable窗口并发出这些窗口而不是一次发出一个项目
     *  */
    private void doSomeWork() {
        Observable.interval(1, TimeUnit.SECONDS).take(12)
                .window(3, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getConsumer());
    }

    private Consumer<Observable<Long>> getConsumer() {
        return new Consumer<Observable<Long>>() {
            @Override
            public void accept(Observable<Long> observable) throws Exception {
                Log.d(TAG, "Sub Divide begin....");
                tvOut.append("Sub Divide begin ....");
                tvOut.append(AppConstant.LINE_SEPARATOR);
                observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<Long>() {
                            @Override
                            public void accept(Long value) throws Exception {
                                Log.d(TAG, "Next:" + value);
                                tvOut.append("Next:" + value);
                                tvOut.append(AppConstant.LINE_SEPARATOR);
                            }
                        });
            }
        };
    }
}