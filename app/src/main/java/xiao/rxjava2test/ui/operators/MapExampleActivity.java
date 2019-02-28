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
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import xiao.rxjava2test.R;
import xiao.rxjava2test.model.ApiUser;
import xiao.rxjava2test.model.User;
import xiao.rxjava2test.utils.AppConstant;
import xiao.rxjava2test.utils.Utils;

public class MapExampleActivity extends AppCompatActivity {
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
     *从服务器中获取到的数据类型可能是ApiUser对象,
     *因为我们的数据库或者其他原因使用User对象比ApiUser对象更好,
     *所以我们可以通过Map操作符将ApiUser对象转换为User对象。
     */
    private void doSomeWork() {
        getObservable()
                // Run on a background thread
                .subscribeOn(Schedulers.io())
                // Be notified on the main thread
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<List<ApiUser>, List<User>>() {

                    @Override
                    public List<User> apply(List<ApiUser> apiUsers) {
                        return Utils.convertApiUserListToUserList(apiUsers);
                    }
                })
                .subscribe(getObserver());
    }

    private Observer<List<User>> getObserver() {
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

    private Observable<List<ApiUser>> getObservable() {
        return Observable.create(new ObservableOnSubscribe<List<ApiUser>>() {
            @Override
            public void subscribe(ObservableEmitter<List<ApiUser>> emitter) throws Exception {
                if (!emitter.isDisposed()) {
                    emitter.onNext(Utils.getApiUserList());
                    emitter.onComplete();
                }
            }
        });
    }
}
