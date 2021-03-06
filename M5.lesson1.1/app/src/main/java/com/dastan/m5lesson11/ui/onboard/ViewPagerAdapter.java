package com.dastan.m5lesson11.ui.onboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.dastan.m5lesson11.R;
import com.dastan.m5lesson11.data.SampleData;

import java.util.ArrayList;

public class ViewPagerAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;
    private ArrayList<SampleData> sampleData = new ArrayList<>();

    public ViewPagerAdapter() {

    }

    public void update(ArrayList<SampleData> sampleData){
        this.sampleData = sampleData;
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return sampleData.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (RelativeLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        context = container.getContext();
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slider_layout, container, false);

        ImageView slideImageView = view.findViewById(R.id.onBoardImageView);
        TextView slideTextView = view.findViewById(R.id.textView);


        SampleData data = sampleData.get(position);
        slideImageView.setImageResource(data.getImg());
        slideTextView.setText(data.getTitle());

        container.addView(view);
        return view;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout) object);
    }
}
