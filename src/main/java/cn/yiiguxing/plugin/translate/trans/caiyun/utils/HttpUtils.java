package com.mrx.translator.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * @author Mr.X
 * @since 2023-12-07 下午 9:27:10
 */
public class HttpUtils {

    private static final HttpClient client = HttpClient.newBuilder()
            .build();

    private static final Logger logger = LoggerFactory.getLogger(HttpUtils.class);

    public static String sendPost(String url, String payload, String... headers) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .POST(HttpRequest.BodyPublishers.ofString(payload))
                    .header("Content-Type", "application/json")
                    .headers(headers)
                    .build();
            logger.info("sending: {} -> {}", url, payload);
            return client.send(request, HttpResponse.BodyHandlers.ofString()).body();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
