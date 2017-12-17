package com.ifood.backend.advancedtest.util;

import feign.RequestTemplate;
import feign.codec.EncodeException;
import feign.form.FormEncoder;

import java.lang.reflect.Type;

public class SpotifyFormEncoder extends FormEncoder {

    @Override
    public void encode(Object object, Type bodyType, RequestTemplate template) throws EncodeException {
        super.encode(object, bodyType, template);
    }
}
