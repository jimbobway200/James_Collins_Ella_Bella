package com.wwu.jimmy.james_collins_ella_bella;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Jimmy on 4/21/2015.
 */
public class sell_to_us extends Fragment {

    public static sell_to_us newInstance() {
        sell_to_us fragment = new sell_to_us();
        return fragment;
    }

    public sell_to_us(){}

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.sell_to_us, container, false);
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        ((MainActivity) activity).onSectionAttached(3);
    }
}
