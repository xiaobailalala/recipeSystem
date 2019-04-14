package com.smxy.recipe.utils;

import java.util.Arrays;
import java.util.BitSet;

public class Main {
    /**
     * 每次把数组中所有元素移动一个位置，移动k轮
     *
     * @param array
     * @param k
     */
    public static void moveArrayElement(String[] array, int k) {
        int length = array.length;
        // 右移newk + n * length个位置，和右移newk个位置效果是一样的
        int newk = k % length;
        String temp = "";
        for (int i = 0; i < newk; i++) {
            temp = array[length - 1];
            for (int j = length - 2; j >= 0; j--) {
                array[j + 1] = array[j];
            }
            array[0] = temp;
        }
    }

    public static void main(String[] args) {
        int l = 1;
        System.out.println(Long.toBinaryString(Long.MAX_VALUE));
//        String[] arr = new String[]{"A", "B", "C"};
//        moveArrayElement(arr, 2);
//        System.out.println(Arrays.toString(arr));
//        String[][] letterStr = new String[][]{{"A", "B", "C", "D", "E", "F", "G", "H", "I"}, {"J", "K", "L", "M", "N", "O", "P", "Q", "R"}, {"S", "T", "U", "V", "W", "X", "Y", "Z", " "}};
    }

    /**
     * 开辟一个新数组，把旧数组中的元素直接放在新数组中正确的位置
     *
     * @param array
     * @param k
     * @return
     */
    public static int[] moveArrayElement1(int[] array, int k) {
        int length = array.length;
        // 右移newk + n * length个位置，和右移newk个位置效果是一样的
        int newk = k % length;
        int[] newArray = new int[length];
        // 重复length次把元素从旧位置移到新位置
        for (int i = 0; i < length; i++) {
            // 求出元素新的位置
            int newPosition = (i + newk) % length;
            newArray[newPosition] = array[i];
        }
        return newArray;
    }

    /**
     * 1.把一个元素放在一个正确的位置，再把被占位置的元素放到它应该在的正确的位置，一直
     * 重复下去，直到数组的所有元素都放在了正确的位置；
     * 2.但是必须考虑环形的情况，比如十个元素的数组，右移5个位置，这时，位置0的元素应该放在位置5，
     * 位置5的元素应该放在位置0，这样，完全通过1的迭代就不能得到 正确的结果
     *
     * @param array
     * @param k
     */
    public static void moveArrayElement2(int[] array, int k) {
        int length = array.length;

        BitSet bitSet = new BitSet(length);
        boolean flag = false;
        // 保证最多只移动count=length次位置
        int count = 0;
        for (int j = 0; j < length; j++) {
            if (flag) {
                break;
            }
            if (!bitSet.get(j)) {
                // 右移newk + n * length个位置，和右移newk个位置效果是一样的
                int newk = k % length;
                // 旧位置
                int oldPosition = j;
                // 保存旧位置的值
                int oldValue = array[oldPosition];
                // 临时值
                int temp = 0;
                // 重复length次把元素从旧位置移到新位置
                for (int i = 0; i < length; i++) {
                    // 求出元素新的位置
                    int newPosition = (oldPosition + newk) % length;
                    // 如果新位置已经放置了对得值，就不要往新位置再次放入值了
                    if (bitSet.get(newPosition)) {
                        break;
                    }
                    // 临时保存新位置(也就是新的旧位置)的值
                    temp = array[newPosition];
                    // 移动元素到新位置
                    array[newPosition] = oldValue;
                    // 又一个位置放置了正确的值
                    count++;
                    if (count == length) {
                        flag = true;
                        break;
                    }
                    // 新位置放置了正确的值
                    bitSet.set(newPosition);
                    // 永久保存旧位置的值
                    oldValue = temp;
                    // 新位置变为旧位置
                    oldPosition = newPosition;
                }
            }
        }
        System.out.println(count);
    }

    /**
     * 经典方法，三次倒置数组中对应位置的元素;
     * 简单说一下原理：数组元素右移k个位置的结果是，原来在
     * 后面的k个元素跑到了数组前面，原来在前面的length-k
     * 个元素，跑到了数组的后面，并且前后两部分元素各自的顺序和
     * 移动前一致，而倒置整个数组元素就是让后面k个元素跑到前面去，
     * 让前面length-k个元素跑到后面去，但是倒置之后前后两部分
     * 元素的顺序跟移动之前不一样了，倒置了，所以要把两部分的元素
     * 倒置回来
     *
     * @param array
     * @param k
     */
    public static void moveArrayElement3(int[] array, int k) {
        // 倒置所有元素
        reverse(array);
        // 倒置前k个元素
        reverse(array, 0, k - 1);
        // 倒置后length - k个元素
        reverse(array, k, array.length - 1);
    }

    /**
     * 倒置数组中begin和end之间的元素，包括begin和end
     *
     * @param array
     * @param begin
     * @param end
     */
    private static void reverse(int[] array, int begin, int end) {
        int length = end - begin + 1;
        int half = length / 2;
        for (int i = 0; i < half; i++) {
            int temp = array[begin];
            array[begin] = array[end];
            array[end] = temp;
            begin++;
            end--;
        }

    }

    /**
     * 倒置数组中begin和end之间的元素，包括begin和end
     *
     * @param array
     * @param begin
     * @param end
     */
    private static void reverse(int[] array) {
        reverse(array, 0, array.length - 1);
    }
}
