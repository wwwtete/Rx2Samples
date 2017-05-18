package com.rx2.operat.creating;


import com.rx2.operat.BaseOperation;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * http://reactivex.io/documentation/operators/range.html
 * range : 函数发射一个整数序列,
 * 如：range(10,2)表示发射一个从10开始，发射2个数字，输出值应该是：10，11
 * Created by wangw on 2017/5/18.
 */

public class Range extends BaseOperation {
    @Override
    public String getSampleName() {
        return "range";
    }

    @Override
    protected void execute() {
        Observable.range(10,2)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer integer) throws Exception {
                        output("range",integer+"");
                    }
                });
    }
}
