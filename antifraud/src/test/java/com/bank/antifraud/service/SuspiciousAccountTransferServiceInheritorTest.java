package com.bank.antifraud.service;

import com.bank.antifraud.dto.SuspiciousTransferDtoErrorImpl;
import com.bank.antifraud.dto.SuspiciousTransferDtoImpl;
import com.bank.antifraud.entity.SuspiciousAccountTransfer;
import com.bank.antifraud.mapper.SuspiciousAccountTransferMapperImpl;
import com.bank.antifraud.mapper.SuspiciousTransferMapper;
import com.bank.antifraud.repository.SuspiciousAccountTransferRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import paramResolver.SuspiciousAccountTransferMapperParamResolver;

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
@ExtendWith({SuspiciousAccountTransferMapperParamResolver.class})
public class SuspiciousAccountTransferServiceInheritorTest {
    @Mock
    SuspiciousAccountTransferRepository suspiciousAccountTransferRepository;

    @Mock
    SuspiciousTransferMapper<SuspiciousAccountTransfer, SuspiciousTransferDtoImpl> suspiciousTransferMapper;

    @InjectMocks
    SuspiciousAccountTransferService suspiciousAccountTransferService;

    @InjectMocks
    SuspiciousTransferService<SuspiciousAccountTransfer, SuspiciousTransferDtoImpl> suspiciousTransferService;

    SuspiciousAccountTransferMapperImpl suspiciousAccountTransferMapper = new SuspiciousAccountTransferMapperImpl();
    private SuspiciousTransferDtoImpl dto;

    SuspiciousAccountTransfer suspiciousAccountTransfer;


    SuspiciousTransferService<SuspiciousAccountTransfer, SuspiciousTransferDtoImpl> spy
            = Mockito.spy(new SuspiciousTransferService<SuspiciousAccountTransfer, SuspiciousTransferDtoImpl>
                            (suspiciousAccountTransferRepository,suspiciousTransferMapper));
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
    void findAllShouldThrowEntityNotFoundExceptionAndReturnList() {
        List<SuspiciousTransferDtoImpl> result = null;
        doThrow(EntityNotFoundException.class).when(suspiciousAccountTransferRepository).findAll();
        try {
            result = suspiciousAccountTransferService.findAll();
        } catch (EntityNotFoundException ignored) {
            result = new ArrayList<>();
            result.add(new SuspiciousTransferDtoErrorImpl("Suspicious Account Transfer not found!"));
        }
        assertThat(result).usingRecursiveComparison()
                .isEqualTo(List.of(new SuspiciousTransferDtoErrorImpl("Suspicious Account Transfer not found!")));
    }

    @Test
    void findByIdShouldThrowEntityNotFoundExceptionAndReturnEntity() {
        SuspiciousTransferDtoImpl result = null;
        doThrow(EntityNotFoundException.class).when(suspiciousAccountTransferRepository).findById(1L);
        try {
            result = suspiciousAccountTransferService.findById(1L);
        } catch (EntityNotFoundException ignored) {
            result = new SuspiciousTransferDtoErrorImpl("Suspicious Account Transfer not found!");
        }
        assertThat(result).usingRecursiveComparison()
                .isEqualTo(new SuspiciousTransferDtoErrorImpl("Suspicious Account Transfer not found!"));
    }

    @Test
    void saveShouldReturnNull() {
        doReturn(true)
                .when(suspiciousAccountTransferRepository).existsByAccountTransferId(555L);
        SuspiciousAccountTransfer suspiciousAccountTransfer = suspiciousAccountTransferService.save(dto);
        assertNull(suspiciousAccountTransfer);
    }

    @Test
    void updateShouldThrowEntityNotFoundException() {

        doThrow(new NullPointerException())
                .when(suspiciousAccountTransferRepository).findById(1L);
        try {
          suspiciousAccountTransferService.update(1L, null);
           fail("-");
        } catch (NullPointerException ignored) {}
        verify(suspiciousAccountTransferRepository, times(1)).findById(any());
    }

    @Test
    void deleteShouldThrowEntityNotFoundException(){
        doThrow(new NullPointerException())
                .when(suspiciousAccountTransferRepository).findById(1L);

        try {
            suspiciousAccountTransferService.delete(1L);
            fail("-");
        } catch (NullPointerException ignored) {
        }
        verify(suspiciousAccountTransferRepository, times(1)).findById(any());
    }

}
