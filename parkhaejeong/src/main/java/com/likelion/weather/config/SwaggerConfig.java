package com.likelion.weather.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("실시간 날씨 정보 조회 서비스 API")
                        .description("OpenWeatherMap API를 연동한 위도/경도 기반 날씨 조회 서비스")
                        .version("v1.0.0"));
    }
}