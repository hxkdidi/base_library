package com.lptiyu.lp_base.uitls;

import java.util.ArrayList;

/**
 * Created by 11298 on 2016/12/8.
 */

public class MathUtils {
    public static String getSixStringRandomly() {
        return (int) ((Math.random() * 9 + 1) * 100000) + "";
    }

    /**
     * 随机指定范围内N个不重复的数 最简单最基本的方法，能取到min，不能取到max，0 < n <= max-min
     *
     * @param min 指定范围最小值
     * @param max 指定范围最大值
     * @param n   随机数个数
     */
    public static ArrayList<Integer> randomCommon(int min, int max, int n) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        if (n > (max - min + 1) || max < min) {
            return arrayList;
        }
        int count = 0;
        while (count < n) {
            int num = (int) (Math.random() * (max - min)) + min;
            boolean flag = true;
            int size = arrayList.size();
            for (int j = 0; j < size; j++) {
                if (num == arrayList.get(j)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                arrayList.add(num);
                count++;
            }
        }
        return arrayList;
    }
}
