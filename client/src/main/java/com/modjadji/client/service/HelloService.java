package com.modjadji.client.service;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import  org.apache.http.impl.client.HttpClientBuilder;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

@Service
public class HelloService {

    @Value("${service.hello.url}")
    private String helloServiceUrl;

    public String getHelloWithSSL() throws NoSuchAlgorithmException, KeyStoreException, CertificateException, KeyManagementException, IOException{
        URL url = new URL(helloServiceUrl);
        SSLSocketFactory sslSocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
        HttpsURLConnection connection = (HttpsURLConnection)url.openConnection();
        connection.setSSLSocketFactory(sslSocketFactory);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuffer stringBuffer = new StringBuffer();
        String line = "";
        while((line = bufferedReader.readLine()) != null){
            stringBuffer.append(line);
        }
        return bufferedReader.toString();
    }
}
