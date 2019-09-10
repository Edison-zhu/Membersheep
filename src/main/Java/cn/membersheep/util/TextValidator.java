package cn.membersheep.util;

/**
 * ÎÄ±¾ÑéÖ¤¹¤¾ßÀà
 * @author tarena
 *
 */
public final class TextValidator {
    /**
     * ÑéÖ¤ÓÃ»§ÃûµÄÕýÔò±í´ïÊ½
     */
    private static final String REGEX_USERNAME = "[a-zA-Z]{1}[a-zA-Z0-9_]{3,15}";

    private static final String REGEX_EMAIL = "[@]{1}[a-zA-Z0-9]+[.]+[a-z]+";

    private static final String REGEX_PHONE = "[0-9]{11}";

    /**
     * Ë½ÓÐ»¯¹¹Ôì·½·¨,²»Ï£Íû±»±ðÈË´´½¨¶ÔÏó
     */
    private TextValidator(){
        super();
    }

    /**
     * ÑéÖ¤ÓÃ»§Ãû¸ñÊ½
     * @param username ÓÃ»§Ãû
     * @return ·µ»ØtrueÊ±±íÊ¾·ûºÏ¸ñÊ½ÒªÇó,·µ»Øfalse±íÊ¾²»·ûºÏ¸ñÊ½ÒªÇó
     */
    public static boolean checkUsername(String username){
        if(username == null){
            return false;
        }
        return username.matches(REGEX_USERNAME);
    }

    /**
     * ÑéÖ¤ÃÜÂë¸ñÊ½
     * @param password ÃÜÂë
     * @return ·µ»ØtrueÊ±±íÊ¾·ûºÏ¸ñÊ½ÒªÇó,·µ»Øfalse±íÊ¾²»·ûºÏ¸ñÊ½ÒªÇó
     */
    public static boolean checkPassword(String password){
        if(password == null){
            return false;
        }
        return password.length() >=4 && password.length() <= 16;
    }

    /**
     * ÑéÖ¤ÓÊÏä¸ñÊ½
     * @param email ÓÊÏä
     * @return ·µ»ØtrueÊ±±íÊ¾·ûºÏ¸ñÊ½ÒªÇó,·µ»Øfalse±íÊ¾²»·ûºÏ¸ñÊ½ÒªÇó
     */
    public static boolean checkEamil(String email){
        if(email == null){
            return false;
        }
        return email.matches(REGEX_EMAIL);
    }

    /**
     * ÑéÖ¤ÊÖ»úºÅ¸ñÊ½
     * @param phone ÊÖ»úºÅ
     * @return ·µ»ØtrueÊ±±íÊ¾·ûºÏ¸ñÊ½ÒªÇó,·µ»Øfalse±íÊ¾²»·ûºÏ¸ñÊ½ÒªÇó
     */
    public static boolean checkPhone(String phone){
        if(phone == null){
            return false;
        }
        return phone.matches(REGEX_PHONE);
    }
}
