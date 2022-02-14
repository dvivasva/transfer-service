package com.dvivasva.transfer.dto;

import com.dvivasva.transfer.utils.MongoLocalDateTime;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
public class TransferDto {
    private String id;
    private double amount;
    private double commission;
    private String idAccountOrigin;
    private String idAccountDestination;

    private Date date;
    //private MongoLocalDateTime date;
}
