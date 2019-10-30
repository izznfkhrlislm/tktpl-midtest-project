package id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.R;

public class PhoneInformationFragment extends Fragment {

    public PhoneInformationFragment() {

    }

    public static PhoneInformationFragment newInstance(String title, String description) {
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putString("description", description);
        PhoneInformationFragment fragment = new PhoneInformationFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_phone_information, container, false);
        return view;
    }
}
