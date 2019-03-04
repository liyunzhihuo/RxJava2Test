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
import xiao.rxjava2test.R;
import xiao.rxjava2test.model.Car;
import xiao.rxjava2test.utils.AppConstant;

public class DeferExActivity extends AppCompatActivity {
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
     * Defer用于推迟Observable代码，直到在RxJava中订阅
     */
    private void doSomeWork() {
        Car car = new Car();
        Observable<String> brandDeferObservable = car.brandDeferObservable();
        car.setBrand("BMW");
        //即使我们在创建Observable之后设置了品牌
        //我们将把这个品牌作为宝马。
        //如果我们没有使用延迟，我们将会获得品牌为空。
        brandDeferObservable.subscribe(getObserver());

    }

    private Observer<String> getObserver() {
        return new Observer<String>() {

            @Override
            public void onSubscribe(Disposable d) {
                Log.e(TAG, " onSubscribe : " + d.isDisposed());
            }

            @Override
            public void onNext(String value) {
                tvOut.append(" onNext : value : " + value);
                tvOut.append(AppConstant.LINE_SEPARATOR);
                Log.e(TAG, " onNext : value : " + value);
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
