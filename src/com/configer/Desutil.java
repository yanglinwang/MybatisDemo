package com.configer;


import java.security.Key;

import javax.crypto.Cipher;

/**
 * DES���ܽ��ܹ�����
 * @Copyright Copyright (c) 2015 Asiainfo.com
 * 
 * @ClassName Desutil.class
 * @Description DES���ܽ��ܹ�����
 *                 1. encryption ����; 
 *                 2. decryption ���� cipher����
 *
 * @version v1.0.0
 * @author zengwei
 * @date 2015-1-20 11:21:36 AM
 * <br>
 * <strong>Modification History:</strong><br>
 * Date         Author          Version            Description<br>
 * ---------------------------------------------------------*<br>
 * 2015-1-20     zengwei           v1.0.0               UPDATE <br>
 */
public class Desutil {
	
    private static String strDefaultKey = "asiainfo";
    
    private Cipher encryptCipher = null;
    
    private Cipher decryptCipher = null;

    /**
     * ��byte����ת��Ϊ��ʾ16����ֵ���ַ���,
     * ��: byte[]{8, 18}ת��Ϊ: 0813, ��public static byte[] hexStr2ByteArr(String strIn)
     * ��Ϊ�����ת������
     * @Function byteArr2HexStr
     * @Description ��byte����ת��Ϊ��ʾ16����ֵ���ַ���,
     *                ��: byte[]{8, 18}ת��Ϊ: 0813, ��public static byte[] hexStr2ByteArr(String strIn),
     *                ��Ϊ�����ת������
     *
     * @param arrB | ��Ҫת����byte����
     * @return java.lang.String | ת������ַ���
     * @throws Exception | �������������κ��쳣, �����쳣ȫ���׳�
     *
     * @version v1.0.0
     * @author zengwei
     * @date 2015-1-20 11:23:13 AM
     * <br>
     * <strong>Modification History:</strong><br>
     * Date         Author          Version            Description<br>
     * ---------------------------------------------------------<br>
     * 2015-1-20     zengwei           v1.0.0               UPDATE <br>
     */
    public static String byteArr2HexStr(byte[] arrB)
        throws Exception {
        int iLen = arrB.length;
        // ÿ��byte�������ַ����ܱ�ʾ, �����ַ����ĳ��������鳤�ȵ�����
        StringBuffer sb = new StringBuffer(iLen * 2);
        for (int i = 0; i < iLen; i++) {
            int intTmp = arrB[i];
            // �Ѹ���ת��Ϊ����
            while (intTmp < 0) {
                intTmp = intTmp + 256;
            }
            // С��0F������Ҫ��ǰ�油0
            if (intTmp < 16) {
                sb.append("0");
            }
            sb.append(Integer.toString(intTmp, 16));
        }
        return sb.toString();
    }

    /**
     * ����ʾ16����ֵ���ַ���ת��Ϊbyte����, ��public static String byteArr2HexStr(byte[] arrB)
     * ��Ϊ�����ת������
     * @Function hexStr2ByteArr
     * @Description ����ʾ16����ֵ���ַ���ת��Ϊbyte����, ��public static String byteArr2HexStr(byte[] arrB)
     *                ��Ϊ�����ת������
     *
     * @param strIn | ��Ҫת�����ַ���
     * @return | ת�����byte����
     * @throws Exception | �������������κ��쳣�������쳣ȫ���׳�
     *
     * @version v1.0.0
     * @author zengwei
     * @date 2015-1-20 11:30:01 AM
     * <br>
     * <strong>Modification History:</strong><br>
     * Date         Author          Version            Description<br>
     * ---------------------------------------------------------<br>
     * 2015-1-20     zengwei           v1.0.0               UPDATE <br>
     */
    public static byte[] hexStr2ByteArr(String strIn)
        throws Exception {
        byte[] arrB = strIn.getBytes();
        int iLen = arrB.length;

        // �����ַ���ʾһ���ֽ�, �����ֽ����鳤�����ַ������ȳ���2
        byte[] arrOut = new byte[iLen / 2];
        for (int i = 0; i < iLen; i = i + 2) {
            String strTmp = new String(arrB, i, 2);
            arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
        }
        return arrOut;
    }

    /**
     * Ĭ�Ϲ��췽����ʹ��Ĭ����Կ
     * @throws Exception
     */
    public Desutil() throws Exception {
        this(strDefaultKey);
    }

    /**
     * ָ����Կ���췽��
     * @param strKey ָ������Կ
     * @throws Exception
     */
    public Desutil(String strKey)
        throws Exception {
    	// ���ﲻ���ṩ���ܽ����ṩ��, DES�㷨JDK�ڲ��������ṩ��, ֱ�ӵ��þͺ���
        // Security.addProvider(new com.sun.crypto.provider.SunJCE());
        Key key = getKey(strKey.getBytes());

        encryptCipher = Cipher.getInstance("DES");
        encryptCipher.init(Cipher.ENCRYPT_MODE, key);

        decryptCipher = Cipher.getInstance("DES");
        decryptCipher.init(Cipher.DECRYPT_MODE, key);
    }

    /**
     * �����ֽ�����
     * @param arrB ����ܵ��ֽ�����
     * @return ���ܺ���ֽ�����
     * @throws Exception
     */
    public byte[] encrypt(byte[] arrB)
        throws Exception {
        return encryptCipher.doFinal(arrB);
    }

    /**
     * �����ַ���
     * @param strIn ����ܵ��ַ���
     * @return ���ܺ���ַ���
     * @throws Exception
     */
    public String encrypt(String strIn)
        throws Exception {
        return byteArr2HexStr(encrypt(strIn.getBytes()));
    }

    /**
     * �����ֽ�����
     * @param arrB ����ܵ��ֽ�����
     * @return ���ܺ���ֽ�����
     * @throws Exception
     */
    public byte[] decrypt(byte[] arrB)
        throws Exception {
        return decryptCipher.doFinal(arrB);
    }

    /**
     * �����ַ���
     * @param strIn ����ܵ��ַ���
     * @return ���ܺ���ַ���
     * @throws Exception
     */
    public String decrypt(String strIn)
        throws Exception {
        return new String(decrypt(hexStr2ByteArr(strIn)));
    }

    /**
     * ��ָ���ַ���������Կ����Կ������ֽ����鳤��Ϊ8λ
     * ����8λʱ���油0������8λֻȡǰ8λ
     * @Function getKey
     * @Description ��ָ���ַ���������Կ����Կ������ֽ����鳤��Ϊ8λ
     *                ����8λʱ���油0������8λֻȡǰ8λ
     *
     * @param arrBTmp | ���ɸ��ַ������ֽ�����
     * @return | ���ɵ���Կ�Ķ���
     * @throws Exception
     *
     * @version v1.0.0
     * @author zengwei
     * @date 2015-1-20 11:28:30 AM
     * <br>
     * <strong>Modification History:</strong><br>
     * Date         Author          Version            Description<br>
     * ---------------------------------------------------------<br>
     * 2015-1-20     zengwei           v1.0.0               UPDATE <br>
     */
    private Key getKey(byte[] arrBTmp)
        throws Exception {
        // ����һ���յ�8λ�ֽ����飨Ĭ��ֵΪ0��
        byte[] arrB = new byte[8];
        // ��ԭʼ�ֽ�����ת��Ϊ8λ
        for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
            arrB[i] = arrBTmp[i];
        }
        // ������Կ
        Key key = new javax.crypto.spec.SecretKeySpec(arrB, "DES");
        return key;
    }

    /**
     * ��Ԫ���Է���
     * @param args
     */
    public static void main(String[] args) {
		String strOriginal = "123456";
		String strOp = "-de";
		// �����θ���
		if (args.length == 2) {
			strOp = args[0];
			strOriginal = args[1];
		} else {
			System.out.println("Wrong Parameter count , " +
					"try use \"java Desutil -de|-en  'the string you want to be Encrypted'\"");
			System.out.println("Now do Encrypt with \"1111\"");
			try {
				Desutil des = new Desutil();
				// ���ܲ���
				System.out.println("*****  ���ܲ��� *****");
				String strEncrypt = des.enTest(strOriginal);
				// ���ܲ���
				System.out.println("*****  ���ܲ��� *****");
				des.deTest(strEncrypt);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return;
		}
		try {
			if (strOp.equals("-de")) {
				Desutil des = new Desutil();
				des.deTest(strOriginal);
			} else if (strOp.equals("-en")) {
				Desutil des = new Desutil();
				des.enTest(strOriginal);
			} else {
				System.out.println("Wrong operater , try use \"java DES -de|-en  'the string you want to be Encrypted'\"");
				System.out.println("Now do Encrypt with \"1111\"");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

    /**
     * ��Ԫ���Է�������ӡ��ָ���ַ������ܺ���ַ���
     */
    private String enTest(String strOriginal) {
        try {
            System.out.println("Plain   String: " + strOriginal);
            
            String strEncrypt= encrypt(strOriginal);
            
            System.out.println("Encrypted String: " + strEncrypt);
            return strEncrypt;
        } catch (Exception ex) {
            ex.printStackTrace();
            return "null";
        }
    }

    /**
     * ��Ԫ���Է�������ӡ��ָ���ַ������ܺ���ַ���
     */
    private void deTest(String strOriginal) {
        try {
            System.out.println("Encrypted String: " + strOriginal);
            System.out.println("Encrypted String length =  " + strOriginal.length());
            
            String strPlain = decrypt(strOriginal);
            
            System.out.println("Plain  String: " + strPlain);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}

