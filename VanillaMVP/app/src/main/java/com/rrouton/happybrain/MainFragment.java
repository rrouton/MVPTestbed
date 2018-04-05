package com.rrouton.happybrain;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class MainFragment extends Fragment implements MainContract.View {

    private MainContract.Presenter mainPresenter;

    private TextView testText;
    private Button testButton;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.main_fragment, container, false);
        testText = root.findViewById(R.id.testTextView);

        testButton = root.findViewById(R.id.testButton);
        testButton.setOnClickListener(__ -> mainPresenter.testButtonClicked());
        return root;
    }

    @Override
    public void setPresenter(MainContract.Presenter mainPresenter) {
        this.mainPresenter = mainPresenter;
    }

    @Override
    public void setTestText(String text) {
        testText.setText(text);
    }
}
