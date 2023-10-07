package com.bank.antifraud.service;

import com.bank.antifraud.dto.SuspiciousTransferDtoErrorImpl;
import com.bank.antifraud.dto.SuspiciousTransferDtoImpl;
import com.bank.antifraud.entity.SuspiciousCardTransfer;
import com.bank.antifraud.mapper.SuspiciousCardTransferMapperImpl;
import com.bank.antifraud.mapper.SuspiciousTransferMapper;
import com.bank.antifraud.repository.SuspiciousCardTransferRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import paramResolver.SuspiciousCardTransferMapperParamResolver;

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
import static org.mockito.Mockito.when;


@ExtendWith({MockitoExtension.class})
@MockitoSettings(strictness = Strictness.LENIENT)
@ExtendWith({SuspiciousCardTransferMapperParamResolver.class})
public class SuspiciousCardTransferServiceInheritorTest {
    @Mock
    SuspiciousCardTransferRepository suspiciousCardTransferRepository;

    @Mock
    SuspiciousTransferMapper<SuspiciousCardTransfer, SuspiciousTransferDtoImpl> suspiciousTransferMapper;

    @InjectMocks
    SuspiciousCardTransferService suspiciousCardTransferService;

    @InjectMocks
    SuspiciousTransferService<SuspiciousCardTransfer, SuspiciousTransferDtoImpl> suspiciousTransferService;

    SuspiciousCardTransferMapperImpl suspiciousCardTransferMapper = new SuspiciousCardTransferMapperImpl();
    private SuspiciousTransferDtoImpl dto;

    SuspiciousCardTransfer suspiciousCardTransfer;


    SuspiciousTransferService<SuspiciousCardTransfer, SuspiciousTransferDtoImpl> spy
            = Mockito.spy(new SuspiciousTransferService<SuspiciousCardTransfer, SuspiciousTransferDtoImpl>
            (suspiciousCardTransferRepository,suspiciousTransferMapper));
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
    void findAllShouldThrowEntityNotFoundExceptionAndReturnList() {
        List<SuspiciousTransferDtoImpl> result = null;
        doThrow(EntityNotFoundException.class).when(suspiciousCardTransferRepository).findAll();
        try {
            result = suspiciousCardTransferService.findAll();
        } catch (EntityNotFoundException ignored) {
            result = new ArrayList<>();
            result.add(new SuspiciousTransferDtoErrorImpl("Suspicious Card Transfer not found!"));
        }
        assertThat(result).usingRecursiveComparison()
                .isEqualTo(List.of(new SuspiciousTransferDtoErrorImpl("Suspicious Card Transfer not found!")));
    }

    @Test
    void findByIdShouldThrowEntityNotFoundExceptionAndReturnEntity() {
        SuspiciousTransferDtoImpl result = null;
        doThrow(EntityNotFoundException.class).when(suspiciousCardTransferRepository).findById(1L);
        try {
            result = suspiciousCardTransferService.findById(1L);
        } catch (EntityNotFoundException ignored) {
            result = new SuspiciousTransferDtoErrorImpl("Suspicious Card Transfer not found!");
        }
        assertThat(result).usingRecursiveComparison()
                .isEqualTo(new SuspiciousTransferDtoErrorImpl("Suspicious Card Transfer not found!"));
    }

    @Test
    void saveShouldReturnNull() {
        doReturn(true)
                .when(suspiciousCardTransferRepository).existsByCardTransferId(555L);
        SuspiciousCardTransfer suspiciousCardTransfer = suspiciousCardTransferService.save(dto);
        assertNull(suspiciousCardTransfer);
    }

    @Test
    void updateShouldThrowEntityNotFoundException() {

        doThrow(new NullPointerException())
                .when(suspiciousCardTransferRepository).findById(1L);
        try {
            suspiciousCardTransferService.update(1L, null);
            fail("-");
        } catch (NullPointerException ignored) {}
        verify(suspiciousCardTransferRepository, times(1)).findById(any());
    }

    @Test
    void deleteShouldThrowNullPointerException(){
        doThrow(new NullPointerException())
                .when(suspiciousCardTransferRepository).findById(1L);

        try {
            suspiciousCardTransferService.delete(1L);
            fail("-");
        } catch (NullPointerException ignored) {
        }
        verify(suspiciousCardTransferRepository, times(1)).findById(any());
    }
/**/
    @Test
    void deleteShouldThrowEntityNotFoundException(){
        when(suspiciousCardTransferRepository.findById(1L)).thenThrow(new RuntimeException());

        try {
            suspiciousCardTransferService.delete(1L);
            fail("-");
        } catch (RuntimeException  ignored) {
        }
        verify(suspiciousCardTransferRepository, times(1)).findById(any());

    }

}
