package com.bank.antifraud.mapper;

import com.bank.antifraud.dto.SuspiciousTransferDtoImpl;
import com.bank.antifraud.entity.SuspiciousAccountTransfer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import paramResolver.SuspiciousAccountTransferMapperParamResolver;



import static org.assertj.core.api.Assertions.assertThat;



@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith({SuspiciousAccountTransferMapperParamResolver.class})
class SuspiciousAccountTransferMapperTest extends
        SuspiciousTransferMapperTest<SuspiciousAccountTransfer, SuspiciousTransferDtoImpl> {

    private final SuspiciousTransferMapper<SuspiciousAccountTransfer, SuspiciousTransferDtoImpl> suspiciousTransferMapper;

    private SuspiciousAccountTransfer suspiciousAccountTransfer;
    private SuspiciousTransferDtoImpl dto;

    public SuspiciousAccountTransferMapperTest(SuspiciousAccountTransferMapperImpl suspiciousTransferMapper) {
        this.suspiciousTransferMapper = suspiciousTransferMapper;
    }

    @BeforeEach
    void setDto() {
        suspiciousAccountTransfer = new SuspiciousAccountTransfer();
        suspiciousAccountTransfer.setId(1L);
        suspiciousAccountTransfer.setAccountTransferId(777L);
        suspiciousAccountTransfer.setIsSuspicious(true);
        suspiciousAccountTransfer.setSuspiciousReason("");
        suspiciousAccountTransfer.setBlockedReason("");
        suspiciousAccountTransfer.setIsBlocked(true);

        dto = new SuspiciousTransferDtoImpl(777L, true, true, "", "");
    }


    @Test
    void toDto() {
        assertThat(dto).usingRecursiveComparison()
                .isEqualTo(super.toDto(suspiciousAccountTransfer, suspiciousTransferMapper));
    }

    @Test
    void fromDto() {
        var res = super.fromDto(dto, suspiciousTransferMapper);
        res.setId(1L);
        assertThat(suspiciousAccountTransfer).usingRecursiveComparison()
                .isEqualTo(res);
    }

    @Test
    void toDtoShouldReturnNullIfNull(){
        assertThat(suspiciousTransferMapper.toDto(null)).isEqualTo(null);
    }

    @Test
    void fromDtoShouldReturnNullIfNull(){
        assertThat(suspiciousTransferMapper.fromDto(null)).isEqualTo(null);
    }
}