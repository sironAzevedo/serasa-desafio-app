package com.serasa.desafio.utils;

import java.util.UUID;

public final class GenerateUtil {

    private GenerateUtil(){}

    public static final Long code(){
        String g = UUID.randomUUID().toString().replaceAll("-", "").replaceAll("[^0-9.]", "").substring(1, 5);
        return Long.valueOf(g);
    }
}
