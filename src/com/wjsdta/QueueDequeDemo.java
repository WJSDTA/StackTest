package com.wjsdta;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: WJ
 * \* Date: 2019/3/21
 * \* Time: 11:05
 * \* To change this template use File | Settings | File Templates.
 * \* Description:Queue借口：队列
 * LinkedList实现了Queue借口
 * \
 */
public class QueueDequeDemo {

    private static void stack(){
        Stack<String> stack = new Stack<>();
        stack.push("Bin");
        stack.push("Tom");
        stack.push("Lily");
        stack.push("Lala");
        System.out.println(stack.peek());
        System.out.println(stack.pop());
    }
    private static void deque(){
        Deque<String> deque = new LinkedList<>();
      deque.add("小花");
      deque.add("小白");
      deque.add("小黑");
      deque.add("小蓝");
        System.out.println(deque.getFirst());
        System.out.println(deque.getLast());
        System.out.println();
        System.out.println();

    }
    private static void queue() {
        Queue<String> queue = new LinkedList<>();
        ((LinkedList<String>) queue).add("小花");
        ((LinkedList<String>) queue).add("小白");
        ((LinkedList<String>) queue).add("小黑");
        ((LinkedList<String>) queue).add("小蓝");
        System.out.println(queue.size());
        System.out.println(queue.peek());
        System.out.println(queue.size());
        System.out.println(queue.poll());
        System.out.println(queue.size());

    }

    public static void main(String[] args) {
        queue();
        deque();
    }
}