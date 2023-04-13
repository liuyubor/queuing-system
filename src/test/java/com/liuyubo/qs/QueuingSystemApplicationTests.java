package com.liuyubo.qs;

import com.liuyubo.qs.bodytrack.BodyTrack;
import com.liuyubo.qs.utils.RandomV;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;

import static java.lang.String.valueOf;

@SpringBootTest
class QueuingSystemApplicationTests {


    @Test
    void contextLoads() throws FileNotFoundException {
        // 获取随机数
        String path = valueOf((int) ((Math.random()*10)));
        // 获取随机视频
        String base64 = RandomV.getVideoPic(ResourceUtils.getFile("classpath:video/" + path + ".mp4"));

        String res = BodyTrack.body_num_base64(base64);

        System.out.println(res);

    }

}
