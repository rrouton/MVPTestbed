package com.rrouton.happybrain;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rrouton.happybrain.models.flickr.Photo;

import java.util.List;

public class MainFragment extends Fragment implements MainContract.View {

    private MainContract.Presenter mainPresenter;
    private RecyclerView recyclerView;
    private MainAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        mainPresenter.start();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.main_fragment, container, false);

        recyclerView = root.findViewById(R.id.photosRecyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MainAdapter();
        recyclerView.setAdapter(adapter);
        return root;
    }

    @Override
    public void setPresenter(MainContract.Presenter mainPresenter) {
        this.mainPresenter = mainPresenter;
    }

    @Override
    public void setPhotos(List<Photo> photos) {
        adapter.setPhotoList(photos);
        adapter.notifyDataSetChanged();
    }
}
