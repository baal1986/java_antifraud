package com.bank.antifraud.service;

import com.bank.antifraud.dto.SuspiciousTransferDtoErrorImpl;
import com.bank.antifraud.dto.SuspiciousTransferDtoImpl;
import com.bank.antifraud.entity.SuspiciousAccountTransfer;
import com.bank.antifraud.exception.service.SuspiciousTransferServiceException;
import com.bank.antifraud.mapper.SuspiciousAccountTransferMapper;
import com.bank.antifraud.repository.SuspiciousAccountTransferRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;


@Service
public class SuspiciousAccountTransferService
        extends SuspiciousTransferService<SuspiciousAccountTransfer, SuspiciousTransferDtoImpl> {


    public SuspiciousAccountTransferService(SuspiciousAccountTransferRepository suspiciousAccountTransferRepository,
                                            SuspiciousAccountTransferMapper suspiciousAccountTransferMapper) {
        super(suspiciousAccountTransferRepository, suspiciousAccountTransferMapper);
    }


    public List<SuspiciousTransferDtoImpl> findAll() {
        List<SuspiciousTransferDtoImpl> result = null;
        try {
            result = super.findAll();
        } catch (EntityNotFoundException e) {
            result = new ArrayList<>();
            result.add(new SuspiciousTransferDtoErrorImpl("Suspicious Account Transfer not found!"));
        }
        return result;
    }

    public SuspiciousTransferDtoImpl findById(Long id) {
        SuspiciousTransferDtoImpl result = null;
        try {
            result = super.findById(id);
        } catch (EntityNotFoundException e) {
            result = new SuspiciousTransferDtoErrorImpl("Suspicious Account Transfer not found!");
        }
        return result;
    }


    public SuspiciousAccountTransfer save(SuspiciousTransferDtoImpl suspiciousTransfer) {
        SuspiciousAccountTransfer result = null;
        if (!((SuspiciousAccountTransferRepository) super.getSuspiciousTransferRepository())
                .existsByAccountTransferId(suspiciousTransfer.getTransferId())) {
            result = super.save(suspiciousTransfer);
        }
        return result;
    }


    public SuspiciousAccountTransfer update(Long id, SuspiciousTransferDtoImpl suspiciousTransfer) {
        SuspiciousAccountTransfer result = null;
        try {
            result = super.update(id, suspiciousTransfer);
        } catch (EntityNotFoundException ignored) {
        }
        return result;
    }


    public SuspiciousAccountTransfer delete(Long id) {
        SuspiciousAccountTransfer result = null;
        try {
            result = super.delete(id);
        } catch (SuspiciousTransferServiceException | EntityNotFoundException ignored) {
        }
        return result;
    }
}
