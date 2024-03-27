package com.example.kalkulator;

import static android.text.method.TextKeyListener.clear;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView resultTextView;
    private double operand1 = Double.NaN;
    private String operator = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTextView = findViewById(R.id.resultTextView);

        // Set OnClickListener for numeric buttons
        setNumericOnClickListener();

        // Set OnClickListener for operator buttons
        setOperatorOnClickListener();

        // Set OnClickListener for equals button
        findViewById(R.id.btnEquals).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compute();
            }
        });

        // Set OnClickListener for clear button
        findViewById(R.id.btnClear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear();
            }
        });
    }

    private void setNumericOnClickListener() {
        int[] numericButtonIds = {
                R.id.btnZero, R.id.btnOne, R.id.btnTwo, R.id.btnThree,
                R.id.btnFour, R.id.btnFive, R.id.btnSix, R.id.btnSeven,
                R.id.btnEight, R.id.btnNine, R.id.btnDecimal
        };

        for (int id : numericButtonIds) {
            findViewById(id).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Button button = (Button) v;
                    resultTextView.append(button.getText());
                }
            });
        }
    }

    private void setOperatorOnClickListener() {
        int[] operatorButtonIds = {R.id.btnAdd, R.id.btnSubtract, R.id.btnMultiply, R.id.btnDivide};

        for (int id : operatorButtonIds) {
            findViewById(id).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    operator = ((Button) v).getText().toString();
                    String text = resultTextView.getText().toString();
                    operand1 = Double.parseDouble(text);
                    resultTextView.setText("");
                }
            });
        }
    }

    private void compute() {
        if (!Double.isNaN(operand1)) {
            String text = resultTextView.getText().toString();
            double operand2 = Double.parseDouble(text);
            double
                    result = Double.NaN;
            switch (operator) {
                case "+":
                    result = operand1 + operand2;
                    break;
                case "-":
                    result = operand1 - operand2;
                    break;
                case "*":
                    result = operand1 * operand2;
                    break;
                case "/":
                    if (operand2 != 0)
                        result = operand1 / operand2;
                    break;
            }

            if (!Double.isNaN(result)) {
                resultTextView.setText(String.valueOf(result));
                operand1 = result;
            }
        }
    }

    private void clear() {
        operand1 = Double.NaN;
        operator = "";
        resultTextView.setText("");
    }
}
