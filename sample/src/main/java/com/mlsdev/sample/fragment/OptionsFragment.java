package com.mlsdev.sample.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mlsdev.sample.R;
import com.mlsdev.sample.SampleActivity;
import com.mlsdev.sample.databinding.FragmentOptionsBinding;

public class OptionsFragment extends Fragment {
    private FragmentOptionsBinding binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_options, container, false);

        binding.btnListFromBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString(SampleActivity.TITLE_KEY, "List From Bottom");
                bundle.putInt(SampleActivity.LAYOUT_KEY, R.layout.fragment_sample);
                Fragment fragment = SampleFragment.newInstance(bundle);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content, fragment)
                        .commit();
            }
        });

        return binding.getRoot();
    }


}
