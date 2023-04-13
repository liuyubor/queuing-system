package com.liuyubo.qs.utils;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.Random;

@Component
public class RandomV {

    final static ByteArrayOutputStream os = new ByteArrayOutputStream();


    public static String getRandomfilePath() {

        return String.valueOf((int) ((Math.random() * 10)));
    }

    /**
     * 获取视频时长，单位为秒
     *
     * @param video 源视频文件
     * @return 时长（s）
     */
    public static long getVideoDuration(File video) {
        long duration = 0L;
        FFmpegFrameGrabber ff = new FFmpegFrameGrabber(video);
        try {
            ff.wait();
            duration = ff.getLengthInTime() / (1000 * 1000);
            ff.stop();
        } catch (FrameGrabber.Exception | InterruptedException e) {
            e.printStackTrace();
        }
        return duration;
    }


    /**
     * 截取视频获得指定帧的图片
     *
     * @param video   源视频文件
     * @return
     */
    public static String getVideoPic(File video) {
        FFmpegFrameGrabber ff = new FFmpegFrameGrabber(video);
        String base64 = null;
        try {
            ff.start();

            // 截取中间帧图片(具体依实际情况而定)
            int i = 0;
            int length = ff.getLengthInFrames();
            //int middleFrame = length / 2;
            Random r = new Random();
            int middleFrame = r.nextInt(length);
            Frame frame = null;
            while (i < length) {
                frame = ff.grabFrame();
                if ((i > middleFrame) && (frame.image != null)) {
                    break;
                }
                i++;
            }

            // 截取的帧图片
            Java2DFrameConverter converter = new Java2DFrameConverter();
            BufferedImage srcImage = converter.getBufferedImage(frame);
            int srcImageWidth = srcImage.getWidth();
            int srcImageHeight = srcImage.getHeight();

            // 对截图进行等比例缩放(缩略图)
            int width = 720;
            int height = (int) (((double) width / srcImageWidth) * srcImageHeight);
            BufferedImage thumbnailImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
            thumbnailImage.getGraphics().drawImage(srcImage.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);
//            System.out.println(picPath);

            ImageIO.write(thumbnailImage, "jpg", os);
            base64 = Base64.getEncoder().encodeToString(os.toByteArray());

//            ImageIO.write(thumbnailImage, "jpg", new File(picPath));

            ff.stop();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return base64;
    }

}
