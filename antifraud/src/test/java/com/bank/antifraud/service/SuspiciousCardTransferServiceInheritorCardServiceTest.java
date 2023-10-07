package com.bank.antifraud.service;


import com.bank.antifraud.mapper.SuspiciousCardTransferMapper;
import com.bank.antifraud.repository.SuspiciousCardTransferRepository;

import org.junit.jupiter.api.Test;

import org.mockito.Mock;


import javax.persistence.EntityNotFoundException;


import static org.junit.jupiter.api.Assertions.fail;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


class SuspiciousCardTransferServiceInheritorCardServiceTest {

    @Mock
    SuspiciousCardTransferRepository suspiciousCardTransferRepository;
    @Mock
    SuspiciousCardTransferMapper suspiciousCardTransferMapper;
    SuspiciousCardTransferService suspiciousCardTransferService;


    @Test
    void testConstruction() {

        suspiciousCardTransferService = new SuspiciousCardTransferService(suspiciousCardTransferRepository, suspiciousCardTransferMapper);
        SuspiciousCardTransferService spy = spy(suspiciousCardTransferService);

        doReturn(null).when(spy).findAll();
        var res = spy.findAll();


    }

    @Test
    void testConstruction2() {

        suspiciousCardTransferService = new SuspiciousCardTransferService(suspiciousCardTransferRepository, suspiciousCardTransferMapper);
        SuspiciousCardTransferService spy = spy(suspiciousCardTransferService);

        doThrow(new EntityNotFoundException()).when(spy).findAll();

        try {
            spy.findAll();
            fail("-");
        } catch (EntityNotFoundException ignored) {
        }

        verify(spy, times(1)).findAll();


    }
}