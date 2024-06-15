package org.example;

import com.google.cloud.functions.HttpFunction;
import com.google.cloud.functions.HttpRequest;
import com.google.cloud.functions.HttpResponse;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.net.HttpURLConnection;
import java.util.List;

public class AnagramsFunction2 implements HttpFunction {
    private static final Gson gson = new Gson();

    @Override
    public void service(HttpRequest httpRequest, HttpResponse httpResponse) throws Exception {
        httpResponse.appendHeader("Access-Control-Allow-Origin", "*");

        if ("OPTIONS".equals(httpRequest.getMethod())) {
            httpResponse.appendHeader("Access-Control-Allow-Methods", "GET");
            httpResponse.appendHeader("Access-Control-Allow-Headers", "Content-Type");
            httpResponse.appendHeader("Access-Control-Max-Age", "3600");
            httpResponse.setStatusCode(HttpURLConnection.HTTP_NO_CONTENT);
            return;
        }

        /*String body = httpRequest.getReader().lines().collect(Collectors.joining());*/
        JsonObject body = gson.fromJson(httpRequest.getReader(), JsonObject.class);
        System.out.println(" The data: " + body);
        String text = body.get("text").getAsString();
        JsonArray textsArray = body.get("texts").getAsJsonArray();

        List<String> texts = gson.fromJson(textsArray, new TypeToken<List<String>>() {}.getType());

        List<String> allAnagrams = Service.getAllAnagrams(text, texts);
        httpResponse.getWriter().write(allAnagrams.toString());
    }
}
