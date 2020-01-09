package com.dastan.m5lesson11;

import android.content.Context;
import android.content.SharedPreferences;

import com.dastan.m5lesson11.data.PreferenceHelper;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Mock
    Context context;
    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        PreferenceHelper.init(context);
    }
    ///true
    @Test
    public void isFirsLaunch(){
        PreferenceHelper preferenceHelper = new PreferenceHelper();
        when(preferenceHelper.getIsFirstLaunch()).thenReturn(true);
        Assert.assertEquals(PreferenceHelper.getIsFirstLaunch(),true);
    }

}