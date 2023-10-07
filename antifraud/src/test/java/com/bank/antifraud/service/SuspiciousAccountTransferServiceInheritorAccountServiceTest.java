package com.bank.antifraud.service;


import com.bank.antifraud.dto.SuspiciousTransferDtoImpl;
import com.bank.antifraud.entity.SuspiciousAccountTransfer;
import com.bank.antifraud.mapper.SuspiciousAccountTransferMapper;
import com.bank.antifraud.repository.SuspiciousAccountTransferRepository;
import com.bank.antifraud.repository.SuspiciousTransferRepository;


import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;


import javax.persistence.EntityNotFoundException;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;


import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;



class SuspiciousAccountTransferServiceInheritorAccountServiceTest {

    @Mock
    SuspiciousAccountTransferRepository suspiciousAccountTransferRepository;

    @Mock
    SuspiciousTransferRepository<SuspiciousAccountTransfer> suspiciousTransferRepository;
    @Mock
    SuspiciousAccountTransferMapper suspiciousAccountTransferMapper;

    @Spy
    @InjectMocks
    SuspiciousAccountTransferService suspiciousAccountTransferService = new SuspiciousAccountTransferService(suspiciousAccountTransferRepository, suspiciousAccountTransferMapper);
    @Spy
    @InjectMocks
    SuspiciousTransferService<SuspiciousAccountTransfer, SuspiciousTransferDtoImpl> suspiciousTransferService
            = new SuspiciousTransferService<>(suspiciousAccountTransferRepository, suspiciousAccountTransferMapper);

    @Test
    void testConstruction() {

        List<SuspiciousAccountTransfer> suspiciousAccountTransferList = new ArrayList<>();
        suspiciousAccountTransferList.add(new SuspiciousAccountTransfer());

        suspiciousAccountTransferRepository = mock(SuspiciousAccountTransferRepository.class);
        suspiciousTransferRepository =  mock(SuspiciousTransferRepository.class);
        suspiciousAccountTransferService = mock(SuspiciousAccountTransferService.class);
        suspiciousTransferService = mock(SuspiciousAccountTransferService.class);
        doReturn(suspiciousAccountTransferList).when(suspiciousAccountTransferRepository).findAll();
        doReturn(suspiciousAccountTransferList).when(suspiciousTransferRepository).findAll();

        suspiciousTransferService.findAll();
        suspiciousAccountTransferService.findAll();

        verify(suspiciousAccountTransferService, times(1)).findAll();
        verify(suspiciousTransferService, times(1)).findAll();


    }

    @Test
    void testConstruction2() {

        suspiciousAccountTransferService = new SuspiciousAccountTransferService(suspiciousAccountTransferRepository, suspiciousAccountTransferMapper);
        SuspiciousAccountTransferService spy = spy(suspiciousAccountTransferService);

        doThrow(new EntityNotFoundException()).when(spy).findAll();

        try {
            spy.findAll();
            fail("-");
        } catch (EntityNotFoundException ignored) {
        }

        verify(spy, times(1)).findAll();


    }
}