package com.example.zuulproxy;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class GatewayClientResponse implements ClientHttpResponse {

    private String responseBody;
    private HttpHeaders headers;
    private String route;
    private int rawStatusCode = 503;
    private HttpStatus statusCode;
    private String statusText;

    public GatewayClientResponse(HttpStatus statusCode, String statusText) {
        this.statusCode = statusCode;
        this.statusText = statusText;

    }

    @Override
    public HttpStatus getStatusCode() throws IOException {
        if (statusCode == null) {
            statusCode = HttpStatus.SERVICE_UNAVAILABLE;
        }
        return statusCode;
    }

    @Override
    public int getRawStatusCode() throws IOException {
        return rawStatusCode;
    }

    @Override
    public String getStatusText() throws IOException {
        if (statusText == null) {
            statusText = "Service unavailable";
        }
        return statusText;
    }

    @Override
    public void close() {

    }

    @Override
    public InputStream getBody() throws IOException {
        if (responseBody == null) {
            responseBody = String.format("{\"message\": \"%s\"}", statusText);
        }
        return new ByteArrayInputStream(responseBody.getBytes());
    }

    @Override
    public HttpHeaders getHeaders() {
        if (headers == null) {
            headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
        }
        return headers;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }

    public void setHeaders(HttpHeaders headers) {
        this.headers = headers;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public void setRawStatusCode(int rawStatusCode) {
        this.rawStatusCode = rawStatusCode;
    }

    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }
}
