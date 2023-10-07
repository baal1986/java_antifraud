package com.bank.antifraud.mapper;



import com.bank.antifraud.dto.SuspiciousTransferDtoImpl;
import com.bank.antifraud.entity.SuspiciousAccountTransfer;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SuspiciousAccountTransferMapper
        extends SuspiciousTransferMapper<SuspiciousAccountTransfer, SuspiciousTransferDtoImpl> {

    @Mapping(source = "accountTransferId", target = "transferId")
    @Override
    SuspiciousTransferDtoImpl toDto(SuspiciousAccountTransfer object);

    @Mapping(source = "transferId", target = "accountTransferId")
    @Override
    SuspiciousAccountTransfer fromDto(SuspiciousTransferDtoImpl object);
}
