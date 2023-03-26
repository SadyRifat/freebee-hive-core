package com.freebee.hive.util;

import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Map;

public class HttpClientUtil {

    public static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    public static <T> HttpRequest.BodyPublisher ofFormData(T obj) {
        if (obj instanceof Map) {
            return ofFormData((Map<? extends Object, ? extends Object>) obj);
        } else {
            return ofFormData(obj.getClass(), obj);
        }
    }

    private static HttpRequest.BodyPublisher ofFormData(Map<? extends Object, ? extends Object> data) {
        var builder = new StringBuilder();
        for (Map.Entry<? extends Object, ? extends Object> entry : data.entrySet()) {
            if (builder.length() > 0) {
                builder.append("&");
            }
            builder.append(URLEncoder.encode(entry.getKey().toString(), StandardCharsets.UTF_8));
            builder.append("=");
            builder.append(URLEncoder.encode(entry.getValue().toString(), StandardCharsets.UTF_8));
        }
        return HttpRequest.BodyPublishers.ofString(builder.toString());
    }

    private static HttpRequest.BodyPublisher ofFormData(Class clazz, Object o) {
        String queryString = "?";
        try {
            for (Field f : clazz.getDeclaredFields()) {
                f.setAccessible(true);
                queryString += queryString.concat(f.getName() + "=" + f.get(o) + "&");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return HttpRequest.BodyPublishers.ofString(queryString.substring(0, queryString.length() - 1));
    }
}
