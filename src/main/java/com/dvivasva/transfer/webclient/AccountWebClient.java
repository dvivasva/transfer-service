package com.dvivasva.transfer.webclient;

import com.dvivasva.transfer.utils.UriAccess;
import com.dvivasva.transfer.utils.UriBase;
import com.dvivasva.transfer.webclient.dto.Account;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collections;

public class AccountWebClient {
    WebClient client = WebClient.builder()
            .baseUrl(UriBase.LOCALHOST_8889)
            .defaultCookie("cookieKey", "cookieValue")
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .defaultUriVariables(Collections.singletonMap("url", UriBase.LOCALHOST_8889))
            .build();

    public Mono<Account> details(String id) {
        return client.get()
                .uri( UriBase.LOCALHOST_8889 + UriAccess.ACCOUNT + id)
                .accept(MediaType.APPLICATION_NDJSON)
                .exchangeToMono(response -> {
                    if (response.statusCode().equals(HttpStatus.OK)) {
                        return response.bodyToMono(Account.class);
                    }
                    else {
                        // Turn to error
                        return response.createException().flatMap(Mono::error);
                    }
                });
    }

    public Mono<Account> update(String accountId,Mono<Account> account) {
        return client.put()
                .uri(UriBase.LOCALHOST_8889 + UriAccess.ACCOUNT + accountId)
                .body(account, Account.class)
                .retrieve()
                .bodyToMono(Account.class);
    }
}
