package com.chilangolabs.reporteciudadano;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;

import com.chilangolabs.reporteciudadano.customelements.MontserratButton;

import java.util.Calendar;

public class AltaReporteActivity extends AppCompatActivity {

    MontserratButton selectDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_reporte);

        selectDate = (MontserratButton) findViewById(R.id.selectDate);

        Calendar c = Calendar.getInstance();
        final int startYear = c.get(Calendar.YEAR);
        final int startMonth = c.get(Calendar.MONTH);
        final int startDay = c.get(Calendar.DAY_OF_MONTH);

        selectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(AltaReporteActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int date) {
//                        edtxActivateAccountBirthDay.setText(date + "/" + month + "/" + year);
                        finish();
                    }
                }, startYear, startMonth, startDay);

                datePickerDialog.show();

            }
        });

    }
}
