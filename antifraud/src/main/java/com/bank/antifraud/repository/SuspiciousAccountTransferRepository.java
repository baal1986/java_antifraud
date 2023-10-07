package com.bank.antifraud.repository;

import com.bank.antifraud.entity.SuspiciousAccountTransfer;
import org.springframework.stereotype.Repository;


@Repository
public interface SuspiciousAccountTransferRepository
        extends SuspiciousTransferRepository<SuspiciousAccountTransfer> {

    boolean existsByAccountTransferId(Long id);
}
