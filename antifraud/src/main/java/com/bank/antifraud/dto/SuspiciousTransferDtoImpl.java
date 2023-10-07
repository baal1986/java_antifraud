package com.bank.antifraud.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Component
@ToString
public class SuspiciousTransferDtoImpl extends SuspiciousTransferDto{
    private Long transferId;
    private Boolean isBlocked;
    private Boolean isSuspicious;
    private String blockedReason;
    private String suspiciousReason;

}
