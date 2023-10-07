package com.bank.antifraud.service;


import com.bank.antifraud.mapper.SuspiciousPhoneTransferMapper;
import com.bank.antifraud.repository.SuspiciousPhoneTransferRepository;

import org.junit.jupiter.api.Test;

import org.mockito.Mock;


import javax.persistence.EntityNotFoundException;


import static org.junit.jupiter.api.Assertions.fail;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


class SuspiciousPhoneTransferServiceInheritorPhoneServiceTest {

    @Mock
    SuspiciousPhoneTransferRepository suspiciousPhoneTransferRepository;
    @Mock
    SuspiciousPhoneTransferMapper suspiciousPhoneTransferMapper;
    SuspiciousPhoneTransferService suspiciousPhoneTransferService;


    @Test
    void testConstruction() {

        suspiciousPhoneTransferService = new SuspiciousPhoneTransferService(suspiciousPhoneTransferRepository, suspiciousPhoneTransferMapper);
        SuspiciousPhoneTransferService spy = spy(suspiciousPhoneTransferService);

        doReturn(null).when(spy).findAll();
        var res = spy.findAll();


    }

    @Test
    void testConstruction2() {

        suspiciousPhoneTransferService = new SuspiciousPhoneTransferService(suspiciousPhoneTransferRepository, suspiciousPhoneTransferMapper);
        SuspiciousPhoneTransferService spy = spy(suspiciousPhoneTransferService);

        doThrow(new EntityNotFoundException()).when(spy).findAll();

        try {
            spy.findAll();
            fail("-");
        } catch (EntityNotFoundException ignored) {
        }

        verify(spy, times(1)).findAll();


    }
}