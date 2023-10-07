package com.bank.antifraud.util;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import paramResolver.TypeOperationParamResolver;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith({TypeOperationParamResolver.class})
class TypeOperationTest {

    private final TypeOperation typeOperation;

    public TypeOperationTest(TypeOperation typeOperation) {
        this.typeOperation = typeOperation;
    }

    @Test
    public void toTypeOperationTest() {

        assertAll(
                () -> {
                    typeOperation.toTypeOperation("POST");
                    assertThat(typeOperation.getOperation()).isEqualTo(Operation.create);
                },

                () -> {
                    typeOperation.toTypeOperation("PUT");
                    assertThat(typeOperation.getOperation()).isEqualTo(Operation.update);
                },
                () -> {
                    typeOperation.toTypeOperation("DELETE");
                    assertThat(typeOperation.getOperation()).isEqualTo(Operation.delete);
                },
                () -> {
                    typeOperation.toTypeOperation("");
                    assertThat(typeOperation.getOperation()).isEqualTo(Operation.non);
                },
                () -> {
                    typeOperation.toTypeOperation("GET");
                    assertThat(typeOperation.getOperation()).isEqualTo(Operation.non);
                }
        );

    }
}