package com.dastan.m5lesson11.ui.onboard;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.dastan.m5lesson11.R;
import com.dastan.m5lesson11.data.SampleData;
import com.dastan.m5lesson11.ui.main.MainActivity;

import java.util.ArrayList;

public class OnBoardActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ViewPager viewPager;
    private LinearLayout linearLayout;
    private TextView[] dots;
    private Button nextBtn;
    private Button finishBtn;
    private int currentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_board);
        initViews();
        initListeners();
        initViewPager();
        addDotsIndicator(0);
    }

    private void initViewPager() {
        ViewPagerAdapter  viewPagerAdapter = new ViewPagerAdapter();
        viewPager.setAdapter(viewPagerAdapter);
        viewPagerAdapter.update(getData());
    }

    public void addDotsIndicator(int position){
        dots = new TextView[3];
        linearLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++){
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.colorWhite));

            linearLayout.addView(dots[i]);
        }

        if (dots.length > 0){
            dots[position].setTextColor(getResources().getColor(R.color.colorGray));
        }
    }

    //findViewById
    private void initViews(){
        toolbar = findViewById(R.id.appBar);
        setSupportActionBar(toolbar);
        viewPager = findViewById(R.id.viewPager);
        linearLayout = findViewById(R.id.dotLayout);

        nextBtn = findViewById(R.id.btnNext);
        finishBtn = findViewById(R.id.btnFinish);
    }

    //setOnClick(this)
    private void initListeners(){
        viewPager.addOnPageChangeListener(viewListener);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(currentPage +1);
            }
        });

        finishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.start(getBaseContext());
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_onboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.skip:
                MainActivity.start(this);
                Log.e("ron", "skip");
                finish();
                break;
        }
        return true;
    }

    public static void start(Context context) {
        context.startActivity(new Intent(context, OnBoardActivity.class));
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDotsIndicator(position);
            currentPage = position;

            if (position == 0){
                nextBtn.setEnabled(true);
                finishBtn.setEnabled(false);
                nextBtn.setVisibility(View.VISIBLE);
                finishBtn.setVisibility(View.INVISIBLE);


            } else if (position == 1){
                nextBtn.setEnabled(true);
                finishBtn.setEnabled(false);
                nextBtn.setVisibility(View.VISIBLE);
                finishBtn.setVisibility(View.INVISIBLE);

            } else if (position == 2){
                nextBtn.setEnabled(true);
                finishBtn.setEnabled(true);
                nextBtn.setVisibility(View.INVISIBLE);
                finishBtn.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private ArrayList<SampleData> getData(){
        ArrayList<SampleData> list = new ArrayList<>();

        list.add(new SampleData("Cristiano Ronaldo",R.drawable.cris1 ));
        list.add(new SampleData("Dos Santos",R.drawable.cris2 ));
        list.add(new SampleData("Aveiro",R.drawable.cris3 ));

        return list;
    }
}
