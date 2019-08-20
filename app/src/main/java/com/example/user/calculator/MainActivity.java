package com.example.user.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //Button zero,one,two,three,four,five,six,seven,eight,nine,point,div,mul,sub,add,lbracket,rbracket,per,cut;
    private int[] numericButtons = {R.id.point, R.id.zero, R.id.one, R.id.two, R.id.three, R.id.four, R.id.five, R.id.six, R.id.seven, R.id.eight, R.id.nine};
    // IDs of all the operator buttons
    private int[] operatorButtons = {R.id.add, R.id.sub, R.id.mul, R.id.div,R.id.per,R.id.lbracket,R.id.rbracket};
    EditText expression;
    TextView result;
    Button cut,equal;
    Boolean flag = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        expression = (EditText)findViewById(R.id.expression);
        expression.setSelection(0);
        result = (TextView)findViewById(R.id.result);
        cut = (Button)findViewById(R.id.cut);
        equal = (Button)findViewById(R.id.equals);
        
        setNumericOnClickListener();
        setOperatorOnClickListener();
        cut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuffer st = new StringBuffer(expression.getText().toString());
                if(st.length()>0){
                    expression.setText(st.substring(0,st.length()-1));
                }
                if(st.length()==0){
                    result.setText("");
                }
                //expression.setSelection(0);

            }
        });

        cut.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                expression.setText("");
                return false;
            }
        });

        equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator();
                    String res = expressionEvaluator.evaluate(expressionEvaluator.toPostfix(expression.getText().toString()));
                    result.setText(res);
                    expression.setText(res);
                    flag = true;
                    //expression.setText("");
                }catch(Exception e){
                    Toast.makeText(MainActivity.this,"Check Your Expression!",Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void setNumericOnClickListener() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button btn = (Button)v;

                expression.setText(expression.getText().toString()+btn.getText().toString());
            }
        };
        for (int id : numericButtons) findViewById(id).setOnClickListener(listener);
    }

    private void setOperatorOnClickListener() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button btn = (Button)v;
                expression.setText(expression.getText().toString()+btn.getText().toString());
            }
        };
        for(int id : operatorButtons) findViewById(id).setOnClickListener(listener);
    }


}
