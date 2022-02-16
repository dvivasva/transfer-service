package com.dvivasva.transfer.webclient.dto;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;


@Getter
@Setter
public class Account {

	private String id;
	private String type;
	private String number;
	private double availableBalance;
	private Date dateCreation;
	private String status;
	private String customerId;

}
