package com.hzz.用两个栈来实现一个队列;

import java.util.Stack;

//首先栈是先进后出,类似于水桶
//其次队列是先进先出类似于水管。
public class Solution {
    Stack<Integer> stack1=new Stack<>();
    Stack<Integer> stack2=new Stack<>();

    public void push(Integer i){
        stack1.add(i);

    }

    public Integer pop(){
        if(stack2.empty()){
            while (!stack1.empty()){
                stack2.add(stack1.pop());
            }
        }

        if(stack2.empty()){
            return -1;
        }else{
            return stack2.pop();
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        solution.push(233131);
        solution.push(22);
        solution.push(223);

        System.out.println(solution.pop());
        System.out.println(solution.pop());
        System.out.println(solution.pop());
    }
}
