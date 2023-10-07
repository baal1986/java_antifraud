package com.bank.antifraud.service;

import com.bank.antifraud.dto.SuspiciousTransferDtoImpl;
import com.bank.antifraud.entity.SuspiciousCardTransfer;
import com.bank.antifraud.mapper.SuspiciousCardTransferMapperImpl;
import com.bank.antifraud.mapper.SuspiciousTransferMapper;
import com.bank.antifraud.repository.SuspiciousTransferRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@ExtendWith({MockitoExtension.class})
@MockitoSettings(strictness = Strictness.LENIENT)
class SuspiciousCardTransferServiceTest
        extends SuspiciousTransferServiceTest<SuspiciousCardTransfer, SuspiciousTransferDtoImpl> {


    @Mock
    SuspiciousTransferRepository<SuspiciousCardTransfer> suspiciousTransferRepository;

    @Mock
    SuspiciousTransferMapper<SuspiciousCardTransfer, SuspiciousTransferDtoImpl> suspiciousTransferMapper;

    @InjectMocks
    SuspiciousTransferService<SuspiciousCardTransfer, SuspiciousTransferDtoImpl> suspiciousTransferService;

    SuspiciousCardTransferMapperImpl suspiciousCardTransferMapper = new SuspiciousCardTransferMapperImpl();
    SuspiciousTransferDtoImpl dto;
    SuspiciousCardTransfer suspiciousCardTransfer;

    @BeforeEach
    void setDto() {
        suspiciousCardTransfer = new SuspiciousCardTransfer();
        suspiciousCardTransfer.setId(1L);
        suspiciousCardTransfer.setIsSuspicious(true);
        suspiciousCardTransfer.setCardTransferId(555L);
        suspiciousCardTransfer.setSuspiciousReason("");
        suspiciousCardTransfer.setBlockedReason("");
        suspiciousCardTransfer.setIsBlocked(true);
        dto = suspiciousCardTransferMapper.toDto(suspiciousCardTransfer);
    }

    @Test
    void findAllShouldReturnEmptyList() {
        super.findAllShouldReturnEmptyList(suspiciousTransferRepository, suspiciousTransferService);
    }

    @Test
    void findAllShouldThrowException() {
        super.findAllShouldThrowException(suspiciousTransferRepository, suspiciousTransferService);
    }

    @Test
    void findByIdShouldReturnEntity() {
        super.findByIdShouldReturnEntity(suspiciousTransferRepository, suspiciousTransferService,
                new SuspiciousCardTransfer(555L), new SuspiciousTransferDtoImpl(),
                suspiciousTransferMapper);
    }

    @Test
    void findByIdShouldThrowEntityNotFoundException() {
        super.findByIdShouldThrowEntityNotFoundException(suspiciousTransferRepository, suspiciousTransferService);
    }

    @Test
    void saveShouldThrowException() {
        suspiciousTransferService.save(dto);
        verify(suspiciousTransferRepository, times(1)).save(any());
    }

    @Test
    void updateShouldCall() {
        super.updateShouldCall(suspiciousTransferRepository, suspiciousTransferService, 1L, dto, suspiciousCardTransfer);
    }

    @Test
    void updateShouldThrowEntityNotFoundException() {
        super.updateShouldThrowEntityNotFoundException(suspiciousTransferRepository, suspiciousTransferService, 1L, dto, suspiciousCardTransfer);
    }

    @Test
    void deleteShouldThrowEntityNotFoundException() {
        super.deleteShouldThrowEntityNotFoundException(suspiciousTransferRepository, suspiciousTransferService, 1L, dto, suspiciousCardTransfer);
    }

    @Test
    void deleteShouldThrowSuspiciousTransferServiceException() {
        super.deleteShouldThrowSuspiciousTransferServiceException(suspiciousTransferRepository, suspiciousTransferService, 1L, dto, suspiciousCardTransfer);
    }

}