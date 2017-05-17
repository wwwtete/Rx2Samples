package com.rx2.fragment;

import android.os.Bundle;

/**
 * Created by wangw on 2017/5/17.
 */

public class TestFragment extends BaseFragment {

    public static TestFragment newInstance() {
        
        Bundle args = new Bundle();
        
        TestFragment fragment = new TestFragment();
        fragment.setArguments(args);
        return fragment;
    }
    
    @Override
    public String getSampleName() {
        return "Test";
    }
}
