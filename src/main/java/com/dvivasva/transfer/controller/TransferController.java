package com.dvivasva.transfer.controller;

import com.dvivasva.transfer.dto.TransferDto;
import com.dvivasva.transfer.service.TransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transfer")
public class TransferController {
    private final TransferService transferService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<TransferDto> create(@RequestBody Mono<TransferDto> transferDtoMono) {
        return transferService.create(transferDtoMono);
    }
    @GetMapping
    public  String getDate(){
        LocalDate todayDate = LocalDate.now();
        LocalDateTime localDateTime=LocalDateTime.now();
        return "range date:" + localDateTime.withDayOfMonth(1) + " between " + LocalDateTime.now();
    }

}
