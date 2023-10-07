package com.bank.antifraud.service;

import com.bank.antifraud.dto.SuspiciousTransferDtoErrorImpl;
import com.bank.antifraud.dto.SuspiciousTransferDtoImpl;
import com.bank.antifraud.entity.SuspiciousAccountTransfer;
import com.bank.antifraud.entity.SuspiciousCardTransfer;
import com.bank.antifraud.exception.service.SuspiciousTransferServiceException;
import com.bank.antifraud.mapper.SuspiciousCardTransferMapper;
import com.bank.antifraud.repository.SuspiciousAccountTransferRepository;
import com.bank.antifraud.repository.SuspiciousCardTransferRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SuspiciousCardTransferService
        extends SuspiciousTransferService<SuspiciousCardTransfer, SuspiciousTransferDtoImpl> {


    public SuspiciousCardTransferService(SuspiciousCardTransferRepository suspiciousCardTransferRepository,
                                         SuspiciousCardTransferMapper cardTransferMapper) {
        super(suspiciousCardTransferRepository, cardTransferMapper);
    }

    public List<SuspiciousTransferDtoImpl> findAll() {
        List<SuspiciousTransferDtoImpl> result = null;
        try {
            result = super.findAll();
        } catch (EntityNotFoundException e) {
            result = new ArrayList<>();
            result.add(new SuspiciousTransferDtoErrorImpl("Suspicious Card Transfer not found!"));
        }
        return result;
    }

    public SuspiciousTransferDtoImpl findById(Long id) {
        SuspiciousTransferDtoImpl result = null;
        try {
            result = super.findById(id);
        } catch (SuspiciousTransferServiceException | EntityNotFoundException e) {
            result = new SuspiciousTransferDtoErrorImpl("Suspicious Card Transfer not found!");
        }
        return result;
    }


    public SuspiciousCardTransfer save(SuspiciousTransferDtoImpl suspiciousTransfer) {
        SuspiciousCardTransfer result = null;
        if (!((SuspiciousCardTransferRepository) super.getSuspiciousTransferRepository())
                .existsByCardTransferId(suspiciousTransfer.getTransferId())) {
            result = super.save(suspiciousTransfer);
        }
        return result;
    }


    public SuspiciousCardTransfer update(Long id, SuspiciousTransferDtoImpl suspiciousTransfer) {
        SuspiciousCardTransfer result = null;
        try {
            result = super.update(id, suspiciousTransfer);
        } catch (EntityNotFoundException ignored) {

        }
        return result;
    }


    public SuspiciousCardTransfer delete(Long id) {
        SuspiciousCardTransfer result = null;
        try {
            result = super.delete(id);
        } catch (SuspiciousTransferServiceException | EntityNotFoundException ignored) {

        }
        return result;
    }

}
