package com.example.JavaDN;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * HttpRequest: Dumps to OutputStream
 */
public class HttpRequest {

    public static void dump(String url, FileOutputStream out) throws Exception {

        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);

        HttpResponse response = httpClient.execute(httpGet);
        HttpEntity httpEntity = response.getEntity();

        InputStream is = httpEntity.getContent();
        try {
            int counter;
            byte[] buffer = new byte[1024];
            while ((counter = is.read(buffer)) != -1) {
                out.write(buffer, 0, counter);
            }
        } finally {
            close(out);
            close(is);
        }
    }

    protected static void close(InputStream is) {
        if (is != null) {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    protected static void close(OutputStream out) {
        if (out != null) {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
