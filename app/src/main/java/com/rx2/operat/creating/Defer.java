package com.rx2.operat.creating;

import com.rx2.operat.BaseOperation;

import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * http://reactivex.io/documentation/operators/defer.html
 * defer 并没有定义一个新的 Observable， defer 只是用来声明当 Subscriber 订阅到一个 Observable 上时，该 Observable 应该如何创建
 * 比如：两个 subscriber 相隔 1秒订阅这个 Observable，但是他们收到的时间数据是一样的！这是因为当订阅的时候，时间数据只调用一次。
 * 其实希望的是，当 一个 subscriber 订阅的时候才去获取当前的时间。
 * defer 的参数是一个返回一个 Observable 对象的函数。该函数返回的 Observable 对象就是 defer 返回的 Observable 对象。
 * 重点是，每当一个新的 Subscriber 订阅的时候，这个函数就重新执行一次。
 * Created by wangw on 2017/5/18.
 */

public class Defer extends BaseOperation {
    @Override
    public String getSampleName() {
        return "defer";
    }

    @Override
    protected void execute() {

        Observable<Long> just = Observable.just(System.currentTimeMillis());
        Observable<Long> defer = Observable.defer(new Callable<ObservableSource<Long>>() {
            @Override
            public ObservableSource<Long> call() throws Exception {
                return Observable.just(System.currentTimeMillis());
            }
        });

        test(just,"普通的");
        test(defer,"defer后");
    }

    private void test(Observable<Long> observable, final String msg) {
        output(msg);
        observable.subscribe(new Consumer<Long>() {
            @Override
            public void accept(@NonNull Long aLong) throws Exception {
                output("第一次时间：",aLong+"");
            }
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        observable.subscribe(new Consumer<Long>() {
            @Override
            public void accept(@NonNull Long aLong) throws Exception {
                output("一秒后的时间：",aLong+"");
            }
        });

    }
}
