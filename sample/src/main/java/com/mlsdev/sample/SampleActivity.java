package com.mlsdev.sample;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.mlsdev.sample.databinding.ActivitySampleBinding;

public class SampleActivity extends AppCompatActivity {
    private ActivitySampleBinding binding;
    private SimpleAdapter adapter = new SimpleAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sample);
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.notifyDataSetChanged();
    }
}
