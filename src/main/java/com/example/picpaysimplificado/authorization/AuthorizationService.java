package com.example.picpaysimplificado.authorization;

import com.example.picpaysimplificado.exception.AnauthorizedTranscationException;
import com.example.picpaysimplificado.transaction.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class AuthorizationService {
    public static final Logger LOGGER = LoggerFactory.getLogger(AuthorizationService.class);
    private RestClient restClient;

    public AuthorizationService(RestClient.Builder builder) {
        this.restClient = builder
                .baseUrl("https://util.devi.tools/api/v2/authorize") //endereco disponibilizado no github do desafio
                .build();
    }

    public void authorize(Transaction transaction){
        LOGGER.info("authorizing a transaction: {}", transaction);
        ResponseEntity<Authorization> response = restClient.get()
                .retrieve()
                .onStatus(HttpStatusCode::isError, (req, res) -> {
                    throw new AnauthorizedTranscationException("Transacao nao autorizada");
                })
                .toEntity(Authorization.class);

        if (response.getStatusCode().isError() || !response.getBody().isAuthorized()) {
            throw new AnauthorizedTranscationException("Transacao nao autorizada");
        }
        LOGGER.info("authorized transaction: {}", transaction);
    }
}
