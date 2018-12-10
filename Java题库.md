 #1.
 public class Solution {
    public int NumberOf1(int n) {
        int count = 0;
        while(n!= 0){
            count++;
            n = n & (n - 1);
         }
        return count;
    }
}
答案正确:恭喜！您提交的程序通过了所有的测试用例
分析一下代码： 这段小小的代码，很是巧妙。
如果一个整数不为0，那么这个整数至少有一位是1。
如果我们把这个整数减1，那么原来处在整数最右边的1就会变为0，原来在1后面的所有的0都会变成1(如果最右边的1后面还有0的话)。
其余所有位将不会受到影响。
举个例子：一个二进制数1100，从右边数起第三位是处于最右边的一个1。减去1后，第三位变成0，它后面的两位0变成了1，而前面的1保持不变，
因此得到的结果是1011.我们发现减1的结果是把最右边的一个1开始的所有位都取反了。这个时候如果我们再把原来的整数和减去1之后的结果做与运算，
从原来整数最右边一个1那一位开始所有位都会变成0。如1100&1011=1000.也就是说，把一个整数减去1，再和原整数做与运算，会把该整数最右边一个1变成0.
那么一个整数的二进制有多少个1，就可以进行多少次这样的操作。 


#2.在一个字符串(0<=字符串长度<=10000，全部由字母组成)中找到第一个只出现一次的字符,并返回它的位置, 如果没有则返回 -1（需要区分大小写
解题思路一：暴力搜索法
从头开始扫描整个字符串的每个字符，当访问到某个字符时，都拿这个字符和后面的每个字符比较
如果在后面没有发现重复字符，则说明这个字符就是只出现一次

解题思路二：hashmap
1.扫描整个字符串，使用HashMap记录字符串中每个字符出现的次数
2.扫描整个字符串，返回在HashMap中第一个字符在HashMap中出现次数为1的索引
public class Solution {
    public int FirstNotRepeatingChar(String str) {
        if(str==null || str.length()==0){
            return -1;
        }
        char[] chas = str.toCharArray();
        Map<Character,Integer> chasMap = new HashMap<Character, Integer>();

        chasMap.put(chas[0],1);
        for(int i=1;i<chas.length;i++){
            if(chasMap.containsKey(chas[i])){
                chasMap.put(chas[i],chasMap.get(chas[i])+1);
            }else{
                chasMap.put(chas[i],1);
            }
        }

        int min = 0;
        for(int i=0;i<chas.length;i++){
            if(chasMap.get(chas[i])==1){
                //min = min<i?min:i;
                min = i;
                break;
            }
        }
        return min;
    }
}
#3.汇编语言中有一种移位指令叫做循环左移（ROL），现在有个简单的任务，就是用字符串模拟这个指令的运算结果。
#对于一个给定的字符序列S，请你把其循环左移K位后的序列输出。
##例如，字符序列S=”abcXYZdef”,要求输出循环左移3位后的结果，即“XYZdefabc”。是不是很简单？OK，搞定它！
##解题思路：先将abc逆序，再将XYZdef逆序，最后将cbafedZYX逆序
public class Solution {
    public void reverse(char[] chas,int start,int end){
        
		char temp = '0';
        while(start<end){
            temp = chas[start];
            chas[start] = chas[end];
            chas[end] = temp;
            start++;
            end--;
        }
    }
    public String LeftRotateString(String str,int n) {
        if(str==null || str.length()==0){
            return "";
        }
        char[] chas = str.toCharArray();
        reverse(chas,0,n-1);
        /*for(char s:chas){
            System.out.print(s);
        }
        System.out.println();*/
        reverse(chas,n,chas.length-1);
        /*for(char s:chas){
            System.out.print(s);
        }
        System.out.println();*/
        reverse(chas,0,chas.length-1);
        /*for(char s:chas){
            System.out.print(s);
        }
        System.out.println();*/
        String reverseStr = new String(chas);
        //System.out.println("reverseStr:"+reverseStr);
        return reverseStr;
    }
}

#4.输入一个递增排序的数组和一个数字S，在数组中查找两个数，使得他们的和正好是S，如果有多对数字的和等于S，输出两个数的乘积最小的。
##解题思路：将数组元素保存HashMap中，在由于题目要求是递增排序数组，找到第一个sum-array[i]在HashMap中即可。
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
public class Solution {
	
    public ArrayList<Integer> FindNumbersWithSum(int [] array,int sum) {
        ArrayList<Integer> tempList = new ArrayList<Integer>();
        Map<Integer,Integer> numMap = new HashMap<Integer,Integer>();
        Map<Integer,Integer> resultMap = new HashMap<Integer,Integer>();
        for(int i=0;i<array.length;i++){
            numMap.put(array[i],i);
        }
        for(int i=0;i<array.length;i++){
            if(numMap.containsKey(sum-array[i])){
                resultMap.put(array[i],sum-array[i]);
                tempList.add(array[i]);
                tempList.add(sum-array[i]);
                break;
            }
        }
        return tempList;
    }
}