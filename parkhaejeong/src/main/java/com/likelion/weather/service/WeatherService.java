package com.likelion.weather.service;

import com.likelion.weather.dto.WeatherResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;

@Service
public class WeatherService {

    @Value("${external-api.open-weather.api-key}")
    private String apiKey;

    @Value("${external-api.open-weather.url}")
    private String apiUrl;

    private final RestTemplate restTemplate = new RestTemplate();
    // 우리 서버가 외부 서버에 HTTP 요청을 보내고, 그 응답을 받아오는 HTTP 통신 클라이언트 역할

    public WeatherResponseDto getWeather(Double lat, Double lon) {
        // yml에 정의된 {lat}, {lon}, {apiKey} 플레이스홀더를 실제 값으로 치환하여 URL 생성
        String finalUrl = UriComponentsBuilder.fromUriString(apiUrl)
                .buildAndExpand(Map.of(
                        "lat", lat,
                        "lon", lon,
                        "apiKey", apiKey
                ))
                .toUriString();

        try {
            // OpenWeatherMap API 호출 및 Map 구조로 응답 받기
            Map<String, Object> response = restTemplate.getForObject(finalUrl, Map.class); // 지정한 URL(finalUrl)로 HTTP GET 요청 보냄.
            // JSON 형태로 받아온 데이터를 자바의 Map 객체로 변환. Map.class(Map<String, Object> 형식으로)

            if (response == null) {
                throw new RuntimeException("날씨 API 응답이 비어있습니다.");
            }

            // JSON 파싱 (OpenWeatherMap 응답 구조에 맞춤) 'name'이라는 데이터를 cityName이라는 임시 변수에 넣음
            String cityName = (String) response.get("name"); // 이 안에 들어가는 이름은 실제 API에서 제공하는 구조에서 쓰이는 이름

            Map<String, Object> main = (Map<String, Object>) response.get("main");
            Double temp = Double.valueOf(main.get("temp").toString()); // main(Map 객체)에서 temp 값을 뽑는다. = 온도
            Integer humidity = Integer.valueOf(main.get("humidity").toString()); //똑같이 humidity 꺼냄. 습도

            List<Map<String, Object>> weatherList = (List<Map<String, Object>>) response.get("weather");
            String description = "";
            String icon = "";
            if (weatherList != null && !weatherList.isEmpty()) {
                // 0번째 배열 요소를 꺼내와서 데이터 추출.
                description = (String) weatherList.get(0).get("description");
                icon = (String) weatherList.get(0).get("icon");
            }

            // DTO로 가공하여 반환
            return WeatherResponseDto.builder() // api에서 받은 값과 dto 변수 연결
                    .cityName(cityName)
                    .temperature(temp)
                    .description(description)
                    .humidity(humidity)
                    .icon(icon)
                    .build(); // 조립 끝났다!

        } catch (Exception e) {
            throw new RuntimeException("OpenWeatherMap API 호출 중 오류 발생: " + e.getMessage());
        }
    }
}