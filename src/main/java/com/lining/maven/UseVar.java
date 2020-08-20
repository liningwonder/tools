package com.lining.maven;

import com.lining.maven.thread.AppThreadPoolExecutorFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

public class UseVar {

    private static final Logger LOG = LoggerFactory.getLogger(UseVar.class);

    private static final ThreadPoolExecutor R_POOL =
            AppThreadPoolExecutorFactory.createThreadPool(
                    UseVar.class.getSimpleName(),
                    1,
                    10,
                    new ArrayBlockingQueue<>(100));

    public void use(HttpServletRequest request) {
        //jdk 8中，引用方法的局部变量，默认该变量时final的
        //jdk 6中则必须显示声明为final
        R_POOL.execute(new Runnable() {
            @Override
            public void run() {
                LOG.warn(Thread.currentThread().getName() + " : " + request.getRemoteAddr());
                //System.out.println(Thread.currentThread().getName() + " : " + request.getRemoteAddr());
            }
        });

    }

    public String getRemoteAddr(HttpServletRequest request) {
        return request.getRemoteAddr();
    }


}
