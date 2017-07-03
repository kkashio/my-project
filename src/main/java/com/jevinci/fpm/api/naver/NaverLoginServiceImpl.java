package com.jevinci.fpm.api.naver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jevinci.fpm.domain.User;
import com.jevinci.fpm.security.auth.rest.LoginResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by seongmin Park on 2017. 6. 27..
 */

@Slf4j
@Service
public class NaverLoginServiceImpl implements NaverLoginService{

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public LoginResponse getProfile(String accessToken){
        LoginResponse loginResponse = null;
        User user = new User();
        String header = "Bearer " + accessToken; // Bearer 다음에 공백 추가
        try {
            String apiURL = "https://openapi.naver.com/v1/nid/me";
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Authorization", header);
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                return null;
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();

            LinkedHashMap<String, Object> result = objectMapper.readValue(response.toString(), LinkedHashMap.class);
            HashMap data = (HashMap) result.get("response");

            user.setEmail(data.get("email").toString());
            user.setSocialId(data.get("id").toString());

            log.info("Request NAVER user profile : " + result);
            return new LoginResponse(result.get("resultcode").toString(), "NAVER API "+result.get("message").toString(), user);

        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }
}
