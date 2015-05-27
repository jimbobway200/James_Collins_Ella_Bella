package com.wwu.jimmy.james_collins_ella_bella;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * Created by Jimmy on 4/21/2015.
 */
public class current_sales extends Fragment {

    public static current_sales newInstance() {
        current_sales fragment = new current_sales();
        return fragment;
    }

    TextView downloadEditText;
    View rootView;

    public current_sales(){}

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.current_sales, container, false);
        Intent intent = new Intent(rootView.getContext(), FileService.class);
        intent.putExtra("url", "http://sw.cs.wwu.edu/~collinj8/ellabella");

        downloadEditText = (TextView) rootView.findViewById(R.id.downloadedEditText);

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(FileService.TRANSACTION_DONE);

        rootView.getContext().registerReceiver(downloadReceiver, intentFilter);
        rootView.getContext().startService(intent);
        return rootView;
    }



    private BroadcastReceiver downloadReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.e("FileService", "Service Received");
            showFileContents();
        }
    };

    private void startService(Intent intent) {
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        ((MainActivity) activity).onSectionAttached(2);
    }

    public void showFileContents(){

        // Will build the String from the local file
        StringBuilder sb;

        try {
            // Opens a stream so we can read from our local file
            FileInputStream fis = rootView.getContext().openFileInput("myFile");


            // Gets an input stream for reading data
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");

            // Used to read the data in small bytes to minimize system load
            BufferedReader bufferedReader = new BufferedReader(isr);

            // Read the data in bytes until nothing is left to read
            sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line).append("\n");
            }

            // Put downloaded text into the EditText
            downloadEditText.setText(sb.toString());


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
