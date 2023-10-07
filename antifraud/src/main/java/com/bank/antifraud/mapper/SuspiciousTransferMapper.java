package com.bank.antifraud.mapper;


import com.bank.antifraud.dto.SuspiciousTransferDto;
import com.bank.antifraud.entity.SuspiciousTransfer;


public interface SuspiciousTransferMapper<T extends SuspiciousTransfer, R extends SuspiciousTransferDto> {
    R toDto(T object);

    T fromDto(R object);


}
