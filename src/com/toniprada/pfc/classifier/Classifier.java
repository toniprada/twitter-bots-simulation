package com.toniprada.pfc.classifier;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.toniprada.pfc.twitter.Account;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by toni on 19/05/14.
 */
public class Classifier {

    private static final String CLASSIFIER_SERVER_URL = "http://localhost:5000/predict-is-human";

    private Gson gson;

    public Classifier() {
        gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    }

    public boolean classify(Account account) {
          try {
          java.net.URL obj = new URL(CLASSIFIER_SERVER_URL);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);

            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            String data = gson.toJson(account);


            String params = "account=" + URLEncoder.encode(data, "UTF-8");
            wr.writeBytes(params);
            wr.flush();
            wr.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            Result result = gson.fromJson(response.toString(), Result.class);
            boolean isHuman = result.isResult();
            if (isHuman) {
                System.out.println(data);
            }
            return isHuman;
       } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        }
        return false;
    }
}
