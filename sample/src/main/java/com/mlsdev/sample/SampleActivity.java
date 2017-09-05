package com.mlsdev.sample;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.mlsdev.sample.databinding.ActivitySampleBinding;
import com.mlsdev.sample.fragment.OptionsFragment;

public class SampleActivity extends AppCompatActivity {
    public static final String TITLE_KEY = "title_key";
    public static final String LAYOUT_KEY = "layout_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySampleBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_sample);

        getSupportFragmentManager().beginTransaction()
                .replace(binding.content.getId(), new OptionsFragment())
                .commit();

    }
}
