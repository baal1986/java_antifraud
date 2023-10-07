package com.bank.antifraud.repository;

import com.bank.antifraud.entity.SuspiciousPhoneTransfer;
import org.springframework.stereotype.Repository;

@Repository
public interface SuspiciousPhoneTransferRepository
        extends SuspiciousTransferRepository<SuspiciousPhoneTransfer> {
    boolean existsByPhoneTransferId(Long id);
}
