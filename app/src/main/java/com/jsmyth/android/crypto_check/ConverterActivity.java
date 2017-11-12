package com.jsmyth.android.crypto_check;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class ConverterActivity extends AppCompatActivity {


    private  EditText currencyValue;
    private TextView cryptoValue,cryptoCoin;
    public static final int BASE_CRYPTO_VALUE = 1;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter);

        mToolbar = (Toolbar)findViewById(R.id.toolbar_converter);

        setSupportActionBar(mToolbar);


        ActionBar toolbar = getSupportActionBar();
        if (getSupportActionBar() != null) {

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        toolbar.setTitle(R.string.crypto_currency_converter);

        currencyValue = (EditText) findViewById(R.id.et_currencyValue);
        cryptoValue = (TextView) findViewById(R.id.textView_crytoValue);
        cryptoCoin = (TextView) findViewById(R.id.textView_crytoCoin);





        //get data passed from main activity
        final Double exchangeRate = getIntent().getDoubleExtra("baseRate", 0);
        String currencySymbol = getIntent().getStringExtra("currencySymbol");
        String coinSelected = getIntent().getStringExtra("coinType");

        //set data
        currencyValue.setHint(currencySymbol);
        cryptoCoin.setText(coinSelected+" = ");




        //make calcilations on text change
        currencyValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                if (!TextUtils.isEmpty(currencyValue.getText().toString())) {

                    double val = Double.valueOf(currencyValue.getText().toString());

                    double finalCryptoValue = (val / exchangeRate) * BASE_CRYPTO_VALUE;
                    cryptoValue.setText(String.valueOf(finalCryptoValue));
                } else {
                    cryptoValue.setText("0.0");
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.toolbar_home:
        }
        return super.onOptionsItemSelected(item);
    }
}
