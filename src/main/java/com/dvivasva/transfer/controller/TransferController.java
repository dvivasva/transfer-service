package com.dvivasva.transfer.controller;

import com.dvivasva.transfer.Model.Transfer;
import com.dvivasva.transfer.dto.TransferDto;
import com.dvivasva.transfer.service.TransferService;
import com.dvivasva.transfer.utils.MongoLocalDateTime;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transfer")
public class TransferController {
    private final TransferService transferService;
    private final static Logger logger= LoggerFactory.getLogger(TransferService.class);

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<TransferDto> create(@RequestBody Mono<TransferDto> transferDtoMono) {
        return this.transferService.create(transferDtoMono);
    }
    @GetMapping
    public  String getDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        //Date actual
        Calendar calendar = Calendar.getInstance();


        //logger.info("Date init: "+ LocalDateTime.MIN);
        //logger.info("Date end: "+ LocalDateTime.MAX);

        /*calendar.set(Calendar.DAY_OF_MONTH,1);
        logger.info("Primer día del mes actual:" + sdf.format(calendar.getTime()));*/

        LocalDate todayDate = LocalDate.now();
        LocalDateTime localDateTime=LocalDateTime.now();

       // logger.info("Date actual: "+ LocalDateTime.now());
        return "range date:" + localDateTime.withDayOfMonth(1) + " between " + LocalDateTime.now();
    }

    @GetMapping("/list")
    public Flux<Transfer> findAllTransferBetweenDateByAccountId(){
        return transferService.findAllTransferBetweenDateByAccountId();
    }
}
