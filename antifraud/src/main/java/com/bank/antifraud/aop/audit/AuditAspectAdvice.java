package com.bank.antifraud.aop.audit;


import com.bank.antifraud.entity.Audit;
import com.bank.antifraud.entity.SuspiciousTransfer;
import com.bank.antifraud.service.AuditService;
import com.bank.antifraud.util.Operation;
import com.bank.antifraud.util.TypeOperation;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Objects;
import java.util.Optional;

@Component
@Aspect
public class AuditAspectAdvice {

    private final AuditService auditService;
    private final TypeOperation typeOperation;
    private final ObjectMapper objectMapper;

    public AuditAspectAdvice(AuditService auditService, TypeOperation typeOperation, ObjectMapper objectMapper) {
        this.auditService = auditService;
        this.typeOperation = typeOperation;
        this.objectMapper = objectMapper;
        objectMapper.enable(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES);
    }

    @Around("execution(* com.bank.antifraud.service.*Service.*(..))")
    public Object audit(ProceedingJoinPoint proceedingJoinPoint) {
        Object result = null;
        HttpServletRequest request = ((ServletRequestAttributes) Objects
                .requireNonNull(RequestContextHolder.getRequestAttributes()))
                .getRequest();
        typeOperation.toTypeOperation(request.getMethod());
        try {
            result = proceedingJoinPoint.proceed();
            var res = result.getClass().getName().split("\\.");
            String entityType = res[res.length - 1];
            Operation(typeOperation.getOperation(), result, entityType);
        } catch (Throwable ignored) {
        }
        return result;
    }

    void Operation(Operation operation, Object result, String res) throws JsonProcessingException {
        switch (operation) {
            case create -> create(result, res, "systemAntiFraud",
                    Timestamp.valueOf(OffsetDateTime.now()
                            .atZoneSameInstant(ZoneOffset.UTC)
                            .toLocalDateTime()));

            case update -> update(result, res, "systemAntiFraud",
                    Timestamp.valueOf(OffsetDateTime.now()
                            .atZoneSameInstant(ZoneOffset.UTC)
                            .toLocalDateTime()));

            case delete -> delete(result, res, "systemAntiFraud",
                    Timestamp.valueOf(OffsetDateTime.now()
                            .atZoneSameInstant(ZoneOffset.UTC)
                            .toLocalDateTime()));
        }
    }

    void create(Object result, String entityType, String user, Timestamp timestamp) throws JsonProcessingException {
        SuspiciousTransfer entity = (SuspiciousTransfer) result;
        Optional<Audit> resAudit = auditService.findById(entityType, entity.getId());
        if (resAudit.isPresent()) {
            auditService.save(Audit.builder()
                    .entityType(entityType)
                    .operationType(typeOperation.getOperation().toString())
                    .createdBy(user)
                    .createdAt(timestamp)
                    .entityJson(objectMapper.writeValueAsString(result))
                    .build()
            );
        }
    }

    void update(Object result, String entityType, String user, Timestamp timestamp) throws JsonProcessingException {
        SuspiciousTransfer entity = (SuspiciousTransfer) result;
        Optional<Audit> resAudit = auditService.findById(entityType, entity.getId());
        if (resAudit.isPresent()) {
            Audit audit = resAudit.get();
            auditService.save(Audit.builder()
                    .id(audit.getId())
                    .entityType(audit.getEntityType())
                    .operationType(typeOperation.getOperation().toString())
                    .createdBy(user)
                    .createdAt(audit.getCreatedAt())
                    .modifiedBy(user)
                    .modifiedAt(timestamp)
                    .newEntityJson(objectMapper.writeValueAsString(entity))
                    .entityJson(audit.getEntityJson())
                    .build()
            );
        }
    }

    void delete(Object result, String entityType, String user, Timestamp timestamp) {
        SuspiciousTransfer entity = (SuspiciousTransfer) result;
        Optional<Audit> resAudit = auditService.findById(entityType, entity.getId());
        if (resAudit.isPresent()) {
            Audit audit = resAudit.get();
            auditService.save(Audit.builder()
                    .id(audit.getId())
                    .entityType(audit.getEntityType())
                    .operationType(typeOperation.getOperation().toString())
                    .createdBy(user)
                    .createdAt(audit.getCreatedAt())
                    .modifiedBy(user)
                    .modifiedAt(timestamp)
                    .newEntityJson(null)
                    .entityJson(audit.getEntityJson())
                    .build()
            );
        }
    }


}
