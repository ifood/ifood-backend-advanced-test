package com.ifood.backend.advancedtest.service.spotify.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import feign.Response;
import feign.jackson.JacksonDecoder;

import java.io.IOException;
import java.lang.reflect.Type;

public class ResponseInterceptor extends JacksonDecoder {
    private static final Gson GSON = new Gson();

    public ResponseInterceptor() {
        super();
    }

    public ResponseInterceptor(ObjectMapper mapper) {
        super(mapper);
    }

    @Override
    public Object decode(Response response, Type type) throws IOException {
        Object decoded = super.decode(response, type);
        return decoded;
    }
}
