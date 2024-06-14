package org.example;

import com.google.cloud.functions.HttpFunction;
import com.google.cloud.functions.HttpRequest;
import com.google.cloud.functions.HttpResponse;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class CloudFunction implements HttpFunction {
    private static final Gson gson = new Gson();

    @Override
    public void service(HttpRequest httpRequest, HttpResponse httpResponse) throws Exception {
        JsonObject body = gson.fromJson(httpRequest.getReader(), JsonObject.class);
        String inputMessage = body.get("message").getAsString();
        httpResponse.getWriter().write("Jeeeeeeeej muxik a function! Input message: " + inputMessage);
    }
}
