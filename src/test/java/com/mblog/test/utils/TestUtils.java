package com.mblog.test.utils;

import org.springframework.http.MediaType;

import java.nio.charset.Charset;

/**
 * Created by root on 10/15/17.
 */
public class TestUtils {

    /**
     * Constant of utf8 json application
     */
    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8")
    );
}