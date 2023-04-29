package com.example.application1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView result, solution;
    MaterialButton buttonC, buttonBracketopen, buttonBracketclose;
    MaterialButton buttonDivide, buttonMultiply, buttonPlus, buttonMinus, buttonequals;
    MaterialButton button0, button1, button2, button3, button4, button5, button6, button7, button8, button9;
    MaterialButton buttonAC, buttondot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result = findViewById(R.id.result_tv);
        solution = findViewById(R.id.solution_tv);
        assignId(buttonC, R.id.button_c);
        assignId(buttonBracketopen, R.id.button_bo);
        assignId(buttonBracketclose, R.id.button_bc);
        assignId(buttonDivide, R.id.button_divide);
        assignId(buttonMultiply, R.id.button_multiply);
        assignId(buttonPlus, R.id.button_plus);
        assignId(buttonMinus, R.id.button_subtract);
        assignId(buttonequals, R.id.button_equal);
        assignId(button0, R.id.button_0);
        assignId(button1, R.id.button_1);
        assignId(button2, R.id.button_2);
        assignId(button3, R.id.button_3);
        assignId(button4, R.id.button_4);
        assignId(button5, R.id.button_5);
        assignId(button6, R.id.button_6);
        assignId(button7, R.id.button_7);
        assignId(button8, R.id.button_8);
        assignId(button9, R.id.button_9);
        assignId(buttonAC, R.id.button_ac);
        assignId(buttondot, R.id.button_dot);

    }

    void assignId(MaterialButton btn, int id) {
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttontext = button.getText().toString();
        String datatocalculate = solution.getText().toString();
        if (buttontext.equals("AC")) {
            solution.setText("");
            result.setText("0");
            return;
        }
        if (buttontext.equals("=")) {
            solution.setText(result.getText());
            return;
        }
        if (buttontext.equals("C")) {
            datatocalculate = datatocalculate.substring(0, datatocalculate.length() - 1);
        } else {
            datatocalculate = datatocalculate + buttontext;
        }
        solution.setText(datatocalculate);
        String finalres = getresult(datatocalculate);
        if (!finalres.equals("err")) {
            result.setText(finalres);
        }
    }

    String getresult(String data) {
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalres = context.evaluateString(scriptable, data, "Javascript", 1, null).toString();
            if (finalres.endsWith(".0")) {
                finalres = finalres.replace(".0", "");
            }
            return finalres;
        } catch (Exception e) {
            return "err";
        }
    }
}