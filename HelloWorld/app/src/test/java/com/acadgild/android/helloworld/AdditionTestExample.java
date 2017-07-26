package com.acadgild.android.helloworld;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by AdityaDua on 17/07/17.
 */
public class AdditionTestExample {
    @Test
    public void addition() throws Exception {
        int a,b;
        a=10;
        b=20;
        int c=a+b;
        int result=30;
        assertEquals("Failed ",result,c);
    }

}