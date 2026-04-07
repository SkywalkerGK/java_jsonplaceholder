package test_call_api;

import java.util.HashMap;
import java.util.Map;

public class TestAPI {

    public static void main(String[] args) {

        // ===== HEADER =====
//        Map<String, String> headers = new HashMap<>();
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");

        // ===== TEST GET =====
//        String getUrl = "https://jsonplaceholder.typicode.com/posts/1";
        String getUrl = "http://jsonplaceholder.typicode.com/posts/1";
        String getResponse = ApiClient.get(getUrl, headers);

        System.out.println("===== GET RESULT =====");
        System.out.println(getResponse);

        // ===== TEST POST =====
//        String postUrl = "https://jsonplaceholder.typicode.com/posts";
        String postUrl = "http://jsonplaceholder.typicode.com/posts";
        String jsonBody = "{\"title\":\"hello\",\"body\":\"test api\",\"userId\":1}";

        String postResponse = ApiClient.post(postUrl, jsonBody, headers);

        System.out.println("\n===== POST RESULT =====");
        System.out.println(postResponse);
    }
}