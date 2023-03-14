package com.liuyubo.qs.utils;

import okhttp3.OkHttpClient;
import org.springframework.stereotype.Component;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Component
public class BodyTrack {
    static final OkHttpClient HTTP_CLIENT = new OkHttpClient().newBuilder().build();


    /**
     * @return 返回示例
     *{
     *"log_id": 716033439,
     * "person_num": 16,
     * "image": "/9j/4AAoFS2P/9k="
     * }
     */
    public static String body_num() {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/image-classify/v1/body_num";
        try {
            // 本地文件路径
            String filePath = "[文件路径]";
            byte[] imgData = FileUtil.readFileByBytes(filePath);
            String imgStr = Base64Util.encode(imgData);
            String imgParam = URLEncoder.encode(imgStr, StandardCharsets.UTF_8);

            String param = "image=" + imgParam;

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = BaiduAuthUtil.getAuth();

            String result = HttpUtil.post(url, accessToken, param);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
