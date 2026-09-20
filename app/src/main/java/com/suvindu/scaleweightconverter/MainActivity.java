package com.suvindu.scaleweightconverter;

import android.os.Bundle;
import android.text.*;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    EditText factorInput, scaleInput, realInput;
    TextView realResult, scaleResult;

    @Override public void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_main);
        factorInput=findViewById(R.id.factorInput);
        scaleInput=findViewById(R.id.scaleInput);
        realInput=findViewById(R.id.realInput);
        realResult=findViewById(R.id.realResult);
        scaleResult=findViewById(R.id.scaleResult);

        TextWatcher w=new TextWatcher(){
            public void beforeTextChanged(CharSequence s,int st,int c,int a){}
            public void onTextChanged(CharSequence s,int st,int b,int c){convert();}
            public void afterTextChanged(Editable e){}
        };
        factorInput.addTextChangedListener(w);
        scaleInput.addTextChangedListener(w);
        realInput.addTextChangedListener(w);
    }

    void convert() {
        double f=parse(factorInput);
        if(f<=0) f=1.28;
        String s=scaleInput.getText().toString().trim();
        realResult.setText(s.isEmpty() ? "Real weight: —" :
            "Real weight: "+fmt(parse(scaleInput)*f)+" g");
        String r=realInput.getText().toString().trim();
        scaleResult.setText(r.isEmpty() ? "Scale reading: —" :
            "Scale reading: "+fmt(parse(realInput)/f)+" g");
    }

    double parse(EditText e){ try{return Double.parseDouble(e.getText().toString().trim());}catch(Exception x){return 0;} }
    String fmt(double x){
        if(Math.abs(x-Math.round(x))<0.005) return String.format(Locale.US,"%.0f",x);
        return String.format(Locale.US,"%.1f",x);
    }
}
