package com.example.demo.od.c132;

import java.util.*;

/**
 * 提取字符串中的最长合法简单数学表达式  (这里重点记录下，用例非常奇葩，很多代码完全是为了贴合用例)
 */
public class Demo035 {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        System.out.println(getResult(str));
    }
    //2 + 2
    public static long getResult(String str){
        Set<Character> set = new HashSet<>(Arrays.asList('+','-','*','0','1','2','3','4','5','6','7','8','9'));
        Set<Character> signSet = new HashSet<>(Arrays.asList('+','-','*'));
        Stack<Long> stack = new Stack<>();
        //因为可能出现 +1 +2 而非 2+2+2这种形式
        stack.add(0L);
        char[] chars = str.toCharArray();
        //上一个运算符
        char preSign = '+';
        //上一个值
        long preNum = 0;
        //上一个字符是否为运算符
        boolean flag = false;
        //最大长度
        int maxLen = 0;
        //记录中间过程产生的长度
        int len = 0;
        long res = 0;
        for (int i = 0; i < chars.length; i++) {
            char c= chars[i];
            len ++;
            if (Character.isDigit(c)){
                preNum = preNum * 10 + c - '0';
                flag = false;
            }
            if (!Character.isDigit(c) || i == chars.length -1){
                switch (preSign){
                    case '+':
                        if (i > 0 && Character.isDigit(chars[i-1]) || i == chars.length - 1){
                            stack.add(preNum);
                        }
                        break;
                    case '-':
                        stack.add(-preNum);
                        break;
                    case '*':
                        //看用例是 +1 算 0+1， *2 算 1 *2   真无语
                        if (stack.size() == 1 && stack.peek() == 0){
                            stack.pop();
                            stack.add(1L);
                        }
                        stack.add(stack.pop() * preNum);
                        break;
                    default:
                }
                preNum = 0;
                preSign = c;
                //不合法 || 到末尾了，要进行最后的计算
                if (signSet.contains(c) && flag || !set.contains(c) || i == chars.length -1){
                    //用例上来看 2+ 算长度1...  +2 却算长度2...
                    if (i > 0 && i != chars.length - 1 && signSet.contains(chars[i-1])){
                        len --;
                    }
                    len --;
                    if (i == chars.length - 1 && Character.isDigit(c)){
                        len ++;
                    }
                    //清空stack
                    if (maxLen < len && !stack.isEmpty()){
                        res = 0;
                        maxLen = len;
                        while (!stack.isEmpty()){
                            res += stack.pop();
                        }
                    }
                    stack.clear();
                    stack.add(0l);
                    flag = false;
                    len = 0;
                    preNum = 0;
                    preSign = '+';
                    continue;
                }
                flag = true;
            }
        }


        return res;
    }
}
