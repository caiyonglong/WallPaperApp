package com.dragon.lib;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
    public static void main(String args[]) {

        System.out.print("����һ");

        Scanner scan = new Scanner(System.in);
        String read = scan.nextLine();

        System.out.println("���:" + read);
//
//        System.out.println("method 2");
//        char tt = 0;
//        try {
//            tt = (char) System.in.read();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println("���:" + tt);


        //Scannerȡ�õ�������space, tab, enter ��Ϊ��������
        //Ҫ��ȡ�ð���space���ڵ����룬������java.io.BufferedReader����ʵ��
        //ʹ��BufferedReader��readLine( )����
        //����Ҫ����java.io.IOException�쳣
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in ));
        //java.io.InputStreamReader�̳���Reader��
        String read2 = null;
        System.out.print("�������ݣ�");
        try {
            read2 = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("�������ݣ�"+read2);
    }
}
