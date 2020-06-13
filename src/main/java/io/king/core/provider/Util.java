package io.king.core.provider;

import lombok.Getter;

import java.io.IOException;
import java.io.InputStream;

public final class Util {

    @Getter(lazy = true)
    private final static Util instance = new Util();

    public String loadBytes(InputStream inputStream) throws IOException {
        byte[] bytes = new byte[1024];
        int streamByte;

        for (int i = 0; (streamByte = inputStream.read()) != -1; i++) {
            bytes[i] = (byte) streamByte;
        }

        return new String(bytes);
    }
}
