package com.dvivasva.transfer.service;

import com.dvivasva.transfer.Model.Transfer;
import com.dvivasva.transfer.dto.TransferDto;
import com.dvivasva.transfer.repository.ITransferRepository;
import com.dvivasva.transfer.utils.TransferUtil;
import com.dvivasva.transfer.webclient.AccountWebClient;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Date;

@RequiredArgsConstructor
@Service
public class TransferService {
    private final ITransferRepository iTransferRepository;
    private final ReactiveMongoTemplate reactiveMongoTemplate;
    private final static Logger logger= LoggerFactory.getLogger(TransferService.class);

    public Mono<Void> operation(String idAccountOrigin, String idAccountDestination,double amount){

        double commission=0d;
        Mono<Long> numberTransfer=findAllTransferBetweenDateByAccountId(idAccountOrigin).count();

        AccountWebClient accountWebClient= new AccountWebClient();

        var retirement= accountWebClient.details(idAccountOrigin)
                .switchIfEmpty(Mono.error(new ClassNotFoundException("..")))
                .map(document->{
                    document.setAvailableBalance(document.getAvailableBalance()-(amount+commission));
                    return document;
                });

        var deposit= accountWebClient.details(idAccountDestination)
                .switchIfEmpty(Mono.error(new ClassNotFoundException("no exist account")))
                .map(document->{
                    document.setAvailableBalance(document.getAvailableBalance()+amount);
                    return document;
                });

        return  Mono.when(accountWebClient.update(idAccountOrigin,retirement),
                    accountWebClient.update(idAccountDestination,deposit));

    }

    public Mono<TransferDto> create(Mono<TransferDto> entityToDto){
                logger.info("inside methode create ");
                Mono<TransferDto> result=entityToDto.map(
                       p -> {
                           Mono<Void> v=operation(p.getIdAccountOrigin(),p.getIdAccountDestination(),p.getAmount());
                           v.doOnSuccess(x->logger.info("update available balance account")).subscribe();
                    p.setDate(new Date());
                    return p;
                });
                return result.map(TransferUtil::dtoToEntity)
                .flatMap(iTransferRepository::save)
                .map(TransferUtil::entityToDto);
    }

   public Flux<Transfer> findAllTransferBetweenDateByAccountId(String idAccountOrigin){
       logger.info("inside methode find ");
       LocalDateTime localDateTime=LocalDateTime.now();
       Query query = new Query();
       query.addCriteria(Criteria.where("date").gte(localDateTime.withDayOfMonth(1)).lte(LocalDateTime.now()).and("idAccountOrigin").is(idAccountOrigin));
       return reactiveMongoTemplate.find(query,Transfer.class);
    }





}
