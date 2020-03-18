package com.example.test_epic.ui.Feed;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test_epic.R;
import com.example.test_epic.models.responseModels.getAllPostResponse;
import com.example.test_epic.retrofitServices.get_all_post;
import com.example.test_epic.ui.MyAdapter;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class FeedFragment extends Fragment {

    private FeedViewModel homeViewModel;
    private static Context context = null;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(FeedViewModel.class);
        View root = inflater.inflate(R.layout.fragment_feed, container, false);
        context = getActivity();

        recyclerView = (RecyclerView) root.findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        try {
            new get_all_post(context).getAllPost(new UiCallback());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return root;
    }

    private class UiCallback implements Callback<getAllPostResponse> {
        @Override
        public void success(getAllPostResponse imageResponse, Response response) {
            Toast.makeText(context, R.string.upload_ok, Toast.LENGTH_LONG);

            try {
                mAdapter = new MyAdapter(context, imageResponse.data);
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