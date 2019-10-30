package id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.R;

public class DetailFragment extends Fragment {

    public DetailFragment() {

    }

    public static DetailFragment newInstance(String title, String description) {
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putString("description", description);
        DetailFragment fragment = new DetailFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        TextView detailTitle = view.findViewById(R.id.textViewTitle);
        detailTitle.setText(getArguments().getString("title"));

        TextView detailDescription = view.findViewById(R.id.textContent);
        detailDescription.setText(getArguments().getString("description"));

        return view;
    }

}
