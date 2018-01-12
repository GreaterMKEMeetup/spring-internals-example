package com.mkejug.spring.appcontext;

import org.springframework.stereotype.Component;

@Component
public class Adder {

    public int add (int x, int y) {
        return x + y;
    }
}
