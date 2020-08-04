package com.dossen.signature;


import com.dossen.exception.SdkException;
import com.dossen.util.HttpCommonUtil;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class HMacSHA256SignerFactory implements ISignerFactory {

    public static final String METHOD = "HmacSHA256";

    private static ISinger singer = null;

    @Override
    public ISinger getSigner() throws SdkException {

        if (null == singer) {
            singer = new HMacSHA256Signer();
        }

        return singer;
    }

    private static class HMacSHA256Signer implements ISinger {
        @Override
        public String sign(String strToSign, String secretKey) throws NoSuchAlgorithmException, InvalidKeyException, SdkException {

            if (HttpCommonUtil.isEmpty(strToSign)) {
                throw new IllegalArgumentException("strToSign can not be empty");
            }

            if (HttpCommonUtil.isEmpty(secretKey)) {
                throw new IllegalArgumentException("secretKey can not be empty");
            }

            Mac hmacSha256 = Mac.getInstance("HmacSHA256");
            byte[] keyBytes = secretKey.getBytes( StandardCharsets.UTF_8 );
            hmacSha256.init(new SecretKeySpec(keyBytes, 0, keyBytes.length, "HmacSHA256"));
            return new String( Base64.encodeBase64(hmacSha256.doFinal(strToSign.getBytes( StandardCharsets.UTF_8 )),false ));
        }
    }
}
