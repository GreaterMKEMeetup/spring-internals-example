package com.mkejug.spring.appcontext;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppContextConsumerTest {

    @Autowired
    AppContextConsumer appContextConsumer;

    @Test
    public void addWithDelegate() {
        Assert.assertEquals(5, appContextConsumer.add(2, 3));
    }
}
