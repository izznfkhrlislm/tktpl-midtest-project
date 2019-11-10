package id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.view.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.R;

public class InputUserFullNameDialog extends DialogFragment {

    public interface OnInputReceived {
        void sendInput(String input);
    }

    public OnInputReceived onInputReceived;

    private EditText userFullNameEditText;
    private Button actionOk, actionCancel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.input_name_dialog, container, false);
        actionOk = view.findViewById(R.id.action_ok);
        actionCancel = view.findViewById(R.id.action_cancel);
        userFullNameEditText = view.findViewById(R.id.input);

        actionCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });

        actionOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("OK clicked!");
                String input = userFullNameEditText.getText().toString();
                System.out.println(input);
                onInputReceived.sendInput(input);
                getDialog().dismiss();
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            onInputReceived = (OnInputReceived) getTargetFragment();
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }
}
