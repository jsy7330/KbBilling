package com.ntels.ccbs.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class CUtil {

    public CUtil() {
    }

    public static void copyObjectValue(Object src, Object dest) {
        Field[] bFields = dest.getClass().getDeclaredFields();

        for (Field field : bFields) {
            String name = field.getName();
            //			name = name.substring(0, 1).toUpperCase() + name.substring(1, name.length());

            if (Character.isUpperCase(name.charAt(1))) {
                name = name.substring(0);
            } else {
                name = Character.toUpperCase(name.charAt(0)) + name.substring(1);
            }

            try {

                Method get = src.getClass().getDeclaredMethod("get" + name);

                Object value = get.invoke(src);

                if (value == null) {
                    continue;
                }

                Method set = dest.getClass().getDeclaredMethod("set" + name, get.getReturnType());

                set.invoke(dest, value);
            } catch (Exception e) {
                // 에러 무시		
            }
        }
    }
}
