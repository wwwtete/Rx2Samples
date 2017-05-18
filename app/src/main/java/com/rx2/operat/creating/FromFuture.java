package com.rx2.operat.creating;

import com.rx2.comm.BaseObserver;
import com.rx2.operat.BaseOperation;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * fromFuture:表示使用FutureTask异步来获取数据
 * Created by wangw on 2017/5/18.
 */

public class FromFuture extends BaseOperation {
    @Override
    public String getSampleName() {
        return "FromFuture";
    }

    @Override
    protected void execute() {
        final FutureTask<String> futureTask = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                //故意延时1秒，超时后如果没有返回数据则进入Error
                Thread.sleep(5000);
                return "Success";
            }
        });
        new Thread(futureTask).start();
        Observable.timer(300,TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(@NonNull Long aLong) throws Exception {
                        output("timer","isCancelled:"+futureTask.isCancelled()+" | isDone:"+futureTask.isDone());
                    }
                });
        Observable.fromFuture(futureTask,100, TimeUnit.MILLISECONDS, Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<String>(this) {
                    @Override
                    public void onNext(@NonNull String s) {
                        output("onNext",s);
                    }
                });
    }
}
