package com.dastan.m5lesson11.ui.adapter;

import android.content.Context;
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
import com.dastan.m5lesson11.data.entity.ForecastEntity;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;

public class WeatherWeekAdapter extends RecyclerView.Adapter<WeatherWeekAdapter.WeekViewHolder> {

    private Context mContext;
    private ArrayList<ForecastEntity> weekData;

    public WeatherWeekAdapter(Context context, ArrayList<ForecastEntity> forecastEntityArrayList) {
        mContext = context;
        weekData = forecastEntityArrayList;

    }

    @NonNull
    @Override
    public WeekViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.list_weather, parent, false);
        return new WeekViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WeekViewHolder holder, int position) {
        ForecastEntity forecastEntity = weekData.get(position);

        String imgUrl = forecastEntity.getWeekWeatherIcon();
        String day = forecastEntity.getWeekDay();
        String maxTemp = forecastEntity.getMaxTemp();
        String minTemp = forecastEntity.getMinTemp();

        holder.dayWeekText.setText(day);
        holder.maxTempWeek.setText(maxTemp);
        holder.minTempWeek.setText(minTemp);
        Glide.with(mContext)
                .load("http://openweathermap.org/img/wn/"
                        + forecastEntity.getList().get(0).getWeather().get(0).getIcon()
                        + "@2x.png")
                .into(holder.weatherWeekIcon);
    }

    @Override
    public int getItemCount() {
        return weekData.size();
    }

    public class WeekViewHolder extends RecyclerView.ViewHolder {

//        @BindView(R.id.weekWeatherIcon)
//        ImageView weatherWeekIcon;
//        @BindView(R.id.weekDayText)
//        TextView dayWeekText;
//        @BindView(R.id.weekTempMaxText)
//        TextView maxTempWeek;
//        @BindView(R.id.weekTempMinText)
//        TextView minTempWeek;

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


