package com.bank.antifraud.repository;

import com.bank.antifraud.entity.SuspiciousCardTransfer;
import org.springframework.stereotype.Repository;

@Repository
public interface SuspiciousCardTransferRepository extends
        SuspiciousTransferRepository<SuspiciousCardTransfer> {

    boolean existsByCardTransferId(Long id);
}
