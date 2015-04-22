package com.wwu.jimmy.james_collins_ella_bella;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Jimmy on 4/21/2015.
 */
public class sell_to_us extends Fragment {

    public static class App{
        public static File _file;
        public static File _dir;
        public static Bitmap bitmap;
    }

    private static final int CAMERA_REQUEST = 1888;
    ImageView item_photo;

    public static sell_to_us newInstance() {
        sell_to_us fragment = new sell_to_us();
        return fragment;
    }

    public sell_to_us() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.sell_to_us, container, false);

        //Take a photo
        Button take_picture_button = (Button) rootView.findViewById(R.id.take_picture_button);
        item_photo = (ImageView) rootView.findViewById(R.id.item_photo);
        take_picture_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//                File f = new File(android.os.Environment.getExternalStorageDirectory(), "temp.jpg");
//
//                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });

        //Send Email


        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        ((MainActivity) activity).onSectionAttached(3);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
//            File file = temp;
//            Uri
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            item_photo.setImageBitmap(photo);

            //save bitmap to memory
            FileOutputStream out = null;
            try {
                out = new FileOutputStream("temp_photo.png");
                photo.compress(Bitmap.CompressFormat.PNG, 100, out); // bmp is your Bitmap instance
                // PNG is a lossless format, the compression factor (100) is ignored
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (out != null) {
                        out.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

//http://stackoverflow.com/questions/2197741/how-can-i-send-emails-from-my-android-application?lq=1
//http://www.javacodegeeks.com/2013/10/send-email-with-attachment-in-android.html