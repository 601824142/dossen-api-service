package com.dossen.signature;


import com.dossen.exception.SdkException;

public interface ISignerFactory {
    ISinger getSigner() throws SdkException;
}
