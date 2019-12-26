package com.tmri.rfid.framework.util;
import java.nio.charset.Charset;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;
public class AES{
	public static final String KEY_ALGORITHM="AES";
	public static final String CIPHER_ALGORITHM="AES/ECB/PKCS5Padding";
	public static String initkey() throws Exception{
		KeyGenerator kg=KeyGenerator.getInstance(KEY_ALGORITHM);
		kg.init(256);
		SecretKey secretKey=kg.generateKey();
		return Base64.encodeBase64String(secretKey.getEncoded());
	}
	public static Key toKey(byte[] key) throws Exception{
		return new SecretKeySpec(key,KEY_ALGORITHM);
	}
	public static String encrypt(String data) throws Exception{
		Key k=toKey("helloworlddotcom".getBytes());
		Cipher cipher=Cipher.getInstance(CIPHER_ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE,k);
		return Base64.encodeBase64String(cipher.doFinal(data.getBytes()));
	}
	public static String decrypt(String data) throws Exception{
		Key k=toKey("helloworlddotcom".getBytes());
		Cipher cipher=Cipher.getInstance(CIPHER_ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE,k);
		String mydecode=new String(cipher.doFinal(Base64.decodeBase64(data)));
		if(!(Charset.forName("GBK").newEncoder().canEncode(mydecode))){
			mydecode=new String(cipher.doFinal(Base64.decodeBase64(data)),"GBK");
		}
		return mydecode;
	}
	public static String encrypt(String data,String key) throws Exception{
		Key k=toKey(key.getBytes());
		Cipher cipher=Cipher.getInstance(CIPHER_ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE,k);
		return Base64.encodeBase64String(cipher.doFinal(data.getBytes()));
	}
	public static String decrypt(String data,String key) throws Exception{
		Key k=toKey(key.getBytes());
		Cipher cipher=Cipher.getInstance(CIPHER_ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE,k);
		String mydecode=new String(cipher.doFinal(Base64.decodeBase64(data)));
		if(!(Charset.forName("GBK").newEncoder().canEncode(mydecode))){
			mydecode=new String(cipher.doFinal(Base64.decodeBase64(data)),"GBK");
		}
		return mydecode;
	}
	public static void main(String[] args){
		try{
			java.util.UUID uuid=java.util.UUID.randomUUID();
			System.out.println(uuid.toString());
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
