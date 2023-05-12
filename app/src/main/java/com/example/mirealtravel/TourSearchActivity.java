package com.example.mirealtravel;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
                Spinner spinnerFrom = (Spinner) findViewById(R.id.spinnerFrom);
                String departureCity = spinnerFrom.getSelectedItem().toString();

                Spinner spinnerTo = (Spinner) findViewById(R.id.spinnerTo);
                String arrivalCountry = spinnerTo.getSelectedItem().toString();

                EditText editTextDate = (EditText) findViewById(R.id.editTextDate);
                String departureDate = editTextDate.getText().toString();

                Spinner spinnerNumDays = (Spinner) findViewById(R.id.spinnerOfDays);
                int numDays = Integer.parseInt(spinnerNumDays.getSelectedItem().toString());

                Spinner spinnerNumP = (Spinner) findViewById(R.id.spinnerNumP);
                int numAdults = Integer.parseInt(spinnerNumP.getSelectedItem().toString());

                Spinner spinnerNumPC = (Spinner) findViewById(R.id.spinnerNumPС);
                int numChildren = Integer.parseInt(spinnerNumPC.getSelectedItem().toString());

                // Вызов метода searchTours() для поиска туров
                searchTours(departureCity, arrivalCountry, departureDate, numDays, numAdults, numChildren);

                // Переход на активити с результатом поиска
                Intent intentToSearchResult = new Intent(TourSearchActivity.this, SearchResultActivity.class);
                intentToSearchResult.putParcelableArrayListExtra("tours", (ArrayList<? extends Parcelable>) tours);

                startActivity(intentToSearchResult);
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

    List<Tour> tours = new ArrayList<>();

    private void searchTours(String departureCity, String arrivalCountry, String departureDate, int numDays, int numAdults, int numChildren) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DatabaseConnection.getConnection();

            String query = "SELECT * FROM Departures " +
                    "INNER JOIN Cities ON Departures.city_id = Cities.id " +
                    "INNER JOIN Countries ON Departures.country_id = Countries.id " +
                    "INNER JOIN Tours ON Departures.tour_id = Tours.id " +
                    "WHERE Cities.name = ? " +
                    "AND Countries.name = ? " +
                    "AND Departures.departure_date = ? " +
                    "AND Departures.duration = ? " +
                    "AND Departures.num_adults = ? " +
                    "AND Departures.num_children = ?";
            stmt = conn.prepareStatement(query);

            // Установка значений параметров
            stmt.setString(1, departureCity);
            stmt.setString(2, arrivalCountry);
            stmt.setString(3, departureDate);
            stmt.setInt(4, numDays);
            stmt.setInt(5, numAdults);
            stmt.setInt(6, numChildren);

            // Выполнение запроса
            rs = stmt.executeQuery();

            tours.clear();

            // Обработка результатов
            while (rs.next()) {
                // Получение данных из результатов запроса и создание объектов Tour
                int tourId = rs.getInt("Tours.id");
                String tourName = rs.getString("Tours.name");
                String tourDescription = rs.getString("Tours.description");
                int tourStarRating = rs.getInt("Tours.star_rating");
                String tourImageUrl = rs.getString("Tours.image_url");
                double tourPrice = rs.getDouble("Tours.price");
                Tour tour = new Tour(tourId, tourName, tourDescription, tourStarRating, tourImageUrl, tourPrice);
                // Дополнительные поля, связанные с туром, могут быть получены из результатов запроса и установлены в объекте tour

                // Добавление объекта Tour в список туров
                tours.add(tour);
            }

            // Здесь вы можете использовать полученные результаты для отображения данных в вашем интерфейсе

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
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
                            String date = year + "-" + String.format("%02d", month + 1) + "-" + String.format("%02d", dayOfMonth);
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