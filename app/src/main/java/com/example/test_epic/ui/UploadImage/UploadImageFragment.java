package com.example.test_epic.ui.UploadImage;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.test_epic.R;
import com.example.test_epic.uplaodImageHelper.DocumentHelper;
import com.example.test_epic.models.responseModels.ImageResponse;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;

import java.io.File;
import java.util.List;

import com.example.test_epic.models.uploadImage;
import com.example.test_epic.retrofitServices.upload_post;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static android.app.Activity.RESULT_OK;

public class UploadImageFragment extends Fragment implements View.OnClickListener {

    private static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 23;
    private static final int REQUEST_CODE_CHOOSE = 25;
    private static UploadImageViewModel dashboardViewModel;
    private static Context context = null;
    private static View view = null;
    private static ImageView myImage = null;
    private static TextView titlePost = null;
    private static TextView descPost = null;
    private static File fileToUpload = null;
    private static uploadImage post = null;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(UploadImageViewModel.class);
        View root = inflater.inflate(R.layout.fragment_upload_image, container, false);
        context = getActivity();

        myImage = root.findViewById(R.id.image_view);
        titlePost = root.findViewById(R.id.title_post);
        descPost = root.findViewById(R.id.desc_post);
        Button getImage = root.findViewById(R.id.add_image);

        getImage.setOnClickListener(this);
        myImage.setOnClickListener(this);
        //askPermissiom(root);
        return root;
    }

    @SuppressLint("CheckResult")
    @Override
    public void onClick(final View v) {
        switch (v.getId()) {
            case R.id.add_image:
                actionUploadImage(fileToUpload);
                break;
            case R.id.image_view:
                askPermissiom(v);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }

    }

    private void askPermissiom(final View v) {
        view = v;
        if (ContextCompat.checkSelfPermission(this.getActivity(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this.getActivity(),
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
        }
        else {
            // Permission has already been granted
            startAction(v);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    //startAction(view);
                } else {
                    Toast.makeText(context, R.string.permission_request_denied, Toast.LENGTH_LONG)
                            .show();
                }
                return;
            }
        }
    }

    private void startAction(View v) {
            Matisse.from(UploadImageFragment.this)
                    .choose(MimeType.ofImage(), false)
                    .countable(true)
                    .maxSelectable(9)
                    .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                    .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                    .thumbnailScale(0.85f)
                    .imageEngine(new GlideEngine())
                    .forResult(REQUEST_CODE_CHOOSE);

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            //fileToUpload = new File(String.valueOf(Matisse.obtainPathResult(data)));
            setImageContent(Matisse.obtainResult(data), Matisse.obtainPathResult(data));
            Log.d("Matisse", "Uris: " + Matisse.obtainResult(data));
            Log.d("Matisse", "Paths: " + Matisse.obtainPathResult(data));
            Log.e("Matisse", "Use the selected photos with original: "+String.valueOf(Matisse.obtainOriginalState(data)));
        }
    }

    private void setImageContent(final List<Uri> Uri, final List path) {
        Uri imageUri = Uri.get(0);
        String filePath = DocumentHelper.getPath(context,imageUri);
        myImage.setImageURI(imageUri);
        fileToUpload = new File(filePath);
    }

    private void actionUploadImage(File image) {
        if (image == null) {
            Toast.makeText(context, R.string.no_image, Toast.LENGTH_LONG);
            return ;
        }

        post = new uploadImage();

        post.image = new File(image.getPath());
        post.title = titlePost.getText().toString();
        post.description = descPost.getText().toString();

        new upload_post(context).uploadPost(post, new UiCallback());
    }

    private class UiCallback implements Callback<ImageResponse> {
        @Override
        public void success(ImageResponse imageResponse, Response response) {
            Toast.makeText(context, R.string.upload_ok, Toast.LENGTH_LONG);
        }

        @Override
        public void failure(RetrofitError error) {
            //Assume we have no connection, since error is null
            if (error == null) {
                //Log.d("no internet", "no internet");
                Toast.makeText(context, R.string.no_internet, Toast.LENGTH_LONG);
            }
        }
    }
}