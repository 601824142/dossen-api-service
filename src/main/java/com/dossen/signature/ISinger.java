package com.dossen.signature;


import com.dossen.exception.SdkException;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public interface ISinger {
    String sign(String strToSign, String secretKey) throws NoSuchAlgorithmException, InvalidKeyException, SdkException;
}
