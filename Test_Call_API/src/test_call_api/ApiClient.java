package test_call_api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class ApiClient {

    // ===== GET =====
    public static String get(String apiUrl, Map<String, String> headers) {
        return callAPI("GET", apiUrl, null, headers);
    }

    // ===== POST =====
    public static String post(String apiUrl, String body, Map<String, String> headers) {
        return callAPI("POST", apiUrl, body, headers);
    }

    // ===== CORE METHOD =====
    private static String callAPI(String method, String apiUrl, String body, Map<String, String> headers) {
        StringBuilder response = new StringBuilder();

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod(method);
            conn.setConnectTimeout(10000);
            conn.setReadTimeout(10000);

            // set headers
            if (headers != null) {
                for (Map.Entry<String, String> h : headers.entrySet()) {
                    conn.setRequestProperty(h.getKey(), h.getValue());
                }
            }

            // send body (POST)
            if (body != null && !body.isEmpty()) {
                conn.setDoOutput(true);
                OutputStream os = conn.getOutputStream();
                os.write(body.getBytes("UTF-8"));
                os.close();
            }

            int code = conn.getResponseCode();

            BufferedReader br;
            if (code >= 200 && code < 300) {
                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }

            String line;
            while ((line = br.readLine()) != null) {
                response.append(line);
            }
            br.close();

            System.out.println("HTTP Code: " + code);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return response.toString();
    }
}