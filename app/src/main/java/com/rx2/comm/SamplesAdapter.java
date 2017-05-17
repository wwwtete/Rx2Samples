package com.rx2.comm;

import android.content.Context;

import com.rx2.R;
import com.rx2.comm.adapter.RecyclerViewAdapter;


/**
 * Created by wangw on 2016/4/15.
 */
public class SamplesAdapter extends RecyclerViewAdapter<SamplesModel> {


    public SamplesAdapter(Context context) {
        super(context, R.layout.samples_item);
    }

    @Override
    protected void onBindData(RecyclerViewAdapter<SamplesModel>.RecyclerViewHolder holder, SamplesModel samplesModel, int position) {
        holder.setText(R.id.tv_name,samplesModel.getSampleName());
    }
}
