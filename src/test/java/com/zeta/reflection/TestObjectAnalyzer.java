package com.zeta.reflection;

import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by Zeta on 2017/4/17.
 */
public class TestObjectAnalyzer {
    @Test
    public void testObjectAnalyzer(){
        ArrayList<Integer> squares = new ArrayList<>();
        for (int i = 1; i <= 5; i++)
            squares.add(i * i);
        System.out.println(new ObjectAnalyzer().toString(squares));
    }
}
