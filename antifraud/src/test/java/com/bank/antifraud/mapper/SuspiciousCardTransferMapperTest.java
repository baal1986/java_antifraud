package com.bank.antifraud.mapper;

import com.bank.antifraud.dto.SuspiciousTransferDtoImpl;
import com.bank.antifraud.entity.SuspiciousCardTransfer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import paramResolver.SuspiciousCardTransferMapperParamResolver;

import static org.assertj.core.api.Assertions.assertThat;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith({SuspiciousCardTransferMapperParamResolver.class})
class SuspiciousCardTransferMapperTest extends
        SuspiciousTransferMapperTest<SuspiciousCardTransfer, SuspiciousTransferDtoImpl> {

    private final SuspiciousTransferMapper<SuspiciousCardTransfer, SuspiciousTransferDtoImpl> suspiciousTransferMapper;

    private SuspiciousCardTransfer suspiciousCardTransfer;
    private SuspiciousTransferDtoImpl dto;

    public SuspiciousCardTransferMapperTest(SuspiciousCardTransferMapperImpl suspiciousTransferMapper) {
        this.suspiciousTransferMapper = suspiciousTransferMapper;
    }

    @BeforeEach
    void setDto() {
        suspiciousCardTransfer = new SuspiciousCardTransfer();
        suspiciousCardTransfer.setId(1L);
        suspiciousCardTransfer.setCardTransferId(777L);
        suspiciousCardTransfer.setIsSuspicious(true);
        suspiciousCardTransfer.setSuspiciousReason("");
        suspiciousCardTransfer.setBlockedReason("");
        suspiciousCardTransfer.setIsBlocked(true);

        dto = new SuspiciousTransferDtoImpl(777L, true, true, "", "");
    }


    @Test
    void toDto() {
        assertThat(dto).usingRecursiveComparison()
                .isEqualTo(super.toDto(suspiciousCardTransfer, suspiciousTransferMapper));
    }

    @Test
    void fromDto() {
        var res = super.fromDto(dto, suspiciousTransferMapper);
        res.setId(1L);
        assertThat(suspiciousCardTransfer).usingRecursiveComparison()
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