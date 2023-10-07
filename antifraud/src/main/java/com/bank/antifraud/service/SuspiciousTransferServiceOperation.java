package com.bank.antifraud.service;

import com.bank.antifraud.dto.SuspiciousTransferDto;
import com.bank.antifraud.entity.SuspiciousTransfer;
import com.bank.antifraud.exception.service.SuspiciousTransferServiceException;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public interface SuspiciousTransferServiceOperation<T extends SuspiciousTransfer,
        R extends SuspiciousTransferDto> {
    List<R> findAll() throws EntityNotFoundException;

    R findById(Long id) throws EntityNotFoundException;

    T save(R suspiciousTransfer);

    T update(Long id, R suspiciousTransfer) throws EntityNotFoundException;

    T delete(Long id) throws SuspiciousTransferServiceException, EntityNotFoundException;
}
