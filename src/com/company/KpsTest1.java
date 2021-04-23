package com.company;

import java.util.Scanner;
import java.util.Stack;

public class KpsTest1 {

    static Scanner scan = new Scanner(System.in);

    static Stack<Character> stack = new Stack<>();
    static int multi;
    static boolean remain = false;

    public static void main(String[] args) {

//        String input1 = "3[a4[c]]";
//        String input1 = "3[a]z";
//        String input1 = "2[2[a]2[c]]2[a2[c3[b]]]a3[c]a2[c]";

        String input = scan.next();

        do {
            remain = false;
            input = execute(input);
        } while (remain);

        System.out.println(input);
    }

    public static String execute(String str) {
        String result = "";

        int sz = str.length();

        String temp = "";

        for (int i = 0; i < sz; i++) {
            char pos = str.charAt(i);

            if (!stack.isEmpty()) {

                if (pos == '[') stack.add('['); remain=true;
                if (pos == ']') stack.pop();

                if (stack.isEmpty()) {
                    for (int m = 0; m < multi; m++) {
                        result += temp;
                    }
                    temp = "";
                    multi = 0;
                } else {
                    temp += pos;
                }
            } else {
                if (Character.isDigit(pos)) {
                    multi = charToInt(pos);
                    pos = str.charAt(++i);
                    stack.add(pos);
                } else {
                    result += pos;
                }
            }
        }


        return result;
    }

    public static int charToInt(char c) {
        return c - '0';
    }
}
