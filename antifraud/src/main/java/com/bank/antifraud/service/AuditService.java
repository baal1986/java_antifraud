package com.bank.antifraud.service;


import com.bank.antifraud.entity.Audit;
import com.bank.antifraud.exception.service.AuditServiceException;
import com.bank.antifraud.repository.AuditRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;


@Service
public class AuditService {

    private final AuditRepository auditRepository;

    public AuditService(AuditRepository auditRepository) {
        this.auditRepository = auditRepository;
    }

    public  Optional<Audit> findById(String entityType, Long id) {
        Optional<Audit> result = Optional.empty();
        try {
            result = auditRepository.findById(entityType, id);
        } catch (EntityNotFoundException ignored) {
        }
        return result;
    }

    public void save(Audit audit) {
        auditRepository.save(audit);
    }

}
