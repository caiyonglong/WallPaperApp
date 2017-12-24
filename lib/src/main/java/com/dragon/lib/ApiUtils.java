package com.dragon.lib;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

/**
 * Created by yonglong on 2017/12/22.
 */

public class ApiUtils {
    public static String getInfosFromUrl(String url) {
        String result = "";
        BufferedReader in = null;
//        HttpURLConnection connection = new H
        try {
            URL uri = new URL(url);
            URLConnection conn = uri.openConnection();
            conn.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = conn.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    conn.getInputStream(),"UTF-8"));

            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
