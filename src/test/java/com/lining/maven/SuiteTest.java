package com.lining.maven;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({AddDemoTest.class, CalculatorTest.class, UseVarTest.class})
public class SuiteTest {
}
