package com.rx2.comm;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.rx2.activity.BaseActivity;
import com.rx2.comm.adapter.RecyclerViewAdapter;
import com.rx2.fragment.BaseFragment;


/**
 * Created by wangw on 2016/5/12.
 */
public class SampleListView extends RecyclerView implements RecyclerViewAdapter.OnItemClickListener {

    private SamplesAdapter mAdapter;

    public SampleListView(Context context) {
        super(context);
        onInitView();
    }

    public SampleListView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        onInitView();
    }

    public SampleListView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        onInitView();
    }

    private void onInitView() {
        mAdapter = new SamplesAdapter(getContext());
        setLayoutManager(new LinearLayoutManager(getContext()));
        setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);

    }

    public void addSample(BaseFragment fragment){
        mAdapter.add(fragment);
    }

    public void addSample(Class clz){
        try {
            mAdapter.add((SamplesModel) clz.newInstance());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onItemClick(View view, Object data, int position) {
        if(BaseActivity.class.isAssignableFrom(data.getClass()))
            getContext().startActivity(new Intent(getContext(), data.getClass()));
        else if (BaseFragment.class.isAssignableFrom(data.getClass())){
            ((BaseActivity)getContext()).replaceFragment((BaseFragment) data);
        }
    }
}
