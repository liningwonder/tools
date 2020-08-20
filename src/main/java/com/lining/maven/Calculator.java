package com.lining.maven;

public class Calculator {

    public int evaluate(String expression) {
        int sum = 0;
        for (String summat: expression.split("\\+"))
            //会抛出运行时异常
            sum += Integer.parseInt(summat);
        return sum;
    }

}
