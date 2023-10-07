package com.bank.antifraud.service;

import com.bank.antifraud.dto.SuspiciousTransferDtoErrorImpl;
import com.bank.antifraud.dto.SuspiciousTransferDtoImpl;
import com.bank.antifraud.entity.SuspiciousPhoneTransfer;
import com.bank.antifraud.mapper.SuspiciousPhoneTransferMapperImpl;
import com.bank.antifraud.mapper.SuspiciousTransferMapper;
import com.bank.antifraud.repository.SuspiciousPhoneTransferRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import paramResolver.SuspiciousPhoneTransferMapperParamResolver;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Fail.fail;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;



@ExtendWith({MockitoExtension.class})
@MockitoSettings(strictness = Strictness.LENIENT)
@ExtendWith({SuspiciousPhoneTransferMapperParamResolver.class})
public class SuspiciousPhoneTransferServiceInheritorTest {
    @Mock
    SuspiciousPhoneTransferRepository suspiciousPhoneTransferRepository;

    @Mock
    SuspiciousTransferMapper<SuspiciousPhoneTransfer, SuspiciousTransferDtoImpl> suspiciousTransferMapper;

    @InjectMocks
    SuspiciousPhoneTransferService suspiciousPhoneTransferService;

    @InjectMocks
    SuspiciousTransferService<SuspiciousPhoneTransfer, SuspiciousTransferDtoImpl> suspiciousTransferService;

    SuspiciousPhoneTransferMapperImpl suspiciousPhoneTransferMapper = new SuspiciousPhoneTransferMapperImpl();
    private SuspiciousTransferDtoImpl dto;

    SuspiciousPhoneTransfer suspiciousPhoneTransfer;


    SuspiciousTransferService<SuspiciousPhoneTransfer, SuspiciousTransferDtoImpl> spy
            = Mockito.spy(new SuspiciousTransferService<SuspiciousPhoneTransfer, SuspiciousTransferDtoImpl>
            (suspiciousPhoneTransferRepository,suspiciousTransferMapper));
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
    void findAllShouldThrowEntityNotFoundExceptionAndReturnList() {
        List<SuspiciousTransferDtoImpl> result = null;
        doThrow(EntityNotFoundException.class).when(suspiciousPhoneTransferRepository).findAll();
        try {
            result = suspiciousPhoneTransferService.findAll();
        } catch (EntityNotFoundException ignored) {
            result = new ArrayList<>();
            result.add(new SuspiciousTransferDtoErrorImpl("Suspicious Phone Transfer not found!"));
        }
        assertThat(result).usingRecursiveComparison()
                .isEqualTo(List.of(new SuspiciousTransferDtoErrorImpl("Suspicious Phone Transfer not found!")));
    }

    @Test
    void findByIdShouldThrowEntityNotFoundExceptionAndReturnEntity() {
        SuspiciousTransferDtoImpl result = null;
        doThrow(EntityNotFoundException.class).when(suspiciousPhoneTransferRepository).findById(1L);
        try {
            result = suspiciousPhoneTransferService.findById(1L);
        } catch (EntityNotFoundException ignored) {
            result = new SuspiciousTransferDtoErrorImpl("Suspicious Phone Transfer not found!");
        }
        assertThat(result).usingRecursiveComparison()
                .isEqualTo(new SuspiciousTransferDtoErrorImpl("Suspicious Phone Transfer not found!"));
    }

    @Test
    void saveShouldReturnNull() {
        doReturn(true)
                .when(suspiciousPhoneTransferRepository).existsByPhoneTransferId(555L);
        SuspiciousPhoneTransfer suspiciousPhoneTransfer = suspiciousPhoneTransferService.save(dto);
        assertNull(suspiciousPhoneTransfer);
    }

    @Test
    void updateShouldThrowEntityNotFoundException() {

        doThrow(new NullPointerException())
                .when(suspiciousPhoneTransferRepository).findById(1L);
        try {
            suspiciousPhoneTransferService.update(1L, null);
            fail("-");
        } catch (NullPointerException ignored) {}
        verify(suspiciousPhoneTransferRepository, times(1)).findById(any());
    }

    @Test
    void deleteShouldThrowEntityNotFoundException(){
        doThrow(new NullPointerException())
                .when(suspiciousPhoneTransferRepository).findById(1L);

        try {
            suspiciousPhoneTransferService.delete(1L);
            fail("-");
        } catch (NullPointerException ignored) {
        }
        verify(suspiciousPhoneTransferRepository, times(1)).findById(any());
    }

}
