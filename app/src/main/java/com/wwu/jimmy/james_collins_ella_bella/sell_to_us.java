package com.wwu.jimmy.james_collins_ella_bella;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
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
    View rootView;
    File file;


    public static sell_to_us newInstance() {
        sell_to_us fragment = new sell_to_us();
        return fragment;
    }

    public sell_to_us() {
    }


    protected void onRestoreInstanceState(Bundle savedInstanceState){

    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.sell_to_us, container, false);

        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root + "/req_images");
        myDir.mkdirs();
        String fname = "Ella-Bella-Temp-Photo.png";
        file = new File(myDir, fname);
        //Restore???
        if (savedInstanceState != null)
        {
            EditText guest_name = (EditText) rootView.findViewById(R.id.guest_name_edit_text);
            EditText guest_email = (EditText) rootView.findViewById(R.id.guest_email_edit_text);
            EditText item_description = (EditText) rootView.findViewById(R.id.item_description);
            EditText asking_price = (EditText) rootView.findViewById(R.id.asking_price);

            guest_email.setText(savedInstanceState.getCharSequence("guest_email"));
            guest_name.setText(savedInstanceState.getCharSequence("guest_name"));
            item_description.setText(savedInstanceState.getCharSequence("item_description"));
            asking_price.setText(savedInstanceState.getCharSequence("asking_price"));
        }

        //Take a photo
        Button take_picture_button = (Button) rootView.findViewById(R.id.take_picture_button);
        item_photo = (ImageView) rootView.findViewById(R.id.item_photo);
        take_picture_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);

                String root = Environment.getExternalStorageDirectory().toString();
                File myDir = new File(root + "/req_images");
                myDir.mkdirs();
                String fname = "Ella-Bella-Temp-Photo.png";
                file = new File(myDir, fname);
                if (file.exists())
                    file.delete();
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });

        //Send Email
        Button submit_form = (Button) rootView.findViewById(R.id.submit_form);
        submit_form.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                EditText guest_name = (EditText) rootView.findViewById(R.id.guest_name_edit_text);
                EditText guest_email = (EditText) rootView.findViewById(R.id.guest_email_edit_text);
                EditText item_description = (EditText) rootView.findViewById(R.id.item_description);
                EditText asking_price = (EditText) rootView.findViewById(R.id.asking_price);


                Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
                String emailtext = "Customer Name: " + guest_name.getText().toString() + '\n' + "Customer Email: " + guest_email.getText().toString() + '\n' + "Item Description: " + item_description.getText().toString() + '\n' + "Asking Price: " + asking_price.getText().toString();
                emailIntent.setType("plain/text");
                emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[] { "jimbobway200@gmail.com" }); // going to address
                emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Sell to Ella Bella"); // subject
                emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file)); //photo
                emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, emailtext);
                startActivity(Intent
                        .createChooser(emailIntent, "Send mail..."));
            }
        });

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        ((MainActivity) activity).onSectionAttached(3);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap( getActivity().getContentResolver(),  Uri.fromFile(file));
                item_photo.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {

                e.printStackTrace();
            } catch (IOException e) {

                e.printStackTrace();
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        EditText guest_name = (EditText) rootView.findViewById(R.id.guest_name_edit_text);
        EditText guest_email = (EditText) rootView.findViewById(R.id.guest_email_edit_text);
        EditText item_description = (EditText) rootView.findViewById(R.id.item_description);
        EditText asking_price = (EditText) rootView.findViewById(R.id.asking_price);
        outState.putCharSequence("guest_name", guest_name.getText().toString());
        outState.putCharSequence("guest_email", guest_email.getText().toString());
        outState.putCharSequence("item_description", item_description.getText().toString());
        outState.putCharSequence("asking_price", asking_price.getText().toString());




    }
}
