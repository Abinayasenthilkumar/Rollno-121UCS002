package com.multiply.easycalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView resulttv,solutiontv;
    MaterialButton buttonC,buttonopenbracket,buttonclosebracket;
    MaterialButton buttondivide,buttonmultiply,buttonplus,buttonminus,buttonequals;
    MaterialButton button0,button1,button2,button3,button4,button5,button6,button7,button8,button9;
    MaterialButton buttonAC,buttondot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resulttv=findViewById(R.id.result_tv);
        solutiontv=findViewById(R.id.solution_tv);
        assignId(buttonC,R.id.button_c);
        assignId(buttonopenbracket,R.id.button_open_bracket);
        assignId(buttonclosebracket,R.id.button_close_bracket);
        assignId(buttondivide,R.id.button_divide);
        assignId(button7,R.id.button_7);
        assignId(button8,R.id.button_8);
        assignId(button9,R.id.button_9);
        assignId(buttonmultiply,R.id.button_multiply);
        assignId(button4,R.id.button_4);
        assignId(button5,R.id.button_5);
        assignId(button6,R.id.button_6);
        assignId(buttonplus,R.id.button_plus);
        assignId(button1,R.id.button_1);
        assignId(button2,R.id.button_2);
        assignId(button3,R.id.button_3);
        assignId(buttonminus,R.id.button_minus);
        assignId(buttonAC,R.id.button_AC);
        assignId(button0,R.id.button_zero);
        assignId(buttondot,R.id.button_dot);
        assignId(buttonequals,R.id.button_equals);
    }
    void assignId(MaterialButton button,int id){
        button=findViewById(id);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button=(MaterialButton) view;
        String buttontext=button.getText().toString();
        String dataToCalculate=solutiontv.getText().toString();
        if(buttontext.equals("AC")){
            solutiontv.setText("");
            resulttv.setText("0");
            return;
        }
        if(buttontext.equals("="))
        {
           solutiontv.setText(resulttv.getText());
           return;
        }
        if (buttontext.equals("C")){
            dataToCalculate=dataToCalculate.substring(0,dataToCalculate.length()-1);

        }else {
            dataToCalculate=dataToCalculate+buttontext;

        }

        solutiontv.setText(dataToCalculate);
        String finalResult=getResult(dataToCalculate);
        if(!finalResult.equals("Err")){
            resulttv.setText(finalResult);
        }

    }
    String getResult(String data){
        try{
            Context context=Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable=context.initStandardObjects();
            String finalResult=context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(finalResult.endsWith(".0")){
                finalResult=finalResult.replace(".0","");
            }
            return finalResult;
        }catch(Exception e){
            return "Err";
        }

    }


}