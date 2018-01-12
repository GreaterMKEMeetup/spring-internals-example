package com.mkejug.spring.bpp;

import org.springframework.stereotype.Component;

@Component
public class SlowAdderImpl implements SlowAdder {

    @Override
    @PerfMonitor
    public int add(int x, int y) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return x + y;
    }
}
