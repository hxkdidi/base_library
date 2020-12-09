package com.lptiyu.lp_base.uitls;


import java.util.Collection;

/**
 * Created by 11298 on 2017/3/16.
 */

public class CollectionUtils {
    /**
     * ma
     * 判断集合是否为空
     *
     * @param collection
     * @return
     */
    public static boolean isEmpty(Collection collection) {
        return collection == null || collection.size() <= 0;
    }

    /**
     * 清空集合
     *
     * @param collections
     * @return
     */
    public static void clearList(Collection... collections) {
        for (Collection collection : collections) {
            if (collection != null) {
                collection.clear();
            }
        }
    }

    /**
     * 比较两个集合是否相等
     *
     * @param a
     * @param b
     * @param <T>
     * @return
     */
    public static <T> boolean equals(Collection<T> a, Collection<T> b) {
        if (a == null) {
            return false;
        }
        if (b == null) {
            return false;
        }
        if (a.isEmpty() && b.isEmpty()) {
            return true;
        }
        return a.size() == b.size() && a.equals(b);
    }
}
