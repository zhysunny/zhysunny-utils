package com.zhysunny.common.arithmetic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 进制数的相互转换
 * @author 章云
 * @date 2019/7/27 14:48
 */
public class HexadecimalUtils {
    /**
     * 10进制数转换为2到16以内的进制数
     * @param param  要转换的数
     * @param number 要转换的进制
     * @return
     */
    public static String getHexadecimal(int param, int number) {
        if (number > 16 || number < 2) {
            throw new RuntimeException("请输入number为2到16以内的");
        }
        List<Integer> list = new ArrayList<Integer>();
        int a = 0;
        while ((param / number) != 0) {
            a = param % number;
            param = param / number;
            list.add(a);
        }
        a = param % number;
        list.add(a);
        Collections.reverse(list);
        StringBuffer result = new StringBuffer();
        String temp = null;
        for (int i : list) {
            if (i >= 10) {
                temp = getMap().get(i + "");
            } else {
                temp = String.valueOf(i);
            }
            result.append(temp);
        }
        return result.toString();
    }

    /**
     * 2到10以内的进制数转换为10进制数
     * @param param  要转换的数
     * @param number 要转换数的进制
     * @return
     */
    public static int getTenHexadecimal(String param, int number) {
        if (number > 16 || number < 2) {
            throw new RuntimeException("请输入number为2到16以内的");
        }
        byte[] by = param.getBytes();
        for (int i = 0; i < by.length; i++) {
            if (by[i] >= 48 && by[i] <= 57) {
                if ((by[i] - 48) >= number) {
                    throw new RuntimeException("请准确输入被转换数的进制数number");
                }
            } else if (by[i] >= 97 && by[i] <= 102) {
                if ((by[i] - 87) >= number) {
                    throw new RuntimeException("请准确输入被转换数的进制数number");
                }
            } else {
                throw new RuntimeException("请准确输入要转换的数param");
            }
        }
        int a = 0;
        int a1 = 0;
        int result = 0;
        for (int i = by.length - 1; i >= 0; i--) {
            if (by[a] >= 48 && by[a] <= 57) {
                a1 = (by[a] - 48);
            } else if (by[a] >= 97 && by[a] <= 102) {
                a1 = (by[a] - 87);
            }
            for (int j = 0; j < i; j++) {
                a1 = a1 * number;
            }
            a++;
            result += a1;
        }
        return result;
    }

    private static Map<String, String> getMap() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("10", "a");
        map.put("11", "b");
        map.put("12", "c");
        map.put("13", "d");
        map.put("14", "e");
        map.put("15", "f");
        return map;
    }
}
