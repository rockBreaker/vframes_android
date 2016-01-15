package com.angarron.vframes.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.angarron.vframes.R;
import com.angarron.vframes.adapter.FrameDataRecyclerViewAdapter;

import java.util.List;
import java.util.Map;

import data.model.character.FrameData;
import data.model.move.IFrameDataEntry;
import data.model.move.MoveCategory;

public class FrameDataFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Activity hostActivity = getActivity();
        IFrameDataFragmentHost frameDataFragmentHost = (IFrameDataFragmentHost) hostActivity;
        FrameData frameData = frameDataFragmentHost.getFrameData();

        if (frameData != null) {
            LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.frame_data_layout, container, false);

            RecyclerView recyclerView = (RecyclerView) linearLayout.findViewById(R.id.frames_recycler_view);
            recyclerView.setLayoutManager(new LinearLayoutManager(hostActivity));
            recyclerView.setAdapter(new FrameDataRecyclerViewAdapter(getContext(), frameData));
            return linearLayout;
        } else {
            return inflater.inflate(R.layout.frame_data_upcoming, container, false);
        }
    }

    public interface IFrameDataFragmentHost {
        FrameData getFrameData();
    }
}
