package com.rx2;

import android.os.Bundle;

import com.rx2.activity.BaseActivity;
import com.rx2.fragment.TestFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        setDefaultView();
        addSample(TestFragment.newInstance());

    }

    @Override
    public String getSampleName() {
        return "";
    }
}
