package com.moises;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Moises on 10/28/2014.
 */
public class SelectionFragment extends Fragment {

    private static final String TAG = "SELECTIONFRAGMENT";

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.selection,
                container, false);
        return view;
    }
}
