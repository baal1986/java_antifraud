package com.bank.antifraud.repository;

import com.bank.antifraud.entity.SuspiciousTransfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;


@Service
public interface SuspiciousTransferRepository<T extends SuspiciousTransfer>
        extends JpaRepository<T, Long> {
}
