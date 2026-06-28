package com.likelion.weather.service;

import com.likelion.weather.dto.WeatherResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@Service
public class WeatherService {

    @Value("${external-api.open-weather.api-key}")
    private String apiKey;

    @Value("${external-api.open-weather.url}")
    private String apiUrl;

    private final RestTemplate restTemplate = new RestTemplate();

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
            Map<String, Object> response = restTemplate.getForObject(finalUrl, Map.class);

            if (response == null) {
                throw new RuntimeException("날씨 API 응답이 비어있습니다.");
            }

            // JSON 파싱 (OpenWeatherMap 응답 구조에 맞춤)
            String cityName = (String) response.get("name");

            Map<String, Object> main = (Map<String, Object>) response.get("main");
            Double temp = Double.valueOf(main.get("temp").toString());
            Integer humidity = Integer.valueOf(main.get("humidity").toString());

            java.util.List<Map<String, Object>> weatherList = (java.util.List<Map<String, Object>>) response.get("weather");
            String description = "";
            String icon = "";
            if (weatherList != null && !weatherList.isEmpty()) {
                description = (String) weatherList.get(0).get("description");
                icon = (String) weatherList.get(0).get("icon");
            }

            // DTO로 가공하여 반환
            return WeatherResponseDto.builder()
                    .cityName(cityName)
                    .temperature(temp)
                    .description(description)
                    .humidity(humidity)
                    .icon(icon)
                    .build();

        } catch (Exception e) {
            throw new RuntimeException("OpenWeatherMap API 호출 중 오류 발생: " + e.getMessage());
        }
    }
}