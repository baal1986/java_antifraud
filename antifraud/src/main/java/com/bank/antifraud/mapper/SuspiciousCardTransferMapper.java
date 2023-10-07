package com.bank.antifraud.mapper;


import com.bank.antifraud.dto.SuspiciousTransferDtoImpl;
import com.bank.antifraud.entity.SuspiciousCardTransfer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SuspiciousCardTransferMapper
        extends SuspiciousTransferMapper<SuspiciousCardTransfer, SuspiciousTransferDtoImpl> {

    @Mapping(source = "cardTransferId", target = "transferId")
    @Override
    SuspiciousTransferDtoImpl toDto(SuspiciousCardTransfer object);

    @Mapping(source = "transferId", target = "cardTransferId")
    @Override
    SuspiciousCardTransfer fromDto(SuspiciousTransferDtoImpl object);
}