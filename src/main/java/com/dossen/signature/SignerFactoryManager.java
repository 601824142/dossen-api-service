package com.dossen.signature;


import com.dossen.util.HttpCommonUtil;

import java.util.HashMap;
import java.util.Map;


public class SignerFactoryManager {

    private static Map<String,ISignerFactory> factoryMap = new HashMap<String,ISignerFactory>(2);

    static {
        registerSignerFactory(HMacSHA256SignerFactory.METHOD, new HMacSHA256SignerFactory());
        registerSignerFactory(HMacSHA1SignerFactory.METHOD, new HMacSHA1SignerFactory());

    }

    public static ISignerFactory registerSignerFactory(String method,ISignerFactory factory) {

        if (HttpCommonUtil.isEmpty(method)) {
            throw new IllegalArgumentException("method can not be empty");
        }

        if (null == factory) {
            throw new IllegalArgumentException("factory can not be null");
        }

        return factoryMap.put(method, factory);
    }

    public static ISignerFactory findSignerFactory(String method) {

        if (HttpCommonUtil.isBlank(method)) {
            method = HMacSHA256SignerFactory.METHOD;
        }

        return factoryMap.get(method);
    }
}
