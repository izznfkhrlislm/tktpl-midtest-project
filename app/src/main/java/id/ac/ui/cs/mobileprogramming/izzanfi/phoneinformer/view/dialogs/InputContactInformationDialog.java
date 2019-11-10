package id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.view.dialogs;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.R;

public class InputContactInformationDialog extends DialogFragment {

    public interface OnInputReceived {
        void sendContactInput(String phoneNumber, String providerName);
    }

    public OnInputReceived onInputReceived;

    private EditText phoneNumberEditText;
    private EditText providerNameEditText;
    private Button actionOk, actionCancel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.input_contact_info_dialog, container, false);
        actionOk = view.findViewById(R.id.action_ok);
        actionCancel = view.findViewById(R.id.action_cancel);
        phoneNumberEditText = view.findViewById(R.id.phoneNumberInput);
        providerNameEditText = view.findViewById(R.id.providerNameInput);

        actionCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });

        actionOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNumber = phoneNumberEditText.getText().toString();
                String providerName = providerNameEditText.getText().toString();
                onInputReceived.sendContactInput(phoneNumber, providerName);
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
