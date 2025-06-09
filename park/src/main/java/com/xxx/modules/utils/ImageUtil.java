package com.xxx.modules.utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;

public class ImageUtil {


    private static Logger logger = LoggerFactory.getLogger(ImageUtil.class);


    public static String base64ToImgTransformation(String path, String img, String imgName) {
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            //前台在用Ajax传base64值的时候会把base64中的+换成空格，所以需要替换回来。
            String baseValue = img.replaceAll(" ", "+");
            //去除base64中无用的部分
            byte[] b = decoder.decodeBuffer(baseValue.replace("data:image/png;base64,", ""));
            for (int i = 0; i < b.length; ++i) {
                // 调整异常数据
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            File file = new File(path);
            if (!file.exists()) {
                file.mkdirs();
            }

            String imgPath = path + imgName + ".png";

            OutputStream out = new FileOutputStream(imgPath);
            //写入数据
            out.write(b);
            out.flush();
            out.close();
            return imgName + ".png";
        }catch (Exception e){
            return null;
        }

    }


    //将图片文件转化为字节数组字符串，并对其进行Base64编码处理
    public static String imageBase64(String imgFile) {
        //待处理的本地图片
        InputStream in = null;
        byte[] data = null;
        //读取图片字节数组
        try {
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        //返回Base64编码过的字节数组字符串

        return  encoder.encode(data);
    }
}
