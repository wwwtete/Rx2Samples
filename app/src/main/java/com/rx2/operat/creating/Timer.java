package com.rx2.operat.creating;

import com.rx2.comm.BaseObserver;
import com.rx2.operat.BaseOperation;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;

/**
 * http://reactivex.io/documentation/operators/timer.html
 * Timer: 创建一个Observable后，等待一段时间后发射数据0就结束
 *
 * Created by wangw on 2017/5/18.
 */

public class Timer extends BaseOperation {
    @Override
    public String getSampleName() {
        return "Timer";
    }

    @Override
    protected void execute() {
        demo1();
    }

    private void demo1() {
        Observable.timer(500, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<Long>(this) {
                    @Override
                    public void onNext(@NonNull Long aLong) {
                        output("timer",""+aLong);
                    }
                });
    }
}
