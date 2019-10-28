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

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    public void displayDetails(String title, String description) {
        TextView detailTitle = getActivity().findViewById(R.id.textViewTitle);
        detailTitle.setText(title);

        TextView detailDescription = getActivity().findViewById(R.id.textContent);
        detailDescription.setText(description);
    }

}
