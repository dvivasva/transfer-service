package com.dvivasva.transfer.repository;

import com.dvivasva.transfer.Model.Transfer;
import com.dvivasva.transfer.dto.TransferDto;
import com.dvivasva.transfer.utils.MongoLocalDateTime;
import org.springframework.data.domain.Range;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.Date;

@Repository
public interface ITransferRepository extends ReactiveMongoRepository<Transfer, String> {


     // @Query("{'date': {$gte: ?0, $lte:?1 }}")
      //@Query("{'date' : { $gte: ?0, $lte: ?1 } }")
      //Flux<TransferDto> finAllTransferBetween( Date startDate, Date endDate);

     //@Query("{'date' : { $gte: ?0, $lte: ?1 } }")

    //Flux<Transfer> finAllTransferBetween(Date startTime, Date end);

}
