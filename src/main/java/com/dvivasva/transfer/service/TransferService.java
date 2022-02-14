package com.dvivasva.transfer.service;

import com.dvivasva.transfer.Model.Transfer;
import com.dvivasva.transfer.dto.TransferDto;
import com.dvivasva.transfer.repository.ITransferRepository;
import com.dvivasva.transfer.utils.MongoLocalDateTime;
import com.dvivasva.transfer.utils.TransferUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Range;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;

@RequiredArgsConstructor
@Service
public class TransferService {
    private final ITransferRepository iTransferRepository;
    private final ReactiveMongoTemplate reactiveMongoTemplate;
    private final static Logger logger= LoggerFactory.getLogger(TransferService.class);

    public Mono<TransferDto> create(Mono<TransferDto> entityToDto){
                logger.info("inside methode create ");
                double commission=0d;
                var number=0;

                 Mono<Long> longMono=findAllTransferBetweenDateByAccountId().count();
                 longMono.subscribe(System.out::println);
               // logger.info("inside methode create ");

                Mono<TransferDto> result=entityToDto.map(
                p -> {


                    p.setDate(new Date());
                    return p;
                });

                return result.map(TransferUtil::dtoToEntity)
                .flatMap(iTransferRepository::save)
                .map(TransferUtil::entityToDto);


        /*return entityToDto.map(TransferUtil::dtoToEntity)
                .flatMap(iTransferRepository::save)
                .map(TransferUtil::entityToDto);*/

    }


   public Flux<Transfer> findAllTransferBetweenDateByAccountId(){
       logger.info("inside methode find ");
       LocalDateTime localDateTime=LocalDateTime.now();
       String idAccountOrigin="ACC-001";
       Query query = new Query();
       query.addCriteria(Criteria.where("date").gte(localDateTime.withDayOfMonth(1)).lte(LocalDateTime.now()).and("idAccountOrigin").is(idAccountOrigin));
       return reactiveMongoTemplate.find(query,Transfer.class);

    }

}
