package com.bank.antifraud.service;


import com.bank.antifraud.dto.SuspiciousTransferDtoErrorImpl;
import com.bank.antifraud.dto.SuspiciousTransferDtoImpl;
import com.bank.antifraud.entity.SuspiciousCardTransfer;
import com.bank.antifraud.entity.SuspiciousPhoneTransfer;
import com.bank.antifraud.exception.service.SuspiciousTransferServiceException;
import com.bank.antifraud.mapper.SuspiciousPhoneTransferMapper;
import com.bank.antifraud.repository.SuspiciousCardTransferRepository;
import com.bank.antifraud.repository.SuspiciousPhoneTransferRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SuspiciousPhoneTransferService
        extends SuspiciousTransferService<SuspiciousPhoneTransfer, SuspiciousTransferDtoImpl> {

    public SuspiciousPhoneTransferService(SuspiciousPhoneTransferRepository suspiciousPhoneTransferRepository,
                                          SuspiciousPhoneTransferMapper suspiciousPhoneTransferMapper) {
        super(suspiciousPhoneTransferRepository, suspiciousPhoneTransferMapper);
    }

    public List<SuspiciousTransferDtoImpl> findAll() {
        List<SuspiciousTransferDtoImpl> result = null;
        try {
            result = super.findAll();
        } catch (EntityNotFoundException e) {
            result = new ArrayList<>();
            result.add(new SuspiciousTransferDtoErrorImpl("Suspicious Phone Transfer not found!"));
        }
        return result;
    }

    public SuspiciousTransferDtoImpl findById(Long id) {
        SuspiciousTransferDtoImpl result = null;
        try {
            result = super.findById(id);
        } catch (SuspiciousTransferServiceException | EntityNotFoundException e) {
            result = new SuspiciousTransferDtoErrorImpl("Suspicious Phone Transfer not found!");
        }
        return result;
    }

    public SuspiciousPhoneTransfer save(SuspiciousTransferDtoImpl suspiciousTransfer) {
        SuspiciousPhoneTransfer result = null;
        if (!((SuspiciousPhoneTransferRepository) super.getSuspiciousTransferRepository())
                .existsByPhoneTransferId(suspiciousTransfer.getTransferId())) {
            result = super.save(suspiciousTransfer);
        }
        return result;
    }

    public SuspiciousPhoneTransfer update(Long id, SuspiciousTransferDtoImpl suspiciousTransfer) {
        SuspiciousPhoneTransfer result = null;
        try {
            result = super.update(id, suspiciousTransfer);
        } catch (EntityNotFoundException ignored) {

        }
        return result;
    }

    public SuspiciousPhoneTransfer delete(Long id) {
        SuspiciousPhoneTransfer result = null;
        try {
            result = super.delete(id);
        } catch (SuspiciousTransferServiceException | EntityNotFoundException ignored) {

        }
        return result;
    }
}
