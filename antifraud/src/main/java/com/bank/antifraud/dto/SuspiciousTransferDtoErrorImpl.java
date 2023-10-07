package com.bank.antifraud.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class SuspiciousTransferDtoErrorImpl extends SuspiciousTransferDtoImpl {
    protected String errorValue;
}
