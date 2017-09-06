package com.mlsdev.sample.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mlsdev.animatedrv.AnimatedRecyclerView;
import com.mlsdev.sample.R;
import com.mlsdev.sample.SampleActivity;
import com.mlsdev.sample.SimpleAdapter;

public class SampleFragment extends Fragment {
    private SimpleAdapter adapter = new SimpleAdapter();

    public static Fragment newInstance(@NonNull Bundle args) {
        SampleFragment fragment = new SampleFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Bundle args = getArguments();
        String title = "Sample";
        int layout = R.layout.fragment_list_from_bottom;
        if (args != null) {
            title = args.getString(SampleActivity.TITLE_KEY);
            layout = args.getInt(SampleActivity.LAYOUT_KEY);
        }

        ((SampleActivity) getActivity()).getSupportActionBar().setTitle(title);

        View view = inflater.inflate(layout, container, false);
        RecyclerView recyclerView = (AnimatedRecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();

        return view;
    }
}
