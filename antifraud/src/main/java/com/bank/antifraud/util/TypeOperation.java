package com.bank.antifraud.util;


import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@Getter
public class TypeOperation {
    private Operation operation;


    public void toTypeOperation(String type) {
        operation = type.equals("POST") ? Operation.create :
                type.equals("PUT") ? Operation.update :
                        type.equals("DELETE") ? Operation.delete : Operation.non;
    }

}
