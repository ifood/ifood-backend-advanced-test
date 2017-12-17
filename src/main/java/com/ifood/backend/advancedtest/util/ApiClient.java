package com.ifood.backend.advancedtest.util;

import feign.Feign;

public class ApiClient {

    Feign.Builder feignBuilder;
    private String basePath;

    public Feign.Builder getFeignBuilder(){
        if (feignBuilder == null) {
            feignBuilder = Feign.builder();
        }
        return feignBuilder;
    }

    public ApiClient setBasePath(String basePath) {
        this.basePath = basePath;
        return this;
    }

    public String getBasePath() {
        return basePath;
    }

    public <T extends ApiClient.Api> T buildClient(Class<T> clientClass) {
        return (T) this.feignBuilder.target(clientClass, this.basePath);
    }

    public interface Api {
    }
}
