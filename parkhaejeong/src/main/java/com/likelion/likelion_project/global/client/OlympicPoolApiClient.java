package com.likelion.likelion_project.global.client;


import com.likelion.likelion_project.common.exception.BusinessException;
import com.likelion.likelion_project.common.response.code.ErrorCode;
import com.likelion.likelion_project.global.client.dto.OlympicPoolResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
public class OlympicPoolApiClient {

    private final RestClient restClient = RestClient.create(); // 기본 RestClient 생성

    @Value("${external-api.olympic.url}")
    private String olympicApiUrl;

    @Value("${external-api.olympic.api-key}") // 발급받은 공공데이터 서비스키
    private String serviceKey;

    public List<OlympicPoolResponseDto> getOlympicPoolInfo() {
        try {
            // 공공데이터 API는 보통 Query Parameter로 인자를 넘겨야 하므로 URI를 빌드합니다.
            String targetUri = UriComponentsBuilder.fromHttpUrl(olympicApiUrl)
                    .queryParam("serviceKey", serviceKey)
                    .queryParam("numOfRows", "10") // 예시: 10개씩 가져오기
                    .queryParam("pageNo", "1")
                    .build()
                    .toUriString();

            OlympicPoolResponseDto[] response = restClient.get()
                    .uri(targetUri)
                    .retrieve()
                    .onStatus(HttpStatusCode::isError, (request, res) -> {
                        log.error("올림픽수영장 API 오류: {}", res.getStatusCode());
                        throw new BusinessException(ErrorCode.BAD_GATEWAY);
                    })
                    .body(OlympicPoolResponseDto[].class);

            if (response == null || response.length == 0) {
                log.warn("올림픽수영장 API 응답이 비어있습니다.");
                throw new BusinessException(ErrorCode.BAD_GATEWAY);
            }

            return Arrays.asList(response);

        } catch (BusinessException e) {
            log.error("올림픽수영장 서버 연결 실패: {}", e.getMessage());
            throw new BusinessException(ErrorCode.SERVICE_UNAVAILABLE); // 필요시 올림픽 전용 에러코드로 변경
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("올림픽수영장 API 예기치 않은 오류: {}", e.getMessage());
            throw new BusinessException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }
}