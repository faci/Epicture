package com.example.test_epic.ui.Profile;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test_epic.R;
import com.example.test_epic.models.responseModels.getImageResponse;
import com.example.test_epic.models.responseModels.getMyFavsResponse;
import com.example.test_epic.retrofitServices.get_all_post;
import com.example.test_epic.retrofitServices.get_my_favs;
import com.example.test_epic.retrofitServices.get_my_post;
import com.example.test_epic.ui.MyAdapter;
import com.example.test_epic.ui.MyAdapterProfile;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ProfileFragment extends Fragment implements View.OnClickListener {

    private static Context context = null;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private Button home;
    private Button fav;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        context = getActivity();
        home = (Button) root.findViewById(R.id.home);
        fav = (Button) root.findViewById(R.id.fav);

        recyclerView = (RecyclerView) root.findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        try {
            new get_my_post(context).getMyPosts(new ProfileFragment.UiCallbackFeed());
            home.setTextColor(getActivity().getResources().getColor(R.color.colorAccent));
            fav.setTextColor(getActivity().getResources().getColor(R.color.white));
        } catch (Exception e) {
            e.printStackTrace();
        }

        home.setOnClickListener(this);
        fav.setOnClickListener(this);
        return root;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case(R.id.home):
                Log.d("test", "click HOME");
                try {
                    new get_my_post(context).getMyPosts(new ProfileFragment.UiCallbackFeed());
                    home.setTextColor(getActivity().getResources().getColor(R.color.colorAccent));
                    fav.setTextColor(getActivity().getResources().getColor(R.color.white));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case(R.id.fav):
                Log.d("test", "click fav");
                try {
                    new get_my_favs(context).getMyFavs(new ProfileFragment.UiCallbackFavs());
                    fav.setTextColor(getActivity().getResources().getColor(R.color.colorAccent));
                    home.setTextColor(getActivity().getResources().getColor(R.color.white));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    private class UiCallbackFeed implements Callback<getImageResponse> {
        @Override
        public void success(getImageResponse imageResponse, Response response) {
            Toast.makeText(context, R.string.upload_ok, Toast.LENGTH_LONG);

            try {
                mAdapter = new MyAdapterProfile(context, imageResponse.data, null);
                recyclerView.setAdapter(mAdapter);
            } catch (Exception e) {
                e.printStackTrace();
            }
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

    private class UiCallbackFavs implements Callback<getMyFavsResponse> {
        @Override
        public void success(getMyFavsResponse imageResponse, Response response) {
            Toast.makeText(context, R.string.upload_ok, Toast.LENGTH_LONG);

            try {
                Log.d("datas", String.valueOf(imageResponse.data));
                mAdapter = new MyAdapterProfile(context, null, imageResponse.data);
                recyclerView.setAdapter(mAdapter);
            } catch (Exception e) {
                e.printStackTrace();
            }
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