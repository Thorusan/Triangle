package com.example.si01016726.triangle;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    public static final String UNIT_MM = "mm";
    public static final String UNIT_CM = "cm";
    public static final String UNIT_M = "m";

    private CanvasView customCanvas;
    private EditText editSideA;
    private EditText editSideB;
    private EditText editSideC;
    private Button btnClear;

    private Spinner spinnerA;
    private Spinner spinnerB;
    private Spinner spinnerC;

    private String unitA = UNIT_MM;
    private String unitB = UNIT_CM;
    private String unitC = UNIT_M;

    private String previousUnitA = UNIT_MM;
    private String previousUnitB = UNIT_CM;
    private String previousUnitC = UNIT_M;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        customCanvas = (CanvasView) findViewById(R.id.signature_canvas);

        editSideA = (EditText) findViewById(R.id.editTextA);
        editSideB = (EditText) findViewById(R.id.editTextB);
        editSideC = (EditText) findViewById(R.id.editTextC);
        btnClear = (Button) findViewById(R.id.btnClear);

        spinnerA = (Spinner) findViewById(R.id.spinnerA);
        spinnerB = (Spinner) findViewById(R.id.spinnerB);
        spinnerC = (Spinner) findViewById(R.id.spinnerC);

        spinnerA.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String valueStrA = editSideA.getText().toString();
                double valueA;

                switch (position) {
                    case 0:
                        unitA = UNIT_MM;
                        valueA=convertParseDouble(valueStrA,unitA, previousUnitA);
                        previousUnitA = UNIT_MM;
                        updateSideA(valueA);
                        break;
                    case 1:
                        unitA = UNIT_CM;
                        valueA=convertParseDouble(valueStrA,unitA, previousUnitA);
                        previousUnitA = UNIT_CM;
                        updateSideA(valueA);
                        break;
                    case 2:
                        unitA = UNIT_M;
                        valueA=convertParseDouble(valueStrA,unitA, previousUnitA);
                        previousUnitA = UNIT_M;
                        updateSideA(valueA);
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerB.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String valueStrB = editSideB.getText().toString();
                double valueB;

                switch (position) {
                    case 0:
                        unitB = UNIT_MM;
                        valueB=convertParseDouble(valueStrB,unitB, previousUnitB);
                        previousUnitB = UNIT_MM;
                        updateSideB(valueB);
                        break;
                    case 1:
                        unitB = UNIT_CM;
                        valueB=convertParseDouble(valueStrB,unitB, previousUnitB);
                        previousUnitB = UNIT_CM;
                        updateSideB(valueB);
                        break;
                    case 2:
                        unitB = UNIT_M;
                        valueB=convertParseDouble(valueStrB,unitB, previousUnitB);
                        previousUnitB = UNIT_M;
                        updateSideB(valueB);
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerC.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String valueStrC = editSideC.getText().toString();
                double valueC;
                double test;

                switch (position) {
                    case 0:
                        unitC = UNIT_MM;
                        valueC=convertParseDouble(valueStrC,unitC, previousUnitC);
                        previousUnitC = UNIT_MM;
                        updateSideC(valueC);
                        break;
                    case 1:
                        unitC = UNIT_CM;
                        valueC=convertParseDouble(valueStrC,unitC, previousUnitC);
                        previousUnitC = UNIT_CM;
                        updateSideC(valueC);
                        break;
                    case 2:
                        unitC = UNIT_M;
                        valueC=convertParseDouble(valueStrC,unitC, previousUnitC);
                        previousUnitC = UNIT_M;
                        updateSideC(valueC);
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editSideA.removeTextChangedListener(watcherA);
                editSideA.setText("");
                editSideA.addTextChangedListener(watcherA);

                editSideB.removeTextChangedListener(watcherB);
                editSideB.setText("");
                editSideB.addTextChangedListener(watcherB);

                editSideC.removeTextChangedListener(watcherC);
                editSideC.setText("");
                editSideC.addTextChangedListener(watcherC);

            }
        });

        editSideA.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    customCanvas.setEmphasizeLine(1);
                }
            }
        });

        editSideB.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    customCanvas.setEmphasizeLine(2);
                }
            }
        });

        editSideC.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    customCanvas.setEmphasizeLine(3);
                }
            }
        });


        editSideA.addTextChangedListener(watcherA);
        editSideB.addTextChangedListener(watcherB);
        editSideC.addTextChangedListener(watcherC);
    }


    private void updateSideA(double value) {
        editSideA.removeTextChangedListener(watcherA);
        editSideA.setText(String.format(Locale.getDefault(), "%.1f", value));
        editSideA.addTextChangedListener(watcherA);
    }

    private void updateSideB(double value) {
        editSideB.removeTextChangedListener(watcherB);
        editSideB.setText(String.format(Locale.getDefault(), "%.1f", value));
        editSideB.addTextChangedListener(watcherB);
    }

    private void updateSideC(double value) {
        editSideC.removeTextChangedListener(watcherC);
        editSideC.setText(String.format(Locale.getDefault(), "%.1f", value));
        editSideC.addTextChangedListener(watcherC);
    }

    private double convertParseDouble(String value , String unit, String previousUnit) {
        try {
            if (unit==previousUnit) {
                return Double.parseDouble(value);
            }
            else if ((unit==UNIT_MM)&&(previousUnit==UNIT_CM)) {
                return Double.parseDouble(value)*10d;
            }
            else if ((unit==UNIT_MM)&&(previousUnit==UNIT_M)) {
                return Double.parseDouble(value)*1000d;
            }
            else if ((unit==UNIT_CM)&&(previousUnit==UNIT_MM)) {
                return (Double.parseDouble(value)/10d);
            }
            else if ((unit==UNIT_CM)&&(previousUnit==UNIT_M)) {
                return (Double.parseDouble(value)*100d);
            }
            else if ((unit==UNIT_M)&&(previousUnit==UNIT_MM)) {
                return  (Double.parseDouble(value)/1000d);
            }
            else if ((unit==UNIT_M)&&(previousUnit==UNIT_CM)) {
                return  (Double.parseDouble(value)/100d);
            }

            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private double parseDouble(String value, String unit) {
        try {
            if (unit==UNIT_MM) {
                return Double.parseDouble(value);
            }
            if (unit==UNIT_CM) {
                return (Double.parseDouble(value)*10d);
            }
            else if (unit==UNIT_M) {
                return  (Double.parseDouble(value)*1000d);
            }
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return 0;
        }
    }



    private final TextWatcher watcherA = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            double hypothenuseLength = 0.0;
            double valueA, valueB,valueC;
            String valueStrA = editSideA.getText().toString();
            String valueStrB = editSideB.getText().toString();
            String valueStrC = editSideC.getText().toString();

            if (valueStrA.isEmpty()) {
                valueA = 0.0;
            } else {
                valueA = parseDouble(valueStrA,unitA);
            }
            if (valueStrB.isEmpty()) {
                valueB = 0.0;
            } else {
                valueB = parseDouble(valueStrB,unitB);
            }

            if (valueStrC.isEmpty()) {
                valueC= 0;
            } else {
                //valueB=0;   // wenn hypothenuse ist already entered, then calculate side
                valueC=parseDouble(valueStrC,unitC);
            }
            if (valueB > 0) {
                hypothenuseLength = HelperClass.CalculateHypotenuse(valueA, valueB, unitC);
                updateSideC(hypothenuseLength);
            }
            else if (valueC > 0) {
                double side = HelperClass.CalculateSide(valueC, valueA, unitB);
                updateSideB(side);
            }
        }
    };

    private final TextWatcher watcherB = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            double hypothenuseLength = 0.0;
            double valueA, valueB, valueC;
            String valueStrA = editSideA.getText().toString();
            String valueStrB = editSideB.getText().toString();
            String valueStrC = editSideC.getText().toString();

            if (valueStrA.isEmpty()) {
                valueA = 0.0;
            } else {
                valueA = parseDouble(valueStrA,unitA);
            }
            if (valueStrB.isEmpty()) {
                valueB = 0.0;
            } else {
                valueB = parseDouble(valueStrB,unitB);
            }

            if(valueStrC.isEmpty()) {
                valueC=0d;
            } else {
                //valueA=0; // wenn hypothenuse ist already entered, then calculate side
                valueC=parseDouble(valueStrC,unitC);
            }

            if (valueA > 0) {
                hypothenuseLength = HelperClass.CalculateHypotenuse(valueB, valueA, unitC);
                updateSideC(hypothenuseLength);
            }
            else if (valueC>0) {
                double side = HelperClass.CalculateSide(valueC, valueB, unitA);
                updateSideA(side);
            }
        }
    };

    private TextWatcher watcherC = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            double sideLength = 0.0;
            double valueA, valueB, valueC;
            String valueStrA = editSideA.getText().toString();
            String valueStrB = editSideB.getText().toString();
            String valueStrC = editSideC.getText().toString();

            if (valueStrA.isEmpty()) {
                valueA = 0.0;
            } else {
                valueA = parseDouble(valueStrA,unitA);
            }
            if (valueStrB.isEmpty()) {
                valueB = 0.0;
            } else {
                valueB = parseDouble(valueStrB,unitB);
            }
            if (valueStrC.isEmpty()) {
                valueC = 0.0;
            } else {
                valueC = parseDouble(valueStrC,unitC);
            }

            if (valueA > 0.0) {
                sideLength = HelperClass.CalculateSide(valueC, valueA, unitB);
                updateSideB(sideLength);

            }
            else if (valueB > 0.0) {
                sideLength = HelperClass.CalculateSide(valueC, valueB, unitA);
                updateSideA(sideLength);
            }
        }
    };

}
