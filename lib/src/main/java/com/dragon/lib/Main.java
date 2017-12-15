package com.dragon.lib;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
    public static void main(String args[]) {

        System.out.print("方法一");

        Scanner scan = new Scanner(System.in);
        String read = scan.nextLine();

        System.out.println("输出:" + read);
//
//        System.out.println("method 2");
//        char tt = 0;
//        try {
//            tt = (char) System.in.read();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println("输出:" + tt);


        //Scanner取得的输入以space, tab, enter 键为结束符，
        //要想取得包含space在内的输入，可以用java.io.BufferedReader类来实现
        //使用BufferedReader的readLine( )方法
        //必须要处理java.io.IOException异常
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in ));
        //java.io.InputStreamReader继承了Reader类
        String read2 = null;
        System.out.print("输入数据：");
        try {
            read2 = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("输入数据："+read2);
    }
}
