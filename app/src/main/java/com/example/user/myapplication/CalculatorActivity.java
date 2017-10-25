package com.example.user.myapplication;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class CalculatorActivity extends AppCompatActivity {

    private ICalculator mCalculator= new BackendCalculatorDebug();
    private TextView mResultTextView;
    private EditText mInputEditText;
    private View mButtonEq;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void InitView(){
        mInputEditText=(EditText) findViewById(R.id.input_txt_edit_text);
        mButtonEq= findViewById(R.id.button_eq_button);
        mResultTextView=(TextView) findViewById(R.id.output_text_text_view);


        mButtonEq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final String result=mCalculator.evaluate(mInputEditText.getText().toString());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                showResult(result);
                            }
                        });
                    }
                }).start();
            }
        });

    }
    private void showResult (String r) {
        mResultTextView.setText(r);
    }
}