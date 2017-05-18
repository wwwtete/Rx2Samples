package com.rx2.operat;

import com.rx2.comm.L;
import com.rx2.comm.Operation;
import com.rx2.comm.OutputView;

/**
 * Created by wangw on 2017/5/18.
 */

public abstract class BaseOperation implements Operation,OutputView {

    protected OutputView mOutputView;

    @Override
    public void onOperat(OutputView output) {
        mOutputView = output;
        execute();
    }

    protected abstract void execute();

    @Override
    public void output(String msg){
        if (mOutputView != null)
        mOutputView.output(msg);
        else
            L.e(msg);
    }

    @Override
    public void output(String prefix,String msg){
        if (mOutputView != null)
        mOutputView.output(prefix,msg);
        else
            L.e("["+prefix+"]"+msg);
    }


}
