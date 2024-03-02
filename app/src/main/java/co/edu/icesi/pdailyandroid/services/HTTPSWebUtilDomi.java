package co.edu.icesi.pdailyandroid.services;

import android.util.Base64;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.HashMap;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class HTTPSWebUtilDomi {

    private OnResponseListener listener;
    private final HashMap<String, String> headers;

    public HTTPSWebUtilDomi() {
        headers = new HashMap<>();
        try {
            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }

                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }
            };
            //Install the all-trusting trust manager
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

            // Install the all-trusting host verifier
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession sslSession) {
                    return true;
                    //Use la variable hostname para retornar true en caso de que
                    //concuerde con la página que usted está intentando consultar
                }
            });
            //HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            e.printStackTrace();
        }
    }

    public void setHeader(String clave, String valor) {
        headers.put(clave, valor);
    }

    public void clearHeaders() {
        headers.clear();
    }

    public void setListener(OnResponseListener listener) {
        this.listener = listener;
    }

    public void GETrequest(int callbackID, String url) {
        try {
            URL page = new URL(url);
            HttpsURLConnection connection = (HttpsURLConnection) page.openConnection();
            for (String key : headers.keySet()) {
                connection.setRequestProperty(key, headers.get(key));
            }
            InputStream is = connection.getInputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = is.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesRead);
            }
            is.close();
            baos.close();
            connection.disconnect();
            String response = baos.toString(StandardCharsets.UTF_8);
            if (listener != null) listener.onResponse(callbackID, response);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String syncGETrequest(String url) {
        try {
            URL page = new URL(url);
            HttpsURLConnection connection = (HttpsURLConnection) page.openConnection();
            for (String key : headers.keySet()) {
                connection.setRequestProperty(key, headers.get(key));
            }
            InputStream is = connection.getInputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = is.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesRead);
            }
            is.close();
            baos.close();
            connection.disconnect();
            String response = baos.toString(StandardCharsets.UTF_8);
            return response;
        } catch (IOException ex) {
            ex.printStackTrace();
            return ex.getLocalizedMessage();
        }
    }

    public String syncPOSTRequest(String url, String json) {
        try {
            URL page = new URL(url);
            HttpsURLConnection connection = (HttpsURLConnection) page.openConnection();
            for (String key : headers.keySet()) {
                connection.setRequestProperty(key, headers.get(key));
            }
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoOutput(true);

            OutputStream os = connection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, StandardCharsets.UTF_8));

            writer.write(json);
            writer.flush();

            InputStream is = connection.getInputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = is.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesRead);
            }
            is.close();
            baos.close();
            os.close();
            connection.disconnect();
            String response = baos.toString(StandardCharsets.UTF_8);
            return response;
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void POSTrequest(int callbackID, String url, String json) {
        try {
            URL page = new URL(url);
            HttpsURLConnection connection = (HttpsURLConnection) page.openConnection();
            connection.setRequestMethod("POST");
            for (String key : headers.keySet()) {
                connection.setRequestProperty(key, headers.get(key));
            }
            connection.setDoInput(true);
            connection.setDoOutput(true);

            OutputStream os = connection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, StandardCharsets.UTF_8));

            writer.write(json);
            writer.flush();

            InputStream is = connection.getInputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = is.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesRead);
            }
            is.close();
            baos.close();
            os.close();
            connection.disconnect();
            String response = baos.toString(StandardCharsets.UTF_8);
            if (listener != null) listener.onResponse(callbackID, response);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void PUTrequest(int callbackID, String url, String json) {
        try {
            URL page = new URL(url);
            HttpsURLConnection connection = (HttpsURLConnection) page.openConnection();
            connection.setRequestMethod("PUT");
            for (String key : headers.keySet()) {
                connection.setRequestProperty(key, headers.get(key));
            }
            connection.setDoOutput(true);

            OutputStream os = connection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, StandardCharsets.UTF_8));

            writer.write(json);
            writer.flush();

            InputStream is = connection.getInputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = is.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesRead);
            }
            is.close();
            baos.close();
            os.close();
            connection.disconnect();
            String response = baos.toString(StandardCharsets.UTF_8);
            if (listener != null) listener.onResponse(callbackID, response);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public String syncPUTRequest(String url, String json) {
        try {
            URL page = new URL(url);
            HttpsURLConnection connection = (HttpsURLConnection) page.openConnection();
            connection.setRequestMethod("PUT");
            for (String key : headers.keySet()) {
                connection.setRequestProperty(key, headers.get(key));
            }
            connection.setDoOutput(true);

            OutputStream os = connection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, StandardCharsets.UTF_8));

            writer.write(json);
            writer.flush();

            InputStream is = connection.getInputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = is.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesRead);
            }
            is.close();
            baos.close();
            os.close();
            connection.disconnect();
            String response = baos.toString(StandardCharsets.UTF_8);
            return response;
        } catch (IOException ex) {
            return ex.getLocalizedMessage();
        }

    }

    public void DELETErequest(int callbackID, String url) {
        try {
            URL page = new URL(url);
            HttpsURLConnection connection = (HttpsURLConnection) page.openConnection();
            connection.setRequestMethod("DELETE");
            for (String key : headers.keySet()) {
                connection.setRequestProperty(key, headers.get(key));
            }
            connection.setDoOutput(true);

            InputStream is = connection.getInputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[4096];
            int bytesRead;

            while ((bytesRead = is.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesRead);
            }
            is.close();
            baos.close();
            connection.disconnect();

            String response = baos.toString(StandardCharsets.UTF_8);
            if (listener != null) listener.onResponse(callbackID, response);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public String syncDELETErequest(String url) {
        try {
            URL page = new URL(url);
            HttpsURLConnection connection = (HttpsURLConnection) page.openConnection();
            connection.setRequestMethod("DELETE");
            for (String key : headers.keySet()) {
                connection.setRequestProperty(key, headers.get(key));
            }
            connection.setDoOutput(true);

            InputStream is = connection.getInputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[4096];
            int bytesRead;

            while ((bytesRead = is.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesRead);
            }
            is.close();
            baos.close();
            connection.disconnect();
            String response = baos.toString(StandardCharsets.UTF_8);
            return response;
        } catch (IOException ex) {
            return ex.getLocalizedMessage();
        }

    }

    public void POSTtoFCM(String API_KEY, String data) {
        try {
            URL page = new URL("https://fcm.googleapis.com/fcm/send");
            HttpsURLConnection connection = (HttpsURLConnection) page.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", "key=" + API_KEY);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            OutputStream os = connection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, StandardCharsets.UTF_8));

            writer.write(data);
            writer.flush();

            InputStream is = connection.getInputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = is.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesRead);
            }
            is.close();
            baos.close();
            os.close();
            connection.disconnect();
            String response = baos.toString(StandardCharsets.UTF_8);
            Log.e(">>>", response);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void saveURLImageOnFile(String url, File file) {
        try {
            URL page = new URL(url);
            HttpsURLConnection connection = (HttpsURLConnection) page.openConnection();
            InputStream is = connection.getInputStream();
            FileOutputStream fos = new FileOutputStream(file);
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = is.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
            is.close();
            fos.close();
            connection.disconnect();
            Log.e(">>>", "Foto descargada!");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void setBasicAuth(String user, String pass) {
        String auth = user + ":" + pass;
        String base64 = android.util.Base64.encodeToString(auth.getBytes(), Base64.DEFAULT);
        headers.put("Authorization", "Basic " + base64);
    }

    public interface OnResponseListener {
        void onResponse(int callbackID, String response);
    }
}