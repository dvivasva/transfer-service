package com.dvivasva.transfer.utils;

import com.dvivasva.transfer.Model.Transfer;
import com.dvivasva.transfer.dto.TransferDto;
import org.springframework.beans.BeanUtils;

public class TransferUtil {

    public static TransferDto entityToDto(Transfer transfer){
        var transferDto=new TransferDto();
        BeanUtils.copyProperties(transfer,transferDto);
        return transferDto;
    }
    public static Transfer dtoToEntity(TransferDto transferDto){
        var entity=new Transfer();
        BeanUtils.copyProperties(transferDto,entity);
        return entity;
    }

}
