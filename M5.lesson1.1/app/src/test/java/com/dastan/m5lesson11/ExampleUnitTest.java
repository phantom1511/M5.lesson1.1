package com.dastan.m5lesson11;

import android.content.SharedPreferences;

import androidx.annotation.Nullable;

import com.dastan.m5lesson11.data.PreferenceHelper;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;
import java.util.Set;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    private static final String IS_FIRST_LAUNCH = "isFirstLaunch";
    private static final String NAME_PREFS = "NAME_PREFS";
    private SharedPreferences sharedPreferences;
    private PreferenceHelper preferenceHelper;

    @Before
    public void setUp() {
        sharedPreferences = new SharedPreferences() {
            @Override
            public Map<String, ?> getAll() {
                return null;
            }

            @Nullable
            @Override
            public String getString(String key, @Nullable String defValue) {
                return null;
            }

            @Nullable
            @Override
            public Set<String> getStringSet(String key, @Nullable Set<String> defValues) {
                return null;
            }

            @Override
            public int getInt(String key, int defValue) {
                return 0;
            }

            @Override
            public long getLong(String key, long defValue) {
                return 0;
            }

            @Override
            public float getFloat(String key, float defValue) {
                return 0;
            }

            @Override
            public boolean getBoolean(String key, boolean defValue) {
                return false;
            }

            @Override
            public boolean contains(String key) {
                return false;
            }

            @Override
            public Editor edit() {
                return null;
            }

            @Override
            public void registerOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener listener) {

            }

            @Override
            public void unregisterOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener listener) {

            }
        };
    }

    //getBoolean first launch
    @Test
    public void get_boolean() {
        boolean isFirstBoolean = false;

        boolean result = sharedPreferences.getBoolean(IS_FIRST_LAUNCH, false);

        Assert.assertEquals(isFirstBoolean, result);
    }

    //getSharedPreferences
    @Test
    public void get_preferences(){
        boolean preference = false;

        boolean result = sharedPreferences.getBoolean(NAME_PREFS, true);

        Assert.assertEquals(preference, result);
    }



    @After
    public void clear() {
        sharedPreferences = null;
    }
}