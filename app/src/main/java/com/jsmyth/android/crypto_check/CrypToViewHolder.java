package com.jsmyth.android.crypto_check;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

// viewHolder class for the recyclerview
public class CrypToViewHolder extends RecyclerView.ViewHolder {

    private Spinner spinnerCrypto;
    private TextView textViewCrytoValue;
    private TextView textViewSymbol;
    ImageView imageCoin, imageViewDelete;
    private Context ctx;

    private List<Double> listBtc = new ArrayList<>();
    private List<Double> listEth = new ArrayList<>();

    Double currentItemRate;

    String[] symbols;
    Resources resources;
    String mSymbol;
    private String coinType = "";


    public CrypToViewHolder(View itemView, List<Double> listBtc, List<Double> listEth) {
        super(itemView);

        this.listBtc = listBtc;
        this.listEth = listEth;


        ctx = itemView.getContext();
        spinnerCrypto = (Spinner) itemView.findViewById(R.id.spinner_crypto);
        textViewCrytoValue = (TextView) itemView.findViewById(R.id.tv_crytoValue);
        textViewSymbol = (TextView) itemView.findViewById(R.id.tv_symbol);
        imageCoin = (ImageView) itemView.findViewById(R.id.image_coin);
        imageViewDelete = (ImageView) itemView.findViewById(R.id.image_view_delete);

        resources = ctx.getResources();
        symbols = resources.getStringArray(R.array.symbol);


        //set up on click listener
        itemView.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Intent converterIntent = new Intent(ctx, ConverterActivity.class);


                String currentSymbol = mSymbol;
                converterIntent.putExtra("baseRate", currentItemRate);
                converterIntent.putExtra("currencySymbol", currentSymbol);
                converterIntent.putExtra("coinType", coinType);
                ctx.startActivity(converterIntent);
            }
        });


    }

    public void setCoinType(String coin) {
        coinType = coin;
    }


    public void setUpSpinner() {

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(ctx,
                R.array.currency, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinnerCrypto.setAdapter(adapter);


    }


    //set up spinner onclick
    public void onClickSpinner(final String coinType) {

        spinnerCrypto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (coinType == "BTC") {
                    if (listBtc.size() > 0) {
                        textViewCrytoValue.setText(String.valueOf(listBtc.get(position)));
                        currentItemRate = listBtc.get(position);
                        textViewSymbol.setText(symbols[position]);
                        mSymbol = symbols[position];
                    }
                } else if (coinType == "ETH") {
                    if (listEth.size() > 0) {
                        textViewCrytoValue.setText(String.valueOf(listEth.get(position)));
                        currentItemRate = listEth.get(position);
                        textViewSymbol.setText(symbols[position]);
                        mSymbol = symbols[position];
                    }

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void setUpImage(String coinType) {

        if (coinType == "BTC") {
            imageCoin.setImageResource(R.drawable.bitcoin);
        } else {
            imageCoin.setImageResource(R.drawable.ethereum);
        }

    }

}
