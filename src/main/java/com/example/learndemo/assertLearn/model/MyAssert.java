package com.example.learndemo.assertLearn.model;

import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

public class MyAssert extends Assert {

    public static void isNull(@Nullable Object object, String message) {
        if (object == null) {
            throw new MyException(message,500);
        }
    }

}
