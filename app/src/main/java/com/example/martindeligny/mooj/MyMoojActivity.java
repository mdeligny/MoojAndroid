package com.example.martindeligny.mooj;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by martindeligny on 30/04/2016.
 */
public class MyMoojActivity extends Fragment {

    ArrayList<Deal> deals = new ArrayList<>();
    WikeoRestClient wk = new WikeoRestClient();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle args) {

        View rootView = inflater.inflate(R.layout.activity_mymooj, container, false);

        try {
            deals = wk.getPublicTimeline();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        final RecyclerView rv = (RecyclerView) rootView.findViewById(R.id.list);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(container.getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(new MyAdapter(deals));

        return rootView;
    }


}
