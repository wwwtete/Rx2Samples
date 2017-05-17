package com.rx2.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.rx2.R;
import com.rx2.comm.L;
import com.rx2.comm.SampleListView;
import com.rx2.comm.SamplesModel;
import com.rx2.fragment.BaseFragment;

import butterknife.ButterKnife;


/**
 * Created by wangw on 2016/4/14.
 */
public abstract class BaseActivity extends AppCompatActivity implements SamplesModel {


    protected SampleListView mListView;
    protected FrameLayout mFlContent;
    protected ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
        L.d("Activity At ("+getClass().getSimpleName()+".java:0)");
    }

    protected void setDefaultView(){
        setContentView(R.layout.activity_home);
        mFlContent = (FrameLayout) findViewById(R.id.fl_content);
        mListView = (SampleListView) findViewById(R.id.listview);
    }

    public void replaceFragment(BaseFragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_content,fragment)
                .addToBackStack(fragment.toString())
                .commitAllowingStateLoss();
        mListView.setVisibility(View.GONE);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (mListView != null){
            mListView.setVisibility(View.VISIBLE);
            mFlContent.setVisibility(View.GONE);

        }

    }

    protected void addSample(BaseFragment fragment){
        if (mListView != null){
            mListView.addSample(fragment);
        }
    }

    protected void addSampleClass(Class<? extends BaseActivity> clz){
        if(mListView != null){
            mListView.addSample(clz);
        }
    }

    public void showToast(String msg){
        Toast.makeText(this,msg, Toast.LENGTH_SHORT).show();
    }

    protected void showLoading(){
        showLoading(true);
    }

    protected void showLoading(boolean cancelable)
    {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.setMessage("正在处理中...");
        mProgressDialog.setCancelable(cancelable);
        mProgressDialog.show();
    }
    protected void closeLoading()
    {
        if( mProgressDialog!=null){
            mProgressDialog.cancel();
            mProgressDialog=null;
        }
    }
}
