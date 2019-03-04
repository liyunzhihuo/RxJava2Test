package xiao.rxjava2test.ui.operators;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import io.reactivex.Observable;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import xiao.rxjava2test.R;
import xiao.rxjava2test.utils.AppConstant;

public class LastOperatorExActivity extends AppCompatActivity {
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
        getObservable().last("A1") // the default item ("A1") to emit if the source ObservableSource is empty
                .subscribe(getObserver());
    }

    private SingleObserver<? super String> getObserver() {
        return  new SingleObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, " onSubscribe : " + d.isDisposed());
            }

            @Override
            public void onSuccess(String value) {
                tvOut.append(" onNext : value : " + value);
                tvOut.append(AppConstant.LINE_SEPARATOR);
                Log.d(TAG, " onNext value : " + value);
            }


            @Override
            public void onError(Throwable e) {
                tvOut.append(" onError : " + e.getMessage());
                tvOut.append(AppConstant.LINE_SEPARATOR);
                Log.d(TAG, " onError : " + e.getMessage());
            }
        };
    }

    private Observable<String> getObservable() {
        return Observable.just("A1", "A2", "A3", "B1", "B2");
    }
}
