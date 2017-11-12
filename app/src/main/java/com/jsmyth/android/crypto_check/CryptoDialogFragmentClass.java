package com.jsmyth.android.crypto_check;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;


public class CryptoDialogFragmentClass extends DialogFragment {

    public CryptoDialogFragmentClass() {
    }


    ActivityFragmentCommunicator communicator;

    LinearLayout btc,eth;
    public static CryptoDialogFragmentClass newInstance(String title) {
        CryptoDialogFragmentClass frag = new CryptoDialogFragmentClass();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       return inflater.inflate(R.layout.dialog_view,container); // return layout
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Get field from view
        eth = (LinearLayout) view.findViewById(R.id.linearLayout_eth);
        btc = (LinearLayout) view.findViewById(R.id.linearLayout_btc);

        communicator = (ActivityFragmentCommunicator)getActivity();

        // Fetch arguments from bundle and set title
        String title = getArguments().getString("title", "Enter Name");
        getDialog().setTitle(title);


        btc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
                communicator.onDataQuery("BTC");
            }
        });

        eth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
                communicator.onDataQuery("ETH");
            }
        });


    }
}
