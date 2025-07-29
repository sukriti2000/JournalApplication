package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.apiResponse.WeatherResponse;
import net.engineeringdigest.journalApp.cache.AppCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    @Value("${weather.api.key}")
    private String apiKey;

    public static final String api = "https://api.weatherstack.com/current?access_key=API_KEY&query=CITY";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AppCache appCache;

    @Autowired
    private RedisService redisService;
    public WeatherResponse getWeather(String city){
        WeatherResponse weatherResponse= redisService.get("weather_of_"+city,WeatherResponse.class);
        //  get mein hum pehele argument mein key denge h aur uska jo response ayega redis se
        // vo reponse kis class POJO m convert krna h vo second argument hoga
        // jaise reponse kisi bhi form aur template m h aur hume bss weather k object ki
        // form m chahiye to Weather response kl agar User k object ki form m chahiye to User.class use hoga

        if(weatherResponse!=null){
            System.out.println("yooooo weather bro"+weatherResponse);
            return weatherResponse;
        }
        else{
            System.out.println("yooooo else vala weather bro"+weatherResponse);
            String finalAPI = appCache.APP_CACHE.get("weather_api").replace("<city>",city).replace("<apiKey>",apiKey);
            ResponseEntity<WeatherResponse> response =  restTemplate.exchange(finalAPI, HttpMethod.GET,null, WeatherResponse.class);
            System.out.println("weather is"+response.getBody().getCurrent().getFeelslike());
            WeatherResponse body = response.getBody();
            if(body!=null){
                redisService.set("weather_of_"+city,body,300l);
            }
            return  body;
        }

    }
}
