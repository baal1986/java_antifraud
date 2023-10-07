package com.bank.antifraud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@MappedSuperclass
@Data
public class SuspiciousTransfer  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "is_blocked", nullable = false)
    @NotNull(message = "is_blocked can't be null")
    protected Boolean isBlocked;

    @Column(name = "is_suspicious", nullable = false)
    @NotNull(message = "is_suspicious can't be null")
    protected Boolean isSuspicious;

    @Column(name = "blocked_reason")
    protected String blockedReason;

    @Column(name = "suspicious_reason", nullable = false)
    @NotNull(message = "suspicious_reason can't be null")
    protected String suspiciousReason;
}
