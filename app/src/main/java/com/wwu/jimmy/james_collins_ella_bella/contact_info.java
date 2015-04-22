package com.wwu.jimmy.james_collins_ella_bella;

        import android.app.Activity;
        import android.content.Intent;
        import android.net.Uri;
        import android.os.Bundle;
        import android.support.annotation.Nullable;
        import android.support.v4.app.Fragment;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageButton;

/**
 * Created by Jimmy on 4/21/2015.
 */
public class contact_info extends Fragment {

    public static contact_info newInstance() {
        contact_info fragment = new contact_info();
        return fragment;
    }

    public contact_info(){}

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //Facebook button
        View rootView = inflater.inflate(R.layout.contact_info, container, false);
        ImageButton facebook_button = (ImageButton) rootView.findViewById(R.id.facebook_button);
        facebook_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri url = Uri.parse("https://www.facebook.com/ellabellavintagefinds");
                Intent intent = new Intent(Intent.ACTION_VIEW, url);
                startActivity(intent);
            }
        });

        //Google Maps Directions
        ImageButton google_maps_button = (ImageButton) rootView.findViewById(R.id.google_maps_button);
        google_maps_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                       Uri.parse("http://maps.google.com/maps?daddr=18628 Main St, Conway, WA"));
                startActivity(intent);
            }
        });

        return rootView;

    }




    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        ((MainActivity) activity).onSectionAttached(1);
    }
}
