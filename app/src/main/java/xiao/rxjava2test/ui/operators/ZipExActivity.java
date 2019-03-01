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
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.schedulers.Schedulers;
import xiao.rxjava2test.R;
import xiao.rxjava2test.model.User;
import xiao.rxjava2test.utils.AppConstant;
import xiao.rxjava2test.utils.Utils;

public class ZipExActivity extends AppCompatActivity {
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
     *有两个user集合,一个是cricket迷,一个是football迷,
     *这里演示的是通过zip操作符找出同时喜欢cricket和football的user集合。
     */
    private void doSomeWork() {
        Observable.zip(getCricketFansObservable(), getFootballFansObservable(),
                new BiFunction<List<User>, List<User>, List<User>>() {
                    @Override
                    public List<User> apply(List<User> cricketFans, List<User> footballFans) {
                        return Utils.filterUserWhoLovesBoth(cricketFans, footballFans);
                    }
                })
                // Run on a background thread
                .subscribeOn(Schedulers.io())
                // Be notified on the main thread
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver());
    }

    private ObservableSource<? extends List<User>> getFootballFansObservable() {
        return Observable.create(new ObservableOnSubscribe<List<User>>() {
            @Override
            public void subscribe(ObservableEmitter<List<User>> emitter) throws Exception {
                if (!emitter.isDisposed()) {
                    emitter.onNext(Utils.getUserListWhoLovesCricket());
                    emitter.onComplete();
                }
            }
        });
    }

    private ObservableSource<? extends List<User>> getCricketFansObservable() {
        return Observable.create(new ObservableOnSubscribe<List<User>>() {
            @Override
            public void subscribe(ObservableEmitter<List<User>> emitter) throws Exception {
                if (!emitter.isDisposed()) {
                    emitter.onNext(Utils.getUserListWhoLovesFootball());
                    emitter.onComplete();
                }
            }
        });
    }

    private Observer<? super List<User>> getObserver() {
        return new Observer<List<User>>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e(TAG, "onSubscribe :" + d.isDisposed());
            }

            @Override
            public void onNext(List<User> users) {
                Log.e(TAG, "onNext");
                tvOut.append(" onNext");
                tvOut.append(AppConstant.LINE_SEPARATOR);
                if (users != null) {
                    for (User user : users) {
                        tvOut.append(" nickname : " + user.getNickname());
                        tvOut.append(AppConstant.LINE_SEPARATOR);
                        tvOut.append(" id : " + user.getId());
                        tvOut.append(AppConstant.LINE_SEPARATOR);
                    }
                } else {
                    tvOut.append("users ==null");
                    tvOut.append(AppConstant.LINE_SEPARATOR);
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError :" + e.getMessage());
                tvOut.append("onError :" + e.getMessage());
                tvOut.append(AppConstant.LINE_SEPARATOR);
            }

            @Override
            public void onComplete() {
                Log.e(TAG, "onComplete");
                tvOut.append("onComplete");
                tvOut.append(AppConstant.LINE_SEPARATOR);
            }
        };
    }
}
