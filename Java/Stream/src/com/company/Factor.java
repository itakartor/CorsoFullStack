package com.company;

import java.util.function.Function;

public class Factor implements Function<Integer, Integer> {


    @Override
    public Integer apply(Integer integer) {
        int result = 1;
        for(int i=integer;i>0;i--)
            result = result*i;
        return result;
    }
}
