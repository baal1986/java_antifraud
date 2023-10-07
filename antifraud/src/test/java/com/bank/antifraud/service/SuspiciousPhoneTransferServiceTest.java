package com.bank.antifraud.service;

import com.bank.antifraud.dto.SuspiciousTransferDtoImpl;
import com.bank.antifraud.entity.SuspiciousPhoneTransfer;
import com.bank.antifraud.mapper.SuspiciousPhoneTransferMapperImpl;
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
class SuspiciousPhoneTransferServiceTest
        extends SuspiciousTransferServiceTest<SuspiciousPhoneTransfer, SuspiciousTransferDtoImpl> {


    @Mock
    SuspiciousTransferRepository<SuspiciousPhoneTransfer> suspiciousTransferRepository;

    @Mock
    SuspiciousTransferMapper<SuspiciousPhoneTransfer, SuspiciousTransferDtoImpl> suspiciousTransferMapper;

    @InjectMocks
    SuspiciousTransferService<SuspiciousPhoneTransfer, SuspiciousTransferDtoImpl> suspiciousTransferService;

    SuspiciousPhoneTransferMapperImpl suspiciousPhoneTransferMapper = new SuspiciousPhoneTransferMapperImpl();
    SuspiciousTransferDtoImpl dto;
    SuspiciousPhoneTransfer suspiciousPhoneTransfer;

    @BeforeEach
    void setDto() {
        suspiciousPhoneTransfer = new SuspiciousPhoneTransfer();
        suspiciousPhoneTransfer.setId(1L);
        suspiciousPhoneTransfer.setIsSuspicious(true);
        suspiciousPhoneTransfer.setPhoneTransferId(555L);
        suspiciousPhoneTransfer.setSuspiciousReason("");
        suspiciousPhoneTransfer.setBlockedReason("");
        suspiciousPhoneTransfer.setIsBlocked(true);
        dto = suspiciousPhoneTransferMapper.toDto(suspiciousPhoneTransfer);
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
                new SuspiciousPhoneTransfer(555L), new SuspiciousTransferDtoImpl(),
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
        super.updateShouldCall(suspiciousTransferRepository, suspiciousTransferService, 1L, dto, suspiciousPhoneTransfer);
    }

    @Test
    void updateShouldThrowEntityNotFoundException() {
        super.updateShouldThrowEntityNotFoundException(suspiciousTransferRepository, suspiciousTransferService, 1L, dto, suspiciousPhoneTransfer);
    }

    @Test
    void deleteShouldThrowEntityNotFoundException() {
        super.deleteShouldThrowEntityNotFoundException(suspiciousTransferRepository, suspiciousTransferService, 1L, dto, suspiciousPhoneTransfer);
    }

    @Test
    void deleteShouldThrowSuspiciousTransferServiceException() {
        super.deleteShouldThrowSuspiciousTransferServiceException(suspiciousTransferRepository, suspiciousTransferService, 1L, dto, suspiciousPhoneTransfer);
    }

}