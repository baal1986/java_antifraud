package com.bank.antifraud.mapper;

import com.bank.antifraud.dto.SuspiciousTransferDto;
import com.bank.antifraud.entity.SuspiciousTransfer;


class SuspiciousTransferMapperTest<T extends SuspiciousTransfer, R extends SuspiciousTransferDto> {

    R toDto(T suspiciousTransfer, SuspiciousTransferMapper<T, R> suspiciousTransferMapper) {
        return suspiciousTransferMapper.toDto(suspiciousTransfer);
    }

    T fromDto(R suspiciousTransferDto, SuspiciousTransferMapper<T, R> suspiciousTransferMapper) {
        return suspiciousTransferMapper.fromDto(suspiciousTransferDto);
    }
}