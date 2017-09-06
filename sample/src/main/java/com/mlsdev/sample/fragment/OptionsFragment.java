package com.mlsdev.sample.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mlsdev.sample.R;
import com.mlsdev.sample.SampleActivity;
import com.mlsdev.sample.databinding.FragmentOptionsBinding;

public class OptionsFragment extends Fragment {
    private FragmentOptionsBinding binding;

    private void replaceFragment(@StringRes int title, @LayoutRes int layout) {
        ((SampleActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        ((SampleActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle bundle = new Bundle();
        bundle.putString(SampleActivity.TITLE_KEY, getString(title));
        bundle.putInt(SampleActivity.LAYOUT_KEY, layout);
        Fragment fragment = SampleFragment.newInstance(bundle);
        getActivity().getSupportFragmentManager().beginTransaction()
                .addToBackStack(fragment.getTag())
                .replace(R.id.content, fragment)
                .commit();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_options, container, false);

        binding.btnListFromBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(R.string.btn_list_from_bottom, R.layout.fragment_list_from_bottom);
            }
        });

        binding.btnListFromBottomScale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(R.string.btn_list_from_bottom_scale, R.layout.fragment_list_from_bottom_scale);
            }
        });

        binding.btnGridFromBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(R.string.btn_grid_from_bottom, R.layout.fragment_grid_from_bottom);
            }
        });

        binding.btnGridFromBottomScale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(R.string.btn_grid_from_bottom_scale, R.layout.fragment_grid_from_bottom_scale);
            }
        });

        binding.btnGridFromBottomRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(R.string.btn_grid_from_bottom_random, R.layout.fragment_grid_from_bottom_random);
            }
        });

        binding.btnGridRandomScale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(R.string.btn_grid_random_scale, R.layout.fragment_grid_from_bottom_random_scale);
            }
        });

        return binding.getRoot();
    }

}
