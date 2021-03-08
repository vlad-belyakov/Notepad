package view;

import java.util.Base64;

public class Convertor {
    // Encode data on your side using BASE64
    public String Encode(String base64) {
        byte[] bytesEncoded = Base64.getEncoder().encode(base64.getBytes());
        return new String(bytesEncoded);
    }

    // Decode data on other side, by processing encoded data
    public String Decode(String base64) {
        String value = "";
        try {
            byte[] valueDecoded = Base64.getDecoder().decode(base64.getBytes());
            value = new String(valueDecoded);
        } catch (Exception e) {
            //ignore
        }
        return value;
    }
}
