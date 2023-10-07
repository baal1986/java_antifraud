package com.bank.antifraud.aop.audit;

import com.bank.antifraud.entity.Audit;
import com.bank.antifraud.entity.SuspiciousCardTransfer;
import com.bank.antifraud.entity.SuspiciousTransfer;
import com.bank.antifraud.repository.AuditRepository;
import com.bank.antifraud.service.AuditService;
import com.bank.antifraud.util.Operation;
import com.bank.antifraud.util.TypeOperation;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;


import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

import static org.assertj.core.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@ExtendWith({MockitoExtension.class})
@MockitoSettings(strictness = Strictness.LENIENT)
class AuditAspectAdviceTest {


    @Mock
    private AuditRepository auditRepository;
    @Mock
    ObjectMapper objectMapper;
    @Mock
    TypeOperation typeOperation;

    @Mock
    SuspiciousTransfer suspiciousTransfer;

    @Mock
    ProceedingJoinPoint proceedingJoinPoint;

    @Mock
    private AuditService auditService;

    @Spy
    @InjectMocks
    private AuditAspectAdvice auditAspectAdvice;

    @Test
    void auditShouldBeOperationCreateMustBePerformed() throws JsonProcessingException {

        SuspiciousCardTransfer result = new SuspiciousCardTransfer();
        result.setId(1L);
        result.setCardTransferId(777L);
        result.setBlockedReason("11");
        result.setSuspiciousReason("222");
        result.setIsBlocked(true);
        result.setIsSuspicious(false);
        String entityType = "SuspiciousCardTransfer";
        Optional<Audit> audit = Optional.of(new Audit());
        doReturn(audit).when(auditRepository).findById(any(), any());
        auditAspectAdvice.Operation(Operation.create, result, entityType);
        auditRepository.findById(any(), any());
        verify(auditAspectAdvice, times(1)).create(any(), any(), any(), any());
    }

    @Test
    void auditShouldBeOperationUpdateMustBePerformed() throws JsonProcessingException {

        SuspiciousCardTransfer result = new SuspiciousCardTransfer();
        result.setId(1L);
        result.setCardTransferId(777L);
        result.setBlockedReason("11");
        result.setSuspiciousReason("222");
        result.setIsBlocked(true);
        result.setIsSuspicious(false);
        String entityType = "SuspiciousCardTransfer";
        Optional<Audit> audit = Optional.of(new Audit());
        doReturn(audit).when(auditRepository).findById(any(), any());
        auditAspectAdvice.Operation(Operation.update, result, entityType);
        auditRepository.findById(any(), any());
        verify(auditAspectAdvice, times(1)).update(any(), any(), any(), any());
    }

    @Test
    void auditShouldBeOperationDeleteMustBePerformed() throws JsonProcessingException {

        SuspiciousCardTransfer result = new SuspiciousCardTransfer();
        result.setId(1L);
        result.setCardTransferId(777L);
        result.setBlockedReason("11");
        result.setSuspiciousReason("222");
        result.setIsBlocked(true);
        result.setIsSuspicious(false);
        String entityType = "SuspiciousCardTransfer";
        Optional<Audit> audit = Optional.of(new Audit());
        doReturn(audit).when(auditRepository).findById(any(), any());
        auditAspectAdvice.Operation(Operation.delete, result, entityType);
        auditRepository.findById(any(), any());
        verify(auditAspectAdvice, times(1)).delete(any(), any(), any(), any());
    }

    @Test
    void auditShouldThrowException() throws Throwable {
        doThrow(new Throwable()).when(proceedingJoinPoint).proceed();
        try {
            auditAspectAdvice.audit(proceedingJoinPoint);
            fail("-");
        } catch (Throwable ignore) {
        }
    }

    @Test
    void createShouldBeCallCreateMethodAndSaveEntity() throws JsonProcessingException {
        //Audit auditObject = mock(Audit.class);
        doReturn(Optional.of(new Audit())).when(auditService).findById("type", 1L);
        doReturn(Operation.create).when(typeOperation).getOperation();
        doThrow(new NullPointerException()).when(auditService).save(any(Audit.class));

        String entityType = "type";
        String user = "systemAntiFraud";
        Timestamp timestamp = Timestamp
                .valueOf(OffsetDateTime.now()
                        .atZoneSameInstant(ZoneOffset.UTC)
                        .toLocalDateTime());

        SuspiciousTransfer transfer = new SuspiciousTransfer();
        transfer.setId(1L);

        try {
            auditAspectAdvice.create(transfer, entityType, user, timestamp);
            fail("-");
        } catch (NullPointerException ignore) {

        }

        verify(auditService, atLeastOnce()).save(any(Audit.class));
        verify(auditService, atLeastOnce()).findById(any(), any());

    }

    @Test
    void createShouldBeCallCreateMethodAndNoSaveEntity() throws JsonProcessingException {
        doReturn(null).when(auditService).findById("type", 1L);
        doReturn(Operation.create).when(typeOperation).getOperation();
        doNothing().when(auditService).save(any(Audit.class));

        String entityType = "type";
        String user = "systemAntiFraud";
        Timestamp timestamp = Timestamp
                .valueOf(OffsetDateTime.now()
                        .atZoneSameInstant(ZoneOffset.UTC)
                        .toLocalDateTime());

        SuspiciousTransfer transfer = new SuspiciousTransfer();
        transfer.setId(1L);

        try {
            auditAspectAdvice.create(transfer, entityType, user, timestamp);
            fail("-");
        } catch (NullPointerException ignore) {

        }

        verify(auditService, times(0)).save(any(Audit.class));
        verify(auditService, atLeastOnce()).findById(any(), any());

    }

    @Test
    void updateShouldBeCallCreateMethodAndSaveEntity() throws JsonProcessingException {

        doReturn(Optional.of(new Audit())).when(auditService).findById("type", 1L);
        doReturn(Operation.create).when(typeOperation).getOperation();
        doThrow(new NullPointerException()).when(auditService).save(any(Audit.class));

        String entityType = "type";
        String user = "systemAntiFraud";
        Timestamp timestamp = Timestamp
                .valueOf(OffsetDateTime.now()
                        .atZoneSameInstant(ZoneOffset.UTC)
                        .toLocalDateTime());

        SuspiciousTransfer transfer = new SuspiciousTransfer();
        transfer.setId(1L);

        try {
            auditAspectAdvice.update(transfer, entityType, user, timestamp);
            fail("-");
        } catch (NullPointerException ignore) {

        }

        verify(auditService, atLeastOnce()).save(any(Audit.class));
        verify(auditService, atLeastOnce()).findById(any(), any());

    }

    @Test
    void updateShouldBeCallCreateMethodAndNoSaveEntity() throws JsonProcessingException {
        doReturn(null).when(auditService).findById("type", 1L);
        doReturn(Operation.create).when(typeOperation).getOperation();
        doNothing().when(auditService).save(any(Audit.class));

        String entityType = "type";
        String user = "systemAntiFraud";
        Timestamp timestamp = Timestamp
                .valueOf(OffsetDateTime.now()
                        .atZoneSameInstant(ZoneOffset.UTC)
                        .toLocalDateTime());

        SuspiciousTransfer transfer = new SuspiciousTransfer();
        transfer.setId(1L);

        try {
            auditAspectAdvice.update(transfer, entityType, user, timestamp);
            fail("-");
        } catch (NullPointerException ignore) {

        }

        verify(auditService, times(0)).save(any(Audit.class));
        verify(auditService, atLeastOnce()).findById(any(), any());

    }

    @Test
    void deleteShouldBeCallCreateMethodAndSaveEntity() {

        doReturn(Optional.of(new Audit())).when(auditService).findById("type", 1L);
        doReturn(Operation.create).when(typeOperation).getOperation();
        doThrow(new NullPointerException()).when(auditService).save(any(Audit.class));

        String entityType = "type";
        String user = "systemAntiFraud";
        Timestamp timestamp = Timestamp
                .valueOf(OffsetDateTime.now()
                        .atZoneSameInstant(ZoneOffset.UTC)
                        .toLocalDateTime());

        SuspiciousTransfer transfer = new SuspiciousTransfer();
        transfer.setId(1L);

        try {
            auditAspectAdvice.delete(transfer, entityType, user, timestamp);
            fail("-");
        } catch (NullPointerException ignore) {

        }

        verify(auditService, atLeastOnce()).save(any(Audit.class));
        verify(auditService, atLeastOnce()).findById(any(), any());

    }

    @Test
    void deleteShouldBeCallCreateMethodAndNoSaveEntity() {
        doReturn(null).when(auditService).findById("type", 1L);
        doReturn(Operation.create).when(typeOperation).getOperation();
        doNothing().when(auditService).save(any(Audit.class));

        String entityType = "type";
        String user = "systemAntiFraud";
        Timestamp timestamp = Timestamp
                .valueOf(OffsetDateTime.now()
                        .atZoneSameInstant(ZoneOffset.UTC)
                        .toLocalDateTime());

        SuspiciousTransfer transfer = new SuspiciousTransfer();
        transfer.setId(1L);

        try {
            auditAspectAdvice.delete(transfer, entityType, user, timestamp);
            fail("-");
        } catch (NullPointerException ignore) {

        }

        verify(auditService, times(0)).save(any(Audit.class));
        verify(auditService, atLeastOnce()).findById(any(), any());

    }

}

