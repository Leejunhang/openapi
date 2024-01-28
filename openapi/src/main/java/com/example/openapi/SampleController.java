package com.example.openapi;

import org.json.JSONObject;
import org.json.XML;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


@RestController
public class SampleController {

    @GetMapping("/openapimid")
    public String openapimid() {
        StringBuffer result = new StringBuffer();
        String jsonPrintString = null;
        try {
            String apiUrl = "http://apis.data.go.kr/1360000/MidFcstInfoService/getMidLandFcst?" +
                    "serviceKey=vSm%2FtSLYYvXIKQccztnH9Ngi4hS1plbcN83tjFcvTsHQiEB2ZGsREad%2BOkwlOOOpLQBB%2BycjRjb2GlCRq0h3qA%3D%3D" +
                    "&numOfRows=10" +
                    "&pageNo=1" +
                    "&regId=11B00000" +
                    "&tmFc=201310171800";
            
            URL url = new URL(apiUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(bufferedInputStream, "UTF-8"));
            String returnLine;
            while((returnLine = bufferedReader.readLine()) != null) {
                result.append(returnLine);
            }

            JSONObject jsonObject = XML.toJSONObject(result.toString());
            jsonPrintString = jsonObject.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonPrintString;
    }
}