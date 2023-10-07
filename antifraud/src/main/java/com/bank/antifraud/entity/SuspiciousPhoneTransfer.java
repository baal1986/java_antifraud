package com.bank.antifraud.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "suspicious_phone_transfers", schema = "anti_fraud")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class SuspiciousPhoneTransfer extends SuspiciousTransfer {

    @Column(name = "phone_transfer_id", unique = true, nullable = false)
    @NotNull(message = "phone_transfer_id can't be null")
    private Long phoneTransferId;


}
