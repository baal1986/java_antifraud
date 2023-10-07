package com.bank.antifraud.mapper;


import com.bank.antifraud.dto.SuspiciousTransferDtoImpl;
import com.bank.antifraud.entity.SuspiciousPhoneTransfer;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SuspiciousPhoneTransferMapper
        extends SuspiciousTransferMapper<SuspiciousPhoneTransfer, SuspiciousTransferDtoImpl> {

    @Mapping(source = "phoneTransferId", target = "transferId")
    @Override
    SuspiciousTransferDtoImpl toDto(SuspiciousPhoneTransfer object);


    @Mapping(source = "transferId", target = "phoneTransferId")
    @Override
    SuspiciousPhoneTransfer fromDto(SuspiciousTransferDtoImpl object);
}
