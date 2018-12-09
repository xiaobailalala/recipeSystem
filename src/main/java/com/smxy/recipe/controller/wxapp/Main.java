/**
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 * <p>
 * code is far away from bug with the animal protecting
 * <p>
 * ┏┓　　　┏┓
 * ┏┛┻━━━┛┻┓
 * ┃　　　　　　　┃
 * ┃　　　━　　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　　　┃
 * ┃　　　┻　　　┃
 * ┃　　　　　　　┃
 * ┗━┓　　　┏━┛
 * 　　┃　　　┃神兽保佑
 * 　　┃　　　┃代码无BUG！
 * 　　┃　　　┗━━━┓
 * 　　┃　　　　　　　┣┓
 * 　　┃　　　　　　　┏┛
 * 　　┗┓┓┏━┳┓┏┛
 * 　　　┃┫┫　┃┫┫
 * 　　　┗┻┛　┗┻┛
 *
 * @Package:
 * @author: zpx
 * Build File @date: 2018/12/8 18:42
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.controller.wxapp;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner id = new Scanner(System.in);
        while (id.hasNext()) {
            int[] dateArr = new int[2];
            for (int i = 0; i < dateArr.length; i++) {
                dateArr[i] = id.nextInt();
            }
            String resultStr = getLetterSortByDate(dateArr);
            id.nextLine();
            String str = id.nextLine();
            for (int i = 0; i < str.length(); i++) {
                int index = resultStr.indexOf(str.charAt(i)) + 1;
                if (index <= 9) {
                    System.out.print(1 + "" + index + " ");
                } else if (index <= 18) {
                    System.out.print(2 + "" + (index - 9) + " ");
                } else {
                    System.out.print(3 + "" + (index - 18) + " ");
                }
            }
            System.out.println();
        }
    }

    private static String getLetterSortByDate(int[] date) {
        String[] letterStr = {"ABCDEFGHI", "JKLMNOPQR", "STUVWXYZ "};
        letterStr = letterStrLeftShift(letterStr, (date[0] - 1) % 3);
        int len = letterStr.length;
        for (int i = 0; i < letterStr.length; i++) {
            letterStr[i] = letterLeftShift(letterStr[i], (date[1] - 1) % 9);
        }
        StringBuilder letterBuilder = new StringBuilder();
        for (String aLetterStr : letterStr) {
            letterBuilder.append(aLetterStr);
        }
        return letterBuilder.toString();
    }

    private static String[] letterStrLeftShift(String[] letterStr, int count) {
        List<String> letterStrList = new LinkedList<>(Arrays.asList(letterStr));
        while (count-- != 0) {
            letterStrList.add(letterStrList.get(0));
            letterStrList.remove(0);
        }
        return letterStrList.toArray(new String[letterStr.length]);
    }

    private static String letterLeftShift(String letter, int count) {
        StringBuilder letterBuilder = new StringBuilder(letter);
        while (count-- != 0) {
            letterBuilder.append(letterBuilder.charAt(0));
            letterBuilder = new StringBuilder(letterBuilder.substring(1, letterBuilder.length()));
        }
        letter = letterBuilder.toString();
        return letter;
    }
}
