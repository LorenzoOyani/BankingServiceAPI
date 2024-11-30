package org.example.bankingportal.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.bankingportal.entities.Messages;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageClientService { // rest template to get resources from the endpoints using the resource server to grant authorities!

    private final String STRING_URL_TEMPLATE = "http://localhost:8089";

    private final WebClient webClient;

    private final List<String> ACCESS_TOKEN = List.of("WU34R9F44B295H");


    public List<Messages> getMessages() {
        String url = STRING_URL_TEMPLATE + "/messages";
        try {
//            ResponseEntity<List<Messages>> response = restTemplate.exchange(
//                    url, HttpMethod.GET, null,
//                    new ParameterizedTypeReference<>() {
//                    });
//
//            return response.getBody();
            return webClient.get()
                    .uri(url)
                    .retrieve()
                    .bodyToFlux(Messages.class)
                    .collectList()
                    .block();
        } catch (Exception e) {
            log.error("error fetching messages", e);
            return List.of();

        }
    }

    public void createMessage(Messages message) {
        String url = STRING_URL_TEMPLATE + "/messages";
        try {
//            HttpHeaders headers = new HttpHeaders();
//            headers.put("Authorization", Collections.singletonList("Bearer " + ACCESS_TOKEN));
//            HttpEntity<?> httpEntity = new HttpEntity<>(message, headers);
//            ResponseEntity<Messages> response = restTemplate.exchange(
//                    url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<>() {
//                    }
//            );
//            log.info("a message was created with response code, {} ", response.getStatusCode());

            webClient.post()
                    .uri(url)
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + ACCESS_TOKEN.getFirst())
                    .body(Mono.just(message), Messages.class)
                    .retrieve()
                    .onStatus(
                            HttpStatusCode::isError, response -> {
                                log.error(
                                        "failed to create message. status code: {}", response.statusCode()
                                );
                                return Mono.error(new RuntimeException("failed to create message"));
                            }

                    )
                    .bodyToMono(Messages.class)
                    .block();
        } catch (Exception ignored) {
            log.info("error logging message: {}", message);
        }
    }
}
