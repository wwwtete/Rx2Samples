package com.rx2.operat.creating;

import com.rx2.comm.BaseObserver;
import com.rx2.operat.BaseOperation;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;

/**
 * http://reactivex.io/documentation/operators/interval.html
 * interval :表示创建一个无限的计时器，每隔一段时间发射一个数字，从0开始，直到调用dispose方法为止，调用dispose后是不会调用onComplete方法的，否则一直无限的发射下去
 * intervalRange: 表示创建一个指定范围的发射器，发射完成后回调onComplete方法
 * Created by wangw on 2017/5/18.
 */

public class Interval extends BaseOperation {
    @Override
    public String getSampleName() {
        return "interval | intervalRange";
    }

    @Override
    protected void execute() {
        demo1();
        demo2();

    }

    private void demo2() {
        Observable.intervalRange(10,5,3000,500,TimeUnit.MILLISECONDS,Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<Long>(this) {
                    @Override
                    public void onNext(@NonNull Long aLong) {
                        output("intervalRange",""+aLong);
                    }
                });
    }

    private void demo1() {
        /**
         * 第一个参数：表示延迟多少毫秒执行
         * 第二个参数：间隔多长时间
         */
        Observable.interval(500,500, TimeUnit.MILLISECONDS, Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<Long>(this) {

                    @Override
                    public void onNext(@NonNull Long aLong) {
                        output("interval",aLong+"");
                        if (aLong > 5) {
                            output("dispose");
                            mDisposable.dispose();
                        }
                    }
                });
    }
}
