package com.masf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Main {

    /**
     * 题目：
     * 输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
     * 例如输入数组{3，32，321}，则打印出这三个数字能排成的最小数字为321323。
     *
     * 解题思路：
     * 先将整型数组转换成String数组，然后将String数组排序，最后将排好序的字符数组拼接出来，关键是制定排序规则
     * 排序规则：
     * 若ab > ba 则 a >b
     * 若ab < ba 则 a < b，
     * 若ab = ba 则 a = b；
     * 解释说明：
     * 比如 "3" < "31"但是 "331" > "313"，所以要将二者拼接起来进行比较
     * @param numbers
     * @return
     */
    public static String  printMinNumber(int[] numbers){
        if(numbers ==null || numbers.length ==0){
            return "";
        }
        int numLen = 0 ;//记录numbers数组的长度
        String resultStr = "";//数组中数字连接的结果

        //定义一个ArrayList数组 将numbers中的元素放入ArrayList
        ArrayList<Integer> numList = new ArrayList<Integer>();
        numLen = numbers.length;
        for(int i=0;i<numLen;i++){
            numList.add(numbers[i]);
        }

        //调用Collections的sort方法 ， 并使用自定义排序规则
        Collections.sort(numList, new Comparator<Integer>() {
            @Override
            public int compare(Integer num1, Integer num2) {
                String str1 = num1 + "" + num2;
                String str2 = num2 + "" + num1;
                return str1.compareTo(str2);
            }
        });

        for(int j:numList){
            resultStr+=j;
        }

        return resultStr;
    }
    public static void main(String[] args) {
	// write your code here
        int[] numbers = {1,51,89,321};

        String result = printMinNumber(numbers);

        System.out.println(result);
    }
}
