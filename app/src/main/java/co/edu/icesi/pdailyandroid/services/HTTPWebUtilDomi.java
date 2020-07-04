package co.edu.icesi.pdailyandroid.services;


import android.util.Base64;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import co.edu.icesi.pdailyandroid.util.Constants;

public class HTTPWebUtilDomi {

    private OnResponseListener listener;
    private HashMap<String, String> headers;

    public HTTPWebUtilDomi() {
        headers = new HashMap<>();
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
            HttpURLConnection connection = (HttpURLConnection) page.openConnection();
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
            String response = new String(baos.toByteArray(), "UTF-8");
            if (listener != null) listener.onResponse(callbackID, response);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String syncGETrequest(String url) {
        try {
            URL page = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) page.openConnection();
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
            String response = new String(baos.toByteArray(), "UTF-8");
            return response;
        } catch (IOException ex) {
            ex.printStackTrace();
            return ex.getLocalizedMessage();
        }
    }


    public void POSTrequest(int callbackID, String url, String json) {
        try {
            URL page = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) page.openConnection();
            for (String key : headers.keySet()) {
                connection.setRequestProperty(key, headers.get(key));
            }
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoOutput(true);

            OutputStream os = connection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));

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
            String response = new String(baos.toByteArray(), "UTF-8");
            if (listener != null) listener.onResponse(callbackID, response);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String syncPOSTRequest(String url, String json) {
        try {
            URL page = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) page.openConnection();
            for (String key : headers.keySet()) {
                connection.setRequestProperty(key, headers.get(key));
            }
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoOutput(true);

            OutputStream os = connection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));

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
            String response = new String(baos.toByteArray(), "UTF-8");
            return response;
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }


    public void PUTrequest(int callbackID, String url, String json) {
        try {
            URL page = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) page.openConnection();
            for (String key : headers.keySet()) {
                connection.setRequestProperty(key, headers.get(key));
            }
            connection.setRequestMethod("PUT");
            connection.setDoOutput(true);

            OutputStream os = connection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));

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
            String response = new String(baos.toByteArray(), "UTF-8");
            if (listener != null) listener.onResponse(callbackID, response);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public String syncPUTRequest(String url, String json) {
        try {
            URL page = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) page.openConnection();
            for (String key : headers.keySet()) {
                connection.setRequestProperty(key, headers.get(key));
            }
            connection.setRequestMethod("PUT");
            connection.setDoOutput(true);

            OutputStream os = connection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));

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
            String response = new String(baos.toByteArray(), "UTF-8");
            return response;
        } catch (IOException ex) {
            return ex.getLocalizedMessage();
        }

    }


    public void DELETErequest(int callbackID, String url) {
        try {
            URL page = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) page.openConnection();
            for (String key : headers.keySet()) {
                connection.setRequestProperty(key, headers.get(key));
            }
            connection.setRequestMethod("DELETE");
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

            String response = new String(baos.toByteArray(), "UTF-8");
            if (listener != null) listener.onResponse(callbackID, response);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public String syncDELETErequest(String url) {
        try {
            URL page = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) page.openConnection();
            for (String key : headers.keySet()) {
                connection.setRequestProperty(key, headers.get(key));
            }
            connection.setRequestMethod("DELETE");
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
            String response = new String(baos.toByteArray(), "UTF-8");
            return response;
        } catch (IOException ex) {
            return ex.getLocalizedMessage();
        }

    }

    public void setBasicAuth(String user, String pass) {
        String auth = user+":"+pass;
        String base64 = android.util.Base64.encodeToString(auth.getBytes(), Base64.DEFAULT);
        headers.put("Authorization","Basic "+base64);
    }


    public interface OnResponseListener {
        void onResponse(int callbackID, String response);
    }
}