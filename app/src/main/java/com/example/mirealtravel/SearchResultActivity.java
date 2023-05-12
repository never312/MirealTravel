package com.example.mirealtravel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class SearchResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        List<Tour> tours = getIntent().getParcelableArrayListExtra("tours");

        LinearLayout tourContainer = findViewById(R.id.tourContainer);

        for (Tour tour : tours) {
            // Создание карточки для каждого тура
            CardView cardView = (CardView) LayoutInflater.from(this).inflate(R.layout.tour_card_view, tourContainer, false);

            ImageView tourImageView = cardView.findViewById(R.id.tourImageView);
            TextView tourNameTextView = cardView.findViewById(R.id.tourNameTextView);
            TextView tourPriceTextView = cardView.findViewById(R.id.tourPriceTextView);
            TextView tourRatingTextView = cardView.findViewById(R.id.tourRatingTextView);
            TextView tourDescriptionTextView = cardView.findViewById(R.id.tourDescriptionTextView);

            // Заполнение данных тура в карточке
            Glide.with(this).load(tour.getImageUrl()).into(tourImageView);
            tourNameTextView.setText(tour.getName());
            tourPriceTextView.setText(String.valueOf(tour.getPrice()));
            tourRatingTextView.setText(String.valueOf(tour.getStarRating()));
            tourDescriptionTextView.setText(tour.getDescription());

            // Добавление карточки в контейнер
            tourContainer.addView(cardView);
        }
    }
}
