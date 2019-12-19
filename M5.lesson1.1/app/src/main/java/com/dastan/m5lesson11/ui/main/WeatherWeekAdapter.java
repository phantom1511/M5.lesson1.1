package com.dastan.m5lesson11.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dastan.m5lesson11.R;
import com.dastan.m5lesson11.data.entity.CurrentWeather;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class WeatherWeekAdapter extends RecyclerView.Adapter<WeatherWeekAdapter.WeekViewHolder> {

    private List<CurrentWeather> weekData;

    public WeatherWeekAdapter(List<CurrentWeather> weekData) {
        this.weekData = weekData;
    }

    @NonNull
    @Override
    public WeekViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_weather, parent, false);
        return new WeekViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WeekViewHolder holder, int position) {
        holder.maxTempWeek.setText(weekData.get(position).getMain().getTempMax().toString() + "°");
        holder.minTempWeek.setText(weekData.get(position).getMain().getTempMin().toString() + "°");
        Glide.with(holder.weatherWeekIcon.getContext())
                .load("http://openweathermap.org/img/wn/"
                        + weekData.get(position).getWeather().get(0).getIcon() + "@2x.png")
                .into(holder.weatherWeekIcon);
        try {
           String date = forCastDate(weekData.get(position).getDt_txt());
            holder.dayWeekText.setText(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

    private String forCastDate(String s) throws ParseException {
        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = dt.parse(s);

        SimpleDateFormat outDt = new SimpleDateFormat("dd");
        String parseDate = outDt.format(date);
        return parseDate;
    }

    @Override
    public int getItemCount() {
        return weekData.size();
    }

    public class WeekViewHolder extends RecyclerView.ViewHolder {

        private ImageView weatherWeekIcon;
        private TextView dayWeekText;
        private TextView maxTempWeek;
        private TextView minTempWeek;

        public WeekViewHolder(@NonNull View itemView) {
            super(itemView);

            weatherWeekIcon = itemView.findViewById(R.id.weekWeatherIcon);
            dayWeekText = itemView.findViewById(R.id.weekDayText);
            maxTempWeek = itemView.findViewById(R.id.weekTempMaxText);
            minTempWeek = itemView.findViewById(R.id.weekTempMinText);

        }
    }
}


