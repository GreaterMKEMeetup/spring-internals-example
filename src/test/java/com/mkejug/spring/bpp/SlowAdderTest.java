package com.mkejug.spring.bpp;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SlowAdderTest {

    @Autowired
    SlowAdder slowAdder;

    @Test
    public void testSlowAdder() {
        int result = slowAdder.add(2, 3);
        Assert.assertEquals(5, result);
    }
}
