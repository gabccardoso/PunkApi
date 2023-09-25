package com.aquiDev.punk.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
@RequestMapping(value = "/punk")
public class PunkApiController {
    private final static String URL_BASE = "https://api.punkapi.com/v2/";

    @PreAuthorize("hasRole('ROLE_OPERATOR')")
    @GetMapping(value = "allBeers")
    public ResponseEntity<String> findAll(){
        String response = requestPunkAPI(URL_BASE+"beers");
        if(response != null){
            return ResponseEntity.ok(response.toString());
        }
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ROLE_OPERATOR')")
    @GetMapping(value = "beerPerPage")
    public ResponseEntity<String> findBeersPerPage(@RequestParam int page){
        String response = requestPunkAPI(URL_BASE+"beers?page="+page+"&per_page=25");
        if(response != null){
            return ResponseEntity.ok(response.toString());
        }
        return ResponseEntity.noContent().build();
    }

    private String requestPunkAPI(String url){
        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                return response.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
