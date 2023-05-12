package com.example.mirealtravel;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;

public class TourSearchActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_search);

        //блок выпадающих меню с выбором

        Spinner spinnerForFromCities = (Spinner) findViewById(R.id.spinnerFrom);
        ArrayAdapter<CharSequence> adapterFrom = ArrayAdapter.createFromResource(this, R.array.departure_cities, android.R.layout.simple_spinner_item);
        adapterFrom.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerForFromCities.setAdapter(adapterFrom);
        spinnerForFromCities.setSelection(0);
        spinnerForFromCities.setOnItemSelectedListener(this);

        Spinner spinnerToResort = (Spinner) findViewById(R.id.spinnerTo);
        ArrayAdapter<CharSequence> adapterTo = ArrayAdapter.createFromResource(this, R.array.arrival_countries, android.R.layout.simple_spinner_item);
        adapterTo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerToResort.setAdapter(adapterTo);
        spinnerToResort.setSelection(0);
        spinnerToResort.setOnItemSelectedListener(this);

        Spinner spinnerNumDays = (Spinner) findViewById(R.id.spinnerOfDays);
        ArrayAdapter<CharSequence> adapterDays = ArrayAdapter.createFromResource(this, R.array.duration, android.R.layout.simple_spinner_item);
        adapterTo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerNumDays.setAdapter(adapterDays);
        spinnerNumDays.setSelection(0);
        spinnerNumDays.setOnItemSelectedListener(this);

        Spinner spinnerNumP = (Spinner) findViewById(R.id.spinnerNumP);
        ArrayAdapter<CharSequence> adapterP = ArrayAdapter.createFromResource(this, R.array.num_adults, android.R.layout.simple_spinner_item);
        adapterTo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerNumP.setAdapter(adapterP);
        spinnerNumP.setSelection(0);
        spinnerNumP.setOnItemSelectedListener(this);

        Spinner spinnerNumPС = (Spinner) findViewById(R.id.spinnerNumPС);
        ArrayAdapter<CharSequence> adapterPС = ArrayAdapter.createFromResource(this, R.array.num_children, android.R.layout.simple_spinner_item);
        adapterTo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerNumPС.setAdapter(adapterP);
        spinnerNumPС.setSelection(0);
        spinnerNumPС.setOnItemSelectedListener(this);

        //выпадающее меню календаря для выбора даты вылета

        EditText editTextDate = (EditText) findViewById(R.id.editTextDate);

        editTextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });

        //переход на активити с результатом поиска

        ImageView searchBtn = (ImageView) findViewById(R.id.search_btn);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToSearResult = new Intent(TourSearchActivity.this, SearchResultActivity.class);
                startActivity(intentToSearResult);
            }
        });

        //переход на активити профиля пользователя

        ImageView profileBtn = (ImageView) findViewById(R.id.profileBtn);

        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToProfile = new Intent(TourSearchActivity.this, ProfileActivity.class);
                startActivity(intentToProfile);
            }
        });


    }
    //метод реализации календаря
    public void showDatePicker(){
        EditText editTextDate = (EditText) findViewById(R.id.editTextDate);
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String date = dayOfMonth + "-" + (month + 1) + "-" + year;
                        editTextDate.setText(date);
                    }
                },
                year,
                month,
                dayOfMonth);
        datePickerDialog.show();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        // Получаем ссылку на TextView, используемый в Spinner
        TextView selectedView = (TextView) view;

        // Устанавливаем гравитацию вправо
        selectedView.setGravity(Gravity.END);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}