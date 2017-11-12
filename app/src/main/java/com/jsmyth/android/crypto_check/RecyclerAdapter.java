package com.jsmyth.android.crypto_check;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<CrypToViewHolder> {

    //declare list
    List<CryptoX> cryptoXList = new ArrayList<>();
    List<Double> listBtc = new ArrayList<>();
    List<Double> listEth = new ArrayList<>();
    Context context;

    InterfaceDeleteListener listener;

    public interface InterfaceDeleteListener {

        void onButtonYesCLicked();
    }

    public RecyclerAdapter(InterfaceDeleteListener listener,Context context,List<CryptoX> cryptoXList, List<Double> listBtc, List<Double> listEth) {

        //initialize variables
        this.cryptoXList = cryptoXList;
        this.context = context;
        this.listBtc = listBtc;
        this.listEth = listEth;
        this.listener = listener;
    }

    @Override
    public CrypToViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //inflate recycler custom row view
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_view, parent, false);


        //instantiate view holder
        CrypToViewHolder crypToViewHolder = new CrypToViewHolder(rootView, listBtc, listEth);

        return crypToViewHolder;

    }

    @Override
    public void onBindViewHolder(CrypToViewHolder holder, final int position) {

        //get model class attributes
        CryptoX cryptoX = cryptoXList.get(position);

        String coinType = cryptoX.getCoinType();

        //set up item view
        holder.setUpSpinner();
        holder.onClickSpinner(coinType);
        holder.setUpImage(coinType);
        holder.setCoinType(coinType);

        //handle delete of card
        holder.imageViewDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {




                //alert dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Click OK to delete card");


                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        delete(position);

                        if (!(cryptoXList.size() > 0)) {

                            listener.onButtonYesCLicked();
                        }

                    }
                });
                builder.show();

            }
        });


    }

    public void delete(int position) {
        //removes the row
        cryptoXList.remove(position);
        notifyItemRemoved(position);
        notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {

        //return number items to populate
        return cryptoXList.size();

    }
}
