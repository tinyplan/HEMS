package com.tinyplan.exam.common.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 前缀
 */
public class PrefixUtil {
    public static final Map<String, String> USER_ID_MAP;

    static {
        USER_ID_MAP = new HashMap<>();
        USER_ID_MAP.put("r1001", "adm");
        USER_ID_MAP.put("r1002", "edu");
        USER_ID_MAP.put("r1003", "can");
        USER_ID_MAP.put("r1004", "inv");
    }

    public static String getUserIdPrefix(String roleId){
        return USER_ID_MAP.get(roleId);
    }



}
