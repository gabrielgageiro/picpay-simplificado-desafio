package com.example.picpaysimplificado.notification;

import com.example.picpaysimplificado.exception.NotificationException;
import com.example.picpaysimplificado.transaction.Transaction;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class NotificationConsumer {
    private RestClient restClient;

    public NotificationConsumer(RestClient.Builder builder) {
        this.restClient = builder
                .baseUrl("https://crudcrud.com/api/f97c96219f1f488b8eb3b79a53103b4c")
                .build();
    }

    @KafkaListener(topics = "transaction-notification", groupId = "picpay-simplificado-desafio")
    public void receiveNotification(Transaction transaction){
        ResponseEntity<Notification> response = restClient.get()
                .retrieve()
                .toEntity(Notification.class);

        if (response.getStatusCode().isError() || !response.getBody().message()) {
            throw new NotificationException("Transacao nao autorizada");
        }
    }
}
