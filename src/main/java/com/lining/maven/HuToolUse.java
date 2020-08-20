package com.lining.maven;

import cn.hutool.core.lang.Snowflake;

public class HuToolUse {

    public void use() {
        Snowflake snowflake = new Snowflake(1, 1);
        System.out.println(snowflake.nextId());
    }

}
