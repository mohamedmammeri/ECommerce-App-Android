package com.corposense.ubs;

import com.corposense.ubs.util.Utils;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class UtilsUnitTest {

    @Test
    public void durationConvertFormat() {
        String formattedDuration = Utils.getDuration(100000);
        assertEquals(formattedDuration, "01:40");
    }

}