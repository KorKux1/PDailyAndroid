package co.edu.icesi.pdailyandroid.services;

import android.annotation.SuppressLint;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * Created by Juliana on 20/08/2015.
 */
public class HTTPSWebUtilDomi {

    private static final String PDAILY_PASSWORD = "F8523E75-9070-4A60-991A-BF22A46F0866";

    HTTPSWebUtilDomi() {
        try {
            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                @SuppressLint("TrustAllX509TrustManager")
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }

                @SuppressLint("TrustAllX509TrustManager")
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }
            };
            //Install the all-trusting trust manager
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

            // Install the all-trusting host verifier
            HttpsURLConnection.setDefaultHostnameVerifier((hostname, arg1) -> !hostname.equalsIgnoreCase("www.icesi.edu.co"));
            //HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static String HTTPPOSTrequest(String url, String json) throws IOException {
        URL page = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) page.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type","application/json");
        connection.setRequestProperty("pdaily-tenant",PDAILY_PASSWORD);
        connection.setDoInput(true);
        connection.setDoOutput(true);

        OutputStream os = connection.getOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, StandardCharsets.UTF_8));

        writer.write(json);
        writer.flush();

        InputStream is = connection.getInputStream();
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int bytesRead;

        while ((bytesRead = is.read(buffer)) != -1) {
            bytes.write(buffer, 0, bytesRead);
        }
        is.close();
        writer.close();
        os.close();
        connection.disconnect();

        return new String(bytes.toByteArray(), StandardCharsets.UTF_8);
    }

    public String PUTrequest(String url, String json) throws Exception {
        URL page = new URL(url);
        HttpsURLConnection connection = (HttpsURLConnection) page.openConnection();
        connection.setRequestMethod("PUT");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("pdaily-tenant",PDAILY_PASSWORD);
        connection.setDoOutput(true);

        OutputStream os = connection.getOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, StandardCharsets.UTF_8));

        writer.write(json);
        writer.flush();

        BufferedInputStream is = new BufferedInputStream(connection.getInputStream());
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int bytesRead;

        while ((bytesRead = is.read(buffer)) != -1) {
            bytes.write(buffer, 0, bytesRead);
        }
        is.close();
        writer.close();
        os.close();
        connection.disconnect();

        return new String(bytes.toByteArray(), StandardCharsets.UTF_8);
    }

    public String GETrequest(String url) throws IOException {
        URL page = new URL(url);
        HttpsURLConnection connection = (HttpsURLConnection) page.openConnection();
        connection.setRequestProperty("pdaily-tenant",PDAILY_PASSWORD);
        BufferedInputStream is = new BufferedInputStream(connection.getInputStream());
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int bytesRead;
        while ((bytesRead = is.read(buffer)) != -1) {
            bytes.write(buffer, 0, bytesRead);
        }
        is.close();
        connection.disconnect();
        return new String(bytes.toByteArray(), StandardCharsets.UTF_8);

    }


    public String POSTrequest(String url, String json) throws IOException {

        URL page = new URL(url);
        HttpsURLConnection connection = (HttpsURLConnection) page.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json-patch+json");
        connection.setRequestProperty("Accept", "application/json");
        connection.setRequestProperty("pdaily-tenant",PDAILY_PASSWORD);
        connection.setDoInput(true);
        connection.setDoOutput(true);
        //connection.connect();

        String query = json;

        OutputStream os = connection.getOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, StandardCharsets.UTF_8));

        writer.write(query);
        writer.flush();

        if (("" + connection.getResponseCode()).startsWith("2")) {
            return connection.getResponseMessage();
        } else {
            byte[] buffer = new byte[4096];
            if (connection.getErrorStream() != null) {
                connection.getErrorStream().read(buffer);
                throw new IOException(new String(buffer).trim());
            } else throw new IOException("ERROR");
        }

    }
}