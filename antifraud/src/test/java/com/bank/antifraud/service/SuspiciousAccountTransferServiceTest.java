package com.bank.antifraud.service;


import com.bank.antifraud.dto.SuspiciousTransferDtoImpl;
import com.bank.antifraud.entity.SuspiciousAccountTransfer;
import com.bank.antifraud.mapper.SuspiciousAccountTransferMapperImpl;
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
class SuspiciousAccountTransferServiceTest
        extends SuspiciousTransferServiceTest<SuspiciousAccountTransfer, SuspiciousTransferDtoImpl> {


    @Mock
    SuspiciousTransferRepository<SuspiciousAccountTransfer> suspiciousTransferRepository;

    @Mock
    SuspiciousTransferMapper<SuspiciousAccountTransfer, SuspiciousTransferDtoImpl> suspiciousTransferMapper;

    @InjectMocks
    SuspiciousTransferService<SuspiciousAccountTransfer, SuspiciousTransferDtoImpl> suspiciousTransferService;

    SuspiciousAccountTransferMapperImpl suspiciousAccountTransferMapper = new SuspiciousAccountTransferMapperImpl();
    SuspiciousTransferDtoImpl dto;
    SuspiciousAccountTransfer suspiciousAccountTransfer;

    @BeforeEach
    void setDto() {
        suspiciousAccountTransfer = new SuspiciousAccountTransfer();
        suspiciousAccountTransfer.setId(1L);
        suspiciousAccountTransfer.setIsSuspicious(true);
        suspiciousAccountTransfer.setAccountTransferId(555L);
        suspiciousAccountTransfer.setSuspiciousReason("");
        suspiciousAccountTransfer.setBlockedReason("");
        suspiciousAccountTransfer.setIsBlocked(true);
        dto = suspiciousAccountTransferMapper.toDto(suspiciousAccountTransfer);
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
                new SuspiciousAccountTransfer(555L), new SuspiciousTransferDtoImpl(),
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
        super.updateShouldCall(suspiciousTransferRepository, suspiciousTransferService, 1L, dto, suspiciousAccountTransfer);
    }

    @Test
    void updateShouldThrowEntityNotFoundException() {
        super.updateShouldThrowEntityNotFoundException(suspiciousTransferRepository, suspiciousTransferService, 1L, dto, suspiciousAccountTransfer);
    }

    @Test
    void deleteShouldThrowEntityNotFoundException() {
        super.deleteShouldThrowEntityNotFoundException(suspiciousTransferRepository, suspiciousTransferService, 1L, dto, suspiciousAccountTransfer);
    }

    @Test
    void deleteShouldThrowSuspiciousTransferServiceException() {
        super.deleteShouldThrowSuspiciousTransferServiceException(suspiciousTransferRepository, suspiciousTransferService, 1L, dto, suspiciousAccountTransfer);
    }





}