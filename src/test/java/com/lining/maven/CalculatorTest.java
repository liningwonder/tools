package com.lining.maven;

import org.junit.*;

import static org.junit.Assert.assertEquals;

public class CalculatorTest {

    @BeforeClass
    public static void beforeClass() {
        System.out.println("use for init");
    }

    @Before
    public void before() {
        System.out.println("before");
    }

    @After
    public void after() {
        System.out.println("after");
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("use for destroy");
    }

    @Test
    public void testAddCm() {
        Calculator calculator = new Calculator();
        int sum = calculator.evaluate("1+2+3");
        assertEquals(6, sum);
    }

}
