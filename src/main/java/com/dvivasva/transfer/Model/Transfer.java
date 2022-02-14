package com.dvivasva.transfer.Model;

import com.dvivasva.transfer.utils.MongoLocalDateTime;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Document
public class Transfer {
    @Id
    private String id;
    private double amount;
    private double commission;
    private String idAccountOrigin;
    private String idAccountDestination;

    //@Temporal(TemporalType.TIMESTAMP)
   /* @DateTimeFormat(style = "M-")
    @CreatedDate*/
    private Date date;
    //private MongoLocalDateTime date;
}
