package com.crusoe.relaxedintegration.util;


public final class Base64 {

    static private final int     BASELENGTH           = 128;
    static private final int     LOOKUPLENGTH         = 64;
    static private final int     TWENTYFOURBITGROUP   = 24;
    static private final int     EIGHTBIT             = 8;
    static private final int     SIXTEENBIT           = 16;
    static private final int     FOURBYTE             = 4;
    static private final int     SIGN                 = -128;
    static private final char    PAD                  = '=';
    static private final boolean fDebug               = false;
    static final private byte[]  base64Alphabet       = new byte[BASELENGTH];
    static final private char[]  lookUpBase64Alphabet = new char[LOOKUPLENGTH];
  
    static {
        for (int i = 0; i < BASELENGTH; ++i) {
            base64Alphabet[i] = -1;
        }
        for (int i = 'Z'; i >= 'A'; i--) {
            base64Alphabet[i] = (byte) (i - 'A');
        }
        for (int i = 'z'; i >= 'a'; i--) {
            base64Alphabet[i] = (byte) (i - 'a' + 26);
        }

        for (int i = '9'; i >= '0'; i--) {
            base64Alphabet[i] = (byte) (i - '0' + 52);
        }

        base64Alphabet['+'] = 62;
        base64Alphabet['/'] = 63;

        for (int i = 0; i <= 25; i++) {
            lookUpBase64Alphabet[i] = (char) ('A' + i);
        }

        for (int i = 26, j = 0; i <= 51; i++, j++) {
            lookUpBase64Alphabet[i] = (char) ('a' + j);
        }

        for (int i = 52, j = 0; i <= 61; i++, j++) {
            lookUpBase64Alphabet[i] = (char) ('0' + j);
        }
        lookUpBase64Alphabet[62] = (char) '+';
        lookUpBase64Alphabet[63] = (char) '/';

    }

    private static boolean isWhiteSpace(char octect) {
        return (octect == 0x20 || octect == 0xd || octect == 0xa || octect == 0x9);
    }

    private static boolean isPad(char octect) {
        return (octect == PAD);
    }

    private static boolean isData(char octect) {
        return (octect < BASELENGTH && base64Alphabet[octect] != -1);
    }

    /**
     * Encodes hex octects into Base64
     *
     * @param binaryData Array containing binaryData
     * @return Encoded Base64 array
     */
    public static String encode(byte[] binaryData) {

        if (binaryData == null) {
            return null;
        }

        int lengthDataBits = binaryData.length * EIGHTBIT;
        if (lengthDataBits == 0) {
            return "";
        }

        int fewerThan24bits = lengthDataBits % TWENTYFOURBITGROUP;
        int numberTriplets = lengthDataBits / TWENTYFOURBITGROUP;
        int numberQuartet = fewerThan24bits != 0 ? numberTriplets + 1 : numberTriplets;
        char encodedData[] = null;

        encodedData = new char[numberQuartet * 4];

        byte k = 0, l = 0, b1 = 0, b2 = 0, b3 = 0;

        int encodedIndex = 0;
        int dataIndex = 0;
        if (fDebug) {
            System.out.println("number of triplets = " + numberTriplets);
        }

        for (int i = 0; i < numberTriplets; i++) {
            b1 = binaryData[dataIndex++];
            b2 = binaryData[dataIndex++];
            b3 = binaryData[dataIndex++];

            if (fDebug) {
                System.out.println("b1= " + b1 + ", b2= " + b2 + ", b3= " + b3);
            }

            l = (byte) (b2 & 0x0f);
            k = (byte) (b1 & 0x03);

            byte val1 = ((b1 & SIGN) == 0) ? (byte) (b1 >> 2) : (byte) ((b1) >> 2 ^ 0xc0);
            byte val2 = ((b2 & SIGN) == 0) ? (byte) (b2 >> 4) : (byte) ((b2) >> 4 ^ 0xf0);
            byte val3 = ((b3 & SIGN) == 0) ? (byte) (b3 >> 6) : (byte) ((b3) >> 6 ^ 0xfc);

            if (fDebug) {
                System.out.println("val2 = " + val2);
                System.out.println("k4   = " + (k << 4));
                System.out.println("vak  = " + (val2 | (k << 4)));
            }

            encodedData[encodedIndex++] = lookUpBase64Alphabet[val1];
            encodedData[encodedIndex++] = lookUpBase64Alphabet[val2 | (k << 4)];
            encodedData[encodedIndex++] = lookUpBase64Alphabet[(l << 2) | val3];
            encodedData[encodedIndex++] = lookUpBase64Alphabet[b3 & 0x3f];
        }

        // form integral number of 6-bit groups
        if (fewerThan24bits == EIGHTBIT) {
            b1 = binaryData[dataIndex];
            k = (byte) (b1 & 0x03);
            if (fDebug) {
                System.out.println("b1=" + b1);
                System.out.println("b1<<2 = " + (b1 >> 2));
            }
            byte val1 = ((b1 & SIGN) == 0) ? (byte) (b1 >> 2) : (byte) ((b1) >> 2 ^ 0xc0);
            encodedData[encodedIndex++] = lookUpBase64Alphabet[val1];
            encodedData[encodedIndex++] = lookUpBase64Alphabet[k << 4];
            encodedData[encodedIndex++] = PAD;
            encodedData[encodedIndex++] = PAD;
        } else if (fewerThan24bits == SIXTEENBIT) {
            b1 = binaryData[dataIndex];
            b2 = binaryData[dataIndex + 1];
            l = (byte) (b2 & 0x0f);
            k = (byte) (b1 & 0x03);

            byte val1 = ((b1 & SIGN) == 0) ? (byte) (b1 >> 2) : (byte) ((b1) >> 2 ^ 0xc0);
            byte val2 = ((b2 & SIGN) == 0) ? (byte) (b2 >> 4) : (byte) ((b2) >> 4 ^ 0xf0);

            encodedData[encodedIndex++] = lookUpBase64Alphabet[val1];
            encodedData[encodedIndex++] = lookUpBase64Alphabet[val2 | (k << 4)];
            encodedData[encodedIndex++] = lookUpBase64Alphabet[l << 2];
            encodedData[encodedIndex++] = PAD;
        }

        return new String(encodedData);
    }

    /**
     * Decodes Base64 data into octects
     *
     * @param encoded string containing Base64 data
     * @return Array containind decoded data.
     */
    public static byte[] decode(String encoded) {

        if (encoded == null) {
            return null;
        }

        char[] base64Data = encoded.toCharArray();
        // remove white spaces
        int len = removeWhiteSpace(base64Data);

        if (len % FOURBYTE != 0) {
            return null;//should be divisible by four
        }

        int numberQuadruple = (len / FOURBYTE);

        if (numberQuadruple == 0) {
            return new byte[0];
        }

        byte decodedData[] = null;
        byte b1 = 0, b2 = 0, b3 = 0, b4 = 0;
        char d1 = 0, d2 = 0, d3 = 0, d4 = 0;

        int i = 0;
        int encodedIndex = 0;
        int dataIndex = 0;
        decodedData = new byte[(numberQuadruple) * 3];

        for (; i < numberQuadruple - 1; i++) {

            if (!isData((d1 = base64Data[dataIndex++])) || !isData((d2 = base64Data[dataIndex++]))
                || !isData((d3 = base64Data[dataIndex++]))
                || !isData((d4 = base64Data[dataIndex++]))) {
                return null;
            }//if found "no data" just return null

            b1 = base64Alphabet[d1];
            b2 = base64Alphabet[d2];
            b3 = base64Alphabet[d3];
            b4 = base64Alphabet[d4];

            decodedData[encodedIndex++] = (byte) (b1 << 2 | b2 >> 4);
            decodedData[encodedIndex++] = (byte) (((b2 & 0xf) << 4) | ((b3 >> 2) & 0xf));
            decodedData[encodedIndex++] = (byte) (b3 << 6 | b4);
        }

        if (!isData((d1 = base64Data[dataIndex++])) || !isData((d2 = base64Data[dataIndex++]))) {
            return null;//if found "no data" just return null
        }

        b1 = base64Alphabet[d1];
        b2 = base64Alphabet[d2];

        d3 = base64Data[dataIndex++];
        d4 = base64Data[dataIndex++];
        if (!isData((d3)) || !isData((d4))) {//Check if they are PAD characters
            if (isPad(d3) && isPad(d4)) {
                if ((b2 & 0xf) != 0)//last 4 bits should be zero
                {
                    return null;
                }
                byte[] tmp = new byte[i * 3 + 1];
                System.arraycopy(decodedData, 0, tmp, 0, i * 3);
                tmp[encodedIndex] = (byte) (b1 << 2 | b2 >> 4);
                return tmp;
            } else if (!isPad(d3) && isPad(d4)) {
                b3 = base64Alphabet[d3];
                if ((b3 & 0x3) != 0)//last 2 bits should be zero
                {
                    return null;
                }
                byte[] tmp = new byte[i * 3 + 2];
                System.arraycopy(decodedData, 0, tmp, 0, i * 3);
                tmp[encodedIndex++] = (byte) (b1 << 2 | b2 >> 4);
                tmp[encodedIndex] = (byte) (((b2 & 0xf) << 4) | ((b3 >> 2) & 0xf));
                return tmp;
            } else {
                return null;
            }
        } else { //No PAD e.g 3cQl
            b3 = base64Alphabet[d3];
            b4 = base64Alphabet[d4];
            decodedData[encodedIndex++] = (byte) (b1 << 2 | b2 >> 4);
            decodedData[encodedIndex++] = (byte) (((b2 & 0xf) << 4) | ((b3 >> 2) & 0xf));
            decodedData[encodedIndex++] = (byte) (b3 << 6 | b4);

        }

        return decodedData;
    }

    /**
     * remove WhiteSpace from MIME containing encoded Base64 data.
     *
     * @param data  the byte array of base64 data (with WS)
     * @return      the new length
     */
    private static int removeWhiteSpace(char[] data) {
        if (data == null) {
            return 0;
        }

        // count characters that's not whitespace
        int newSize = 0;
        int len = data.length;
        for (int i = 0; i < len; i++) {
            if (!isWhiteSpace(data[i])) {
                data[newSize++] = data[i];
            }
        }
        return newSize;
    }
    
    /**
     * 将暗码转为明码，如果不能转则返回secretCode
     * @param secretCode
     * @return
     */
    public static String toPlainCode(String secretCode){
    	byte[] bytes = Base64.decode(secretCode);
    	if (bytes==null){
    		//解密失败
    		return secretCode;
    	}else{
    		return new String(bytes);
    	}
    }
    
    /**
     * 将http URL中的参数串parameters中的"openId"插入或更新为"openId="+openId
     * @param parameters
     * @param openId
     * @return
     */
    public static String mergeOpenId(String parameters, String openId){
    	if (parameters.contains(openId)){
    		return parameters;
    	}
    	if (parameters.contains("openId")){
    		//更新
    		int openIdIndex = parameters.indexOf("openId=")+"openId=".length();
    		int indexOfAnd = parameters.indexOf("&", openIdIndex);
    		String oldOpenId;
    		if (indexOfAnd==-1){
    			//openId是最后一个参数
    			oldOpenId = parameters.substring(openIdIndex);
    		}else{
    			//openId后面还有参数
    			oldOpenId = parameters.substring(openIdIndex, indexOfAnd);
    		}
    		return parameters.replace(oldOpenId, openId);
    	}else{
    		//添加
			if(parameters.indexOf("?") > -1){
	    		return parameters + "&openId="+openId;
			}else{
				return parameters + "?openId="+openId;
			}
    	}
    }
    
    public static void main(String[] args) {
    	String str = Base64.encode("cElkPTcwMDM3MCZySWQ9MTAwNCZjSWQ9NDU1Jm9wZW5JZD1vUVZNZHR3dU1fVnRpTmJTM2VuOVZRRVZPamw4=".getBytes());
    	String str2 = Base64.encode(str.getBytes());
    	System.out.println(str);
    	System.out.println(str2);
		System.out.println(new String(Base64.decode(new String(Base64.decode(str2)))));
//		System.out.println(new String(Base64.decode("cElkPTcwMDM3MCZySWQ9MTAwNCZjSWQ9NDU1Jm9wZW5JZD1vUVZNZHR3dU1fVnRpTmJTM2VuOVZRRVZPamw4=")));
		System.out.println(Base64.decode("sdfasdfas")==null);
		String payload = "Y21wdElkPTkxNCZjbXB0TmFtZT0lRTglQTclODYlRTklQTIlOTElRTclOUIlQjQlRTYlOTIlQUQmb3BlbklkPW9RVk1kdHd1TV9WdGlOYlMzZW45VlFFVk9qbDg=&from=groupmessage&isappinstalled=0";
		int suffixIndex = payload.indexOf("&from=groupmessage");
		payload = payload.substring(0, suffixIndex);
		System.out.println(new String(Base64.decode(payload)));	
		System.out.println("=====================================");
		System.out.println(new String(Base64.decode("Y21wdElkPTkxNCZjbXB0TmFtZT0lRTglQTclODYlRTklQTIlOTElRTclOUIlQjQlRTYlOTIlQUQmb3BlbklkPW9RVk1kdHd1TV9WdGlOYlMzZW45VlFFVk9qbDg=")));
		System.out.println(Base64.decode("asefasfd=")==null);
		String parameters = "cmptId=914&cmptName=%E8%A7%86%E9%A2%91%E7%9B%B4%E6%92%AD&openId=oQVMdtwuM_VtiNbS3en9VQEVOjl8&b=123";
		String parameters2 = "cmptId=914&cmptName=%E8%A7%86%E9%A2%91%E7%9B%B4%E6%92%AD&openId=oQVMdtwuM_VtiNbS3en9VQEVOjl";
		System.out.println(toPlainCode(parameters));
		System.out.println(toPlainCode("Y21wdElkPTkxNCZjbXB0TmFtZT0lRTglQTclODYlRTklQTIlOTElRTclOUIlQjQlRTYlOTIlQUQmb3BlbklkPW9RVk1kdHd1TV9WdGlOYlMzZW45VlFFVk9qbDg="));
		System.out.println(mergeOpenId(parameters, "232323"));
		System.out.println(mergeOpenId(parameters2, "232323"));
		System.out.println(mergeOpenId(parameters, "oQVMdtwuM_VtiNbS3en9VQEVOjl"));
		System.out.println(mergeOpenId("cmptId=914", "oQVMdtwuM_VtiNbS3en9VQEVOjl232"));
		
		
		
		
	}
}











