package com.dossen.signature;


import com.dossen.constant.SdkConstant;
import com.dossen.exception.SdkException;
import com.dossen.util.HttpCommonUtil;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class HMacSHA1SignerFactory implements ISignerFactory {

    public static final String METHOD = "HmacSHA1";

    private static ISinger singer = null;

    @Override
    public ISinger getSigner() throws SdkException {

        if (null == singer) {
            singer = new HMacSHA1Signer();
        }

        return singer;
    }

    private static class HMacSHA1Signer implements ISinger {
        @Override
        public String sign(String strToSign, String secretKey) throws NoSuchAlgorithmException, InvalidKeyException, SdkException {

            if (HttpCommonUtil.isEmpty(strToSign)) {
                throw new IllegalArgumentException("strToSign can not be empty");
            }

            if (HttpCommonUtil.isEmpty(secretKey)) {
                throw new IllegalArgumentException("secretKey can not be empty");
            }

            Mac hmacSha1 = Mac.getInstance(METHOD);
            byte[] keyBytes = secretKey.getBytes( SdkConstant.CLOUDAPI_ENCODING);
            hmacSha1.init(new SecretKeySpec(keyBytes, 0, keyBytes.length, METHOD));

            byte[] md5Result = hmacSha1.doFinal(strToSign.getBytes( SdkConstant.CLOUDAPI_ENCODING));
            return Base64.encodeBase64String(md5Result);
        }
    }


}
