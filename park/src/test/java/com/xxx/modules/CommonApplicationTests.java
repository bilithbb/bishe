package com.xxx.modules;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CommonApplicationTests {

    @Test
    void contextLoads() {

        Double price = 112.0;

        double v = price / 6;
        System.out.println(v);
    }

}
