package com.likelion.weather.controller;

import com.likelion.weather.dto.WeatherResponseDto;
import com.likelion.weather.service.WeatherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Weather", description = "날씨 정보 조회 API")
@RestController
@RequestMapping("/api/weather")
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;

    @Operation(summary = "위도 및 경도 기반 실시간 날씨 조회", description = "위도(lat)와 경도(lon)를 입력받아 현재 날씨 정보를 반환합니다.")
    @GetMapping
    public ResponseEntity<WeatherResponseDto> getWeather(
            @Parameter(description = "위도 (예: 37.5665)", example = "37.5665") @RequestParam Double lat,
            @Parameter(description = "경도 (예: 126.9780)", example = "126.9780") @RequestParam Double lon) { // 스웨거 화면에 나오는 description 부분과 name 부분(순서대로)

        WeatherResponseDto responseDto = weatherService.getWeather(lat, lon);
        return ResponseEntity.ok(responseDto);
    }
}