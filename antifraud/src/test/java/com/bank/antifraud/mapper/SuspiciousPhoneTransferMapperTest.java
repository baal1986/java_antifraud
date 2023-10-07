package com.bank.antifraud.mapper;

import com.bank.antifraud.dto.SuspiciousTransferDtoImpl;
import com.bank.antifraud.entity.SuspiciousPhoneTransfer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import paramResolver.SuspiciousPhoneTransferMapperParamResolver;

import static org.assertj.core.api.Assertions.assertThat;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith({SuspiciousPhoneTransferMapperParamResolver.class})
class SuspiciousPhoneTransferMapperTest extends
        SuspiciousTransferMapperTest<SuspiciousPhoneTransfer, SuspiciousTransferDtoImpl> {

    private final SuspiciousTransferMapper<SuspiciousPhoneTransfer, SuspiciousTransferDtoImpl> suspiciousTransferMapper;

    private SuspiciousPhoneTransfer suspiciousPhoneTransfer;
    private SuspiciousTransferDtoImpl dto;

    public SuspiciousPhoneTransferMapperTest(SuspiciousPhoneTransferMapperImpl suspiciousTransferMapper) {
        this.suspiciousTransferMapper = suspiciousTransferMapper;
    }

    @BeforeEach
    void setDto() {
        suspiciousPhoneTransfer = new SuspiciousPhoneTransfer();
        suspiciousPhoneTransfer.setId(1L);
        suspiciousPhoneTransfer.setPhoneTransferId(777L);
        suspiciousPhoneTransfer.setIsSuspicious(true);
        suspiciousPhoneTransfer.setSuspiciousReason("");
        suspiciousPhoneTransfer.setBlockedReason("");
        suspiciousPhoneTransfer.setIsBlocked(true);

        dto = new SuspiciousTransferDtoImpl(777L, true, true, "", "");
    }


    @Test
    void toDto() {
        assertThat(dto).usingRecursiveComparison()
                .isEqualTo(super.toDto(suspiciousPhoneTransfer, suspiciousTransferMapper));
    }

    @Test
    void fromDto() {
        var res = super.fromDto(dto, suspiciousTransferMapper);
        res.setId(1L);
        assertThat(suspiciousPhoneTransfer).usingRecursiveComparison()
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