package com.lining.maven;

import com.lining.maven.constant.Constants;
import org.junit.Test;
import org.slf4j.MDC;

import javax.servlet.http.HttpServletRequest;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UseVarTest {

    @Test
    public void test() {
        UseVar useVar = new UseVar();
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getRemoteAddr()).thenReturn("192.168.67.1");
        assertEquals(useVar.getRemoteAddr(request), "192.168.67.1");
    }

    @Test
    public void use() {
        for (int i = 0; i < 100; i++) {
            UseVar useVar = new UseVar();
            HttpServletRequest request = mock(HttpServletRequest.class);
            when(request.getRemoteAddr()).thenReturn("192.168.67.1");
            useVar.use(request);
        }
    }

}
