package com.lining.maven;

import org.junit.*;

import static org.junit.Assert.assertEquals;

public class AddDemoTest {

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
    public void testAdd() {
        assertEquals(6, new AddDemo().add(2,4));
    }

    @Test
    public void testSubtract() {
        assertEquals(20, new AddDemo().subtract(30, 10));
    }

}
