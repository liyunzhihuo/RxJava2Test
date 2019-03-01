package xiao.rxjava2test.ui.observer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import xiao.rxjava2test.R;
import xiao.rxjava2test.utils.AppConstant;

public class SingleObserverExActivity extends AppCompatActivity {
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
     * SingleObserver Example
     * */
    private void doSomeWork() {
        Single.just("1")
                .subscribe(getSingleObserver());
    }

    private SingleObserver<? super String> getSingleObserver() {
        return new SingleObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e(TAG, " onSubscribe ");

            }

            @Override
            public void onSuccess(String s) {
                Log.e(TAG, " onSuccess s : " + s);
                tvOut.append(" onSuccess : value : " + s);
                tvOut.append(AppConstant.LINE_SEPARATOR);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, " onError e : " + e.getMessage());
                tvOut.append(" onSuccess : e : " + e);
                tvOut.append(AppConstant.LINE_SEPARATOR);
            }
        };
    }
}
