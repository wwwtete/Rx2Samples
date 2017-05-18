package com.rx2.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rx2.R;
import com.rx2.comm.OutputView;
import com.rx2.comm.SampleListView;
import com.rx2.comm.SamplesModel;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by wangw on 2017/5/17.
 */

public abstract class BaseFragment extends Fragment implements SamplesModel,OutputView {

    protected View mView;
    protected SampleListView mRecyclerView;
    protected TextView mTvOutput;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        this.mView = inflater.inflate(getLayoutResId(),container,false);
        return mView;
    }

    protected int getLayoutResId(){
        return R.layout.frag_common;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        onInitView();
        onInitData();
    }

    protected abstract void onInitData();

    protected void onInitView() {
        mRecyclerView = (SampleListView) mView.findViewById(R.id.recycler);
        mRecyclerView.setOutputView(this);
        mTvOutput = (TextView) mView.findViewById(R.id.tv_output);
        Observable.create(new ObservableOnSubscribe<View>() {
            @Override
            public void subscribe(@NonNull final ObservableEmitter<View> e) throws Exception {
                mTvOutput.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        e.onNext(v);
                    }
                });
            }
        })
                .buffer(500, TimeUnit.MILLISECONDS, Schedulers.computation(),2)
                .filter(new Predicate<List<View>>() {
                    @Override
                    public boolean test(@NonNull List<View> views) throws Exception {
                        return views.size() >= 2;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<View>>() {
                    @Override
                    public void accept(@NonNull List<View> views) throws Exception {
                        mTvOutput.setText("");
                    }
                });

    }

    public void addOperat(Class clz){
        if (mRecyclerView != null)
            mRecyclerView.addSample(clz);
    }

    @Override
    public void output(String msg){
        if (mTvOutput != null){
            mTvOutput.setText(mTvOutput.getText()+"\n"+msg);
        }
    }

    @Override
    public void output(String prefix, String msg) {
        output("["+prefix+"] => "+msg);
    }
}
