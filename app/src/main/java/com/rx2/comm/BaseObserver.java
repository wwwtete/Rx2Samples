package com.rx2.comm;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by wangw on 2017/5/18.
 */

public abstract class BaseObserver<T> implements Observer<T> {

    private OutputView mOutputView;
    public  Disposable mDisposable;

    public BaseObserver(OutputView outputView) {
        mOutputView = outputView;
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        mOutputView.output("onSubscribe",d.toString());
        mDisposable = d;
    }


    @Override
    public void onError(@NonNull Throwable e) {
        mOutputView.output("onError",e.toString());
    }

    @Override
    public void onComplete() {
        mOutputView.output("onComplete!");
    }
}
