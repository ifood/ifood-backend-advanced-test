package com.ifood.backend.advancedtest.service.spotify.interceptor;

import com.google.gson.Gson;
import feign.RequestTemplate;

public class RequestInterceptor implements feign.RequestInterceptor {
    private static final Gson GSON = new Gson();

    @Override
    public void apply(RequestTemplate template) {
        String request = "";

        if (template.body() != null) {
            request = GSON.toJson(new String(template.body()));
            request = request
                        .replace("\\n", "")
                        .replace("\\\"", "\"");
        }
    }
}
