package com.bank.antifraud.service;

import com.bank.antifraud.dto.SuspiciousTransferDto;
import com.bank.antifraud.entity.SuspiciousTransfer;
import com.bank.antifraud.exception.service.SuspiciousTransferServiceException;
import com.bank.antifraud.mapper.SuspiciousTransferMapper;
import com.bank.antifraud.repository.SuspiciousTransferRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Fail.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


class SuspiciousTransferServiceTest<T extends SuspiciousTransfer, R extends SuspiciousTransferDto> {

    void findAllShouldThrowException(SuspiciousTransferRepository<T> suspiciousTransferRepository,
                                     SuspiciousTransferService<T, R> suspiciousTransferService) {
        doThrow(EntityNotFoundException.class).when(suspiciousTransferRepository).findAll();
        try {
            suspiciousTransferService.findAll();
            //fail("EntityNotFoundException");
        } catch (EntityNotFoundException ignored) {
        }
    }

    void findAllShouldReturnEmptyList(SuspiciousTransferRepository<T> suspiciousTransferRepository,
                                      SuspiciousTransferService<T, R> suspiciousTransferService) {
        doReturn(List.of()).when(suspiciousTransferRepository).findAll();
        assertThat(suspiciousTransferService.findAll()).isEmpty();
    }

    void findByIdShouldReturnEntity(SuspiciousTransferRepository<T> suspiciousTransferRepository,
                                    SuspiciousTransferService<T, R> suspiciousTransferService,
                                    T suspiciousTransfer, R suspiciousTransferDto,
                                    SuspiciousTransferMapper<T, R> suspiciousTransferMapper) {

        doReturn(Optional.of(suspiciousTransfer)).when(suspiciousTransferRepository).findById(1L);
        suspiciousTransferDto = suspiciousTransferService.findById(1L);
        assertEquals(suspiciousTransferMapper.toDto(suspiciousTransfer), suspiciousTransferDto);
        verify(suspiciousTransferRepository, times(1)).findById(any());
    }


    void findByIdShouldThrowEntityNotFoundException(SuspiciousTransferRepository<T> suspiciousTransferRepository,
                                                    SuspiciousTransferService<T, R> suspiciousTransferService) {
        doReturn(Optional.empty()).when(suspiciousTransferRepository).findById(1L);
        try {
            suspiciousTransferService.findById(1L);
            fail("EntityNotFoundException");
        } catch (EntityNotFoundException ignored) {
        }
    }

    void updateShouldCall(SuspiciousTransferRepository<T> suspiciousTransferRepository,
                          SuspiciousTransferService<T, R> suspiciousTransferService,
                          Long id, R dto, T suspiciousTransfer) {
        doReturn(Optional.of(suspiciousTransfer)).when(suspiciousTransferRepository).findById(id);
        suspiciousTransferService.update(id, dto);
        verify(suspiciousTransferRepository, times(1)).findById(id);
        verify(suspiciousTransferRepository, times(1)).save(any());
    }

    void updateShouldThrowEntityNotFoundException(SuspiciousTransferRepository<T> suspiciousTransferRepository,
                                                  SuspiciousTransferService<T, R> suspiciousTransferService,
                                                  Long id, R dto, T suspiciousTransfer) {
        doReturn(Optional.empty()).when(suspiciousTransferRepository).findById(id);
        try {
            suspiciousTransferService.update(id, dto);
            fail("EntityNotFoundException");
        } catch (EntityNotFoundException ignored) {
        }
    }

    void deleteShouldThrowEntityNotFoundException(SuspiciousTransferRepository<T> suspiciousTransferRepository,
                                                  SuspiciousTransferService<T, R> suspiciousTransferService,
                                                  Long id, R dto, T suspiciousTransfer) {
        doReturn(Optional.empty()).when(suspiciousTransferRepository).findById(id);
        try {
            suspiciousTransferService.delete(id);
            fail("EntityNotFoundException");
        } catch (EntityNotFoundException ignored) {
        }

    }

    void deleteShouldThrowSuspiciousTransferServiceException(SuspiciousTransferRepository<T> suspiciousTransferRepository,
                                                             SuspiciousTransferService<T, R> suspiciousTransferService,
                                                             Long id, R dto, T suspiciousTransfer) {

        doReturn(Optional.of(suspiciousTransfer)).when(suspiciousTransferRepository).findById(id);
        doThrow(SuspiciousTransferServiceException.class).when(suspiciousTransferRepository).deleteById(id);
        try {
            suspiciousTransferService.delete(id);
            fail("SuspiciousTransferServiceException");
        } catch (SuspiciousTransferServiceException ignored) {
        }
    }

}