package com.rx2.fragment;

import com.rx2.operat.creating.Defer;
import com.rx2.operat.creating.FromFuture;
import com.rx2.operat.creating.Interval;
import com.rx2.operat.creating.Range;
import com.rx2.operat.creating.Timer;

/**
 * 如何创建 Observable 来发射事件流
 *
 * Created by wangw on 2017/5/18.
 */

public class CreateFrag extends BaseFragment {

    @Override
    public String getSampleName() {
        return "创建一个事件流";
    }

    @Override
    protected void onInitData() {
        addOperat(Defer.class);
        addOperat(Range.class);
        addOperat(Interval.class);
        addOperat(Timer.class);
        addOperat(FromFuture.class);
    }
}
