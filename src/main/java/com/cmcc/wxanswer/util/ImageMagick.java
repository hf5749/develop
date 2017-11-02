package com.cmcc.wxanswer.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Iterator;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.apache.commons.lang3.StringUtils;
import org.im4java.core.CompositeCmd;
import org.im4java.core.ConvertCmd;
import org.im4java.core.IMOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * Created by IntelliJ IDEA.
 * User: LiuYang
 * Date: 12-7-9
 * Time: 下午1:46
 */
public class ImageMagick {
    final static Logger logger = LoggerFactory.getLogger(ImageMagick.class);
    private static String imageMagickPath = null;
    private static String fontsPath = "c:\\windows\\fonts\\sursong.ttf";
    private static Boolean isWin = false;

    static {
        Properties p = System.getProperties();
        String osName = p.getProperty("os.name");
        if (StringUtils.containsIgnoreCase(osName, "win")) {
            imageMagickPath = "c:\\Program Files\\ImageMagick-6.7.7-Q16";
            isWin=true;
        } else if (StringUtils.containsIgnoreCase(osName, "linux")) {
            imageMagickPath = "/usr/local/ImageMagick";
        }
    }

    /*
     * 根据坐标裁剪图片
     * @param srcPath   要裁剪图片的路径
     * @param newPath   裁剪图片后的路径
     * @param x   起始横坐标
     * @param y   起始挫坐标
     * @param x1  结束横坐标
     * @param y1  结束挫坐标
     */
    public static void cutImage(String srcPath, String newPath, int x, int y, int x1, int y1) throws Exception {
        int width = x1 - x;  //裁剪的宽度
        int height = y1 - y;  //裁剪的高度
        IMOperation op = new IMOperation();
        op.addImage(srcPath);
        op.crop(width, height, x, y);
        op.p_repage();
        op.addImage(newPath);
        ConvertCmd convert = new ConvertCmd();
        //linux下不要设置此值
        if (isWin) {
            convert.setSearchPath(imageMagickPath);
        }
        convert.run(op);
    }

    /*
     * 根据坐标裁剪图片
     * @param srcPath   要裁剪图片的路径
     * @param newPath   裁剪图片后的路径
     * @param x   起始横坐标
     * @param y   起始挫坐标
     * @param x1  结束横坐标
     * @param y1  结束挫坐标
     */
    public static void cutImage(String srcPath, String newPath, int x1, int y1) throws Exception {
        int width = 0;  //裁剪的宽度
        int height = 0;  //裁剪的高度
        IMOperation op = new IMOperation();
        op.gravity("center");
        op.addImage(srcPath);
        op.crop(width, height, x1, y1);
        op.p_repage();
        op.addImage(newPath);
        ConvertCmd convert = new ConvertCmd();
        //linux下不要设置此值
        if (isWin) {
            convert.setSearchPath(imageMagickPath);
        }
        convert.run(op);
    }

    /* 图片复制
     * @param imgSrc   图片源地址
     * @param newSrc  复制地地址
     */
    public static void copyImage(String imgPath, String newPath) throws Exception {
        IMOperation op = new IMOperation();
        op.addImage(imgPath);
        op.addImage(newPath);
        ConvertCmd convert = new ConvertCmd();
        //linux下不要设置此值
        if (isWin) {
            convert.setSearchPath(imageMagickPath);
        }
        convert.run(op);
    }

    /*
     * 根据尺寸缩放图片
     * @param width  缩放后的图片宽度
     * @param height  缩放后的图片高度
     * @param srcPath   源图片路径
     * @param newPath   缩放后图片的路径
     */
    public static void resizeImage(int width, int height, String srcPath, String newPath) throws Exception {
    	logger.debug("ImageMagick.resizeImage:" + width + ":" + height + "," + srcPath + "," + newPath);
        IMOperation op = new IMOperation();
        op.addImage();
        op.resize(width, height);
        op.addImage();
        //op.addRawArgs("-debug all");
        ConvertCmd convert = new ConvertCmd();
        //linux下不要设置此值
        if (isWin) {
            convert.setSearchPath(imageMagickPath);
        }
        convert.run(op, srcPath, newPath);
    }

    /*
     * 根据宽度缩放图片
     * @param width  缩放后的图片宽度
     * @param srcPath   源图片路径
     * @param newPath   缩放后图片的路径
     */
    public static void resizeImage(int width, String srcPath, String newPath) throws Exception {
        IMOperation op = new IMOperation();
        op.addImage(srcPath);
        op.resize(width, null);
        op.addImage(newPath);
        ConvertCmd convert = new ConvertCmd();
        //linux下不要设置此值
        if (isWin) {
            convert.setSearchPath(imageMagickPath);
        }
        convert.run(op);
    }

    /*
     * 根据高度缩放图片
     * @param height  缩放后的图片高度
     * @param srcPath   源图片路径
     * @param newPath   缩放后图片的路径
     */
    public static void resizeImageByHeight(int height, String srcPath, String newPath) throws Exception {
        IMOperation op = new IMOperation();
        op.addImage(srcPath);
        op.resize(null, height);
        op.addImage(newPath);
        ConvertCmd convert = new ConvertCmd();
        //linux下不要设置此值
        if (isWin) {
            convert.setSearchPath(imageMagickPath);
        }
        convert.run(op);
    }

    /*
     * 给图片加文字水印
     * @param srcPath 源图片路径
     */
    public static void addImgText(String srcPath) throws Exception {
        IMOperation op = new IMOperation();
        op.font(fontsPath).gravity("southeast").pointsize(12).fill("#FFFFFF").draw("text 5,5 '移动云教育'");
        op.addImage();
        op.addImage();
        ConvertCmd convert = new ConvertCmd();
        if (isWin) {
            convert.setSearchPath(imageMagickPath);
        }
        convert.run(op, srcPath, srcPath);
    }

    /*
     * 给图片加文字水印,并输出到新的路径
     * @param srcPath 源图片路径
     * @param newPath 水印后图片路径
     */
    public static void addImgText(String srcPath, String newPath) throws Exception {
        IMOperation op = new IMOperation();
        //gravity("Center")中心加水印
        op.font(fontsPath).gravity("southeast").pointsize(38).fill("black").draw("text 55,50  '大家faf好'");
        op.addImage();
        op.addImage();
        ConvertCmd convert = new ConvertCmd();
        //linux下不要设置此值
        if (isWin) {
            convert.setSearchPath(imageMagickPath);
        }
        convert.run(op, srcPath, newPath);
    }

    /*
     * 给图片加图片水印,并输出到新的路径
     * @param shuiyinPath 水印素材图片路径
     * @param scrPath 需要增加水印的图片路径
     * @param newPath 增加水印以后的图片路径
     */
    public static void addImgShuiyin(String shuiyinPath, String scrPath, String newPath) throws Exception {
        IMOperation op = new IMOperation();
        op.gravity("southeast");
        op.addImage();
        op.addImage();
        op.addImage();
        CompositeCmd compositeCmd = new CompositeCmd();
        //linux下不要设置此值
        if (isWin) {
            compositeCmd.setSearchPath(imageMagickPath);
        }
        compositeCmd.run(op, shuiyinPath, scrPath, newPath);
    }

    /*
     * 图片翻转，并覆盖源文件
     * @param imgSrc 图片路径
     * @param mode 翻转模式（0顺时针，1，逆时针）
     * @param angle 翻转角度，此处绝对为非负数
     */
    public static void overturnAndOverWritePic(String imgSrc, int mode, double angle) throws Exception {
        IMOperation op = new IMOperation();
        if (mode == 0) op.rotate(angle);
        if (mode == 1) {
            angle = angle * -1;
            op.rotate(angle);
        }
        op.addImage();
        op.addImage();
        ConvertCmd convertCmd = new ConvertCmd();
        //linux下不要设置此值
        if (isWin) {
            convertCmd.setSearchPath(imageMagickPath);
        }
        convertCmd.run(op, imgSrc, imgSrc);
    }

    /*
     * 图片翻转，并创建新文件
     * @param imgSrc 图片路径
     * @param newImgSrc 新图片路径
     * @param mode 翻转模式（0顺时针，1，逆时针）
     * @param angle 翻转角度，此处绝对为非负数
     */
    public static void overturnAndCreateNewPic(String imgSrc, String newImgSrc, int mode, double angle) throws Exception {
        IMOperation op = new IMOperation();
        if (mode == 0) op.rotate(angle);
        if (mode == 1) {
            angle = angle * -1;
            op.rotate(angle);
        }
        op.addImage();
        op.addImage();
        ConvertCmd convertCmd = new ConvertCmd();
        //linux下不要设置此值
        if (isWin) {
            convertCmd.setSearchPath(imageMagickPath);
        }
        convertCmd.run(op, imgSrc, newImgSrc);
    }

    /*
     * 图片截取最后一帧,主要用于GIF，其他图片格式也可以
     * @param imgSrc 图片路径
     * @param newImgSrc 新图片路径      *
     */
    public static void gifPicCut(String imgSrc, String newImgSrc) throws Exception {
        IMOperation op = new IMOperation();
        op.flatten();
        op.addImage();
        op.addImage();
        ConvertCmd convertCmd = new ConvertCmd();
        //linux下不要设置此值
        if (isWin) {
            convertCmd.setSearchPath(imageMagickPath);
        }
        convertCmd.run(op, imgSrc, newImgSrc);
    }

    /*
     * 获取正确的图片格式
     * @param file_upload 上传的图片对象，里面有个图片流
     * 返回值：图片类型（如果这个类型不对头的话是空字符串）
     */
    public static String getPicFormat(CommonsMultipartFile file_upload) {
        String fileExtend = null;
        ImageInputStream imageInputStream = null;
        Iterator<ImageReader> iterator = null;
        try {
            imageInputStream = ImageIO.createImageInputStream(file_upload.getInputStream());
            iterator = ImageIO.getImageReaders(imageInputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        while (iterator.hasNext()) {
            ImageReader reader = iterator.next();
            try {
                fileExtend = reader.getFormatName();//获取上传文件的扩展名
            } catch (Exception e) {
                fileExtend = "";
                e.printStackTrace();
            }
        }
        return fileExtend;
    }

    //切中图的方法
    public static int makeMiddlePic(String oriPicPath, String middlePicPath) {
        try {
            //拿图片长宽
            File oriFile = new File(oriPicPath);
            BufferedImage image = null;
            image = ImageIO.read(oriFile);
            int height = image.getHeight();
            int width = image.getWidth();
            if (height >= 140 || width >= 220) {
                resizeImage(220, 140, oriPicPath, middlePicPath);
                gifPicCut(middlePicPath, middlePicPath);
            } else {
                copyImage(oriPicPath, middlePicPath);
                gifPicCut(middlePicPath, middlePicPath);
            }
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception oriPicPath="+oriPicPath+",middlePicPath="+middlePicPath);
            return -1;
        }
    }

    //切原图的方法
    public static int makeOriPic(String oriPicPath, int isShuiyin) {
        try {
            //拿图片长宽
            File oriFile = new File(oriPicPath);
            BufferedImage image = null;
            image = ImageIO.read(oriFile);
            int height = image.getHeight();
            int width = image.getWidth();
            if (height >= 2000 || width >= 1000) {
                resizeImage(1000, 2000, oriPicPath, oriPicPath);
            } else {
                copyImage(oriPicPath, oriPicPath);
            }
            return 1;
        } catch (Exception e) {
            System.out.println("Exception oriPicPath="+oriPicPath);
            e.printStackTrace();
            return -1;
        }
    }

    //切详细图的方法
    public static int makeDetailPic(String oriPicPath, String detailPicPath, int isShuiyin) {
        try {
            //拿图片长宽
            File oriFile = new File(oriPicPath);
            BufferedImage image = null;
            image = ImageIO.read(oriFile);
            int height = image.getHeight();
            int width = image.getWidth();
            if (height >= 2000 || width >= 1000) {
                resizeImage(1000, 2000, oriPicPath, detailPicPath);
            } else {
                copyImage(oriPicPath, detailPicPath);
            }
            File detailFile = new File(detailPicPath);
            image = ImageIO.read(detailFile);
            height = image.getHeight();
            width = image.getWidth();
            if (height >= 160 && width >= 200 && isShuiyin == 1) {
                addImgText(detailPicPath);
            }
            if (detailFile.length() > 1024 * 1024) return -2;
            return 1;
        } catch (Exception e) {
            System.out.println("Exception oriPicPath="+oriPicPath+",detailPicPath="+detailPicPath);
            e.printStackTrace();
            return -1;
        }
    }

    //切小图的方法
    public static int makeSmallPic(String oriPicPath, String smallPicPath) {
        try {
            //拿图片长宽
            File oriFile = new File(oriPicPath);
            BufferedImage image = null;
            image = ImageIO.read(oriFile);
            int height = image.getHeight();
            int width = image.getWidth();
            if (height >= 52 || width >= 60) {
                resizeImage(60, 52, oriPicPath, smallPicPath);
                gifPicCut(smallPicPath, smallPicPath);

            } else {
                copyImage(oriPicPath, smallPicPath);
                gifPicCut(smallPicPath, smallPicPath);
            }
            return 1;
        } catch (Exception e) {
            System.out.println("Exception oriPicPath="+oriPicPath+",smallPicPath="+smallPicPath);
            e.printStackTrace();
            return -1;
        }
    }

    //切大图的方法
    public static int makeBigPic(String oriPicPath, String bigPicPath, int isShuiyin) {
        try {
            //拿图片长宽
            File oriFile = new File(oriPicPath);
            BufferedImage image = null;
            image = ImageIO.read(oriFile);
            int height = image.getHeight();
            int width = image.getWidth();
            if (width >= 240) {
                resizeImage(240, oriPicPath, bigPicPath);
                gifPicCut(bigPicPath, bigPicPath);

            } else {
                copyImage(oriPicPath, bigPicPath);
                gifPicCut(bigPicPath, bigPicPath);
            }
            File bigFile = new File(bigPicPath);
            image = ImageIO.read(bigFile);
            height = image.getHeight();
            width = image.getWidth();
            if (height >= 160 && width >= 200 && isShuiyin == 1) addImgText(bigPicPath);
            return 1;
        } catch (Exception e) {
            System.out.println("Exception oriPicPath="+oriPicPath+",bigPicPath="+bigPicPath);
            e.printStackTrace();
            return -1;
        }
    }

    //切cover图的方法
    public static int makeCoverPic(String oriPicPath, String coverPicPath, int isShuiyin) {
        try {
            //拿图片长宽
            File oriFile = new File(oriPicPath);
            BufferedImage image = null;
            image = ImageIO.read(oriFile);
            int height = image.getHeight();
            int width = image.getWidth();
            if (width >= 210) {
                resizeImage(210, oriPicPath, coverPicPath);
                gifPicCut(coverPicPath, coverPicPath);

            } else {
                copyImage(oriPicPath, coverPicPath);
                gifPicCut(coverPicPath, coverPicPath);
            }
            File coverFile = new File(coverPicPath);
            image = ImageIO.read(coverFile);
            height = image.getHeight();
            width = image.getWidth();
            if (height >= 160 && width >= 200 && isShuiyin == 1) addImgText(coverPicPath);
            return 1;
        } catch (Exception e) {
            System.out.println("Exception oriPicPath="+oriPicPath+",coverPicPath="+coverPicPath);
            e.printStackTrace();
            return -1;
        }
    }

    //切scolll图的方法
    public static int makeScrollPic(String oriPicPath, String scrollPicPath) {
        try {
            //拿图片长宽
            File oriFile = new File(oriPicPath);
            BufferedImage image = null;
            image = ImageIO.read(oriFile);
            int height = image.getHeight();
            int width = image.getWidth();
            if (height >= 158 || width >= 240) {
                resizeImage(220, 140, oriPicPath, scrollPicPath);
                gifPicCut(scrollPicPath, scrollPicPath);
            } else {
                copyImage(oriPicPath, scrollPicPath);
                gifPicCut(scrollPicPath, scrollPicPath);
            }
            return 1;
        } catch (Exception e) {
            System.out.println("Exception oriPicPath="+oriPicPath+",scrollPicPath="+scrollPicPath);
            e.printStackTrace();
            return -1;
        }
    }

    //给帖子压图的方法
    public static int makeTieZiPic(String tieziFilePath) {
        try {
            //拿图片长宽
            File oriFile = new File(tieziFilePath);
            BufferedImage image = null;
            image = ImageIO.read(oriFile);
            int height = image.getHeight();
            int width = image.getWidth();
            if (height >= 450 || width >= 100) {
                resizeImage(450, 100, tieziFilePath, tieziFilePath);
            } else {
                return 1;
            }
            return 1;
        } catch (Exception e) {
            System.out.println("Exception tieziFilePath="+tieziFilePath);
            e.printStackTrace();
            return -1;
        }
    }

    /*
     * 从队列获取不立刻要压制的图片地址
     * @param   oriImgPath:原图片地址
     * @param   type：哪个功能上传的图片
     * @param   isshuiyin：打不打水印
     * 返回值：1，成功了，-1，图片处理失败，-2，传进来的值不全
     */
    public static int picPutInOtherPath(String oriImgPath, int type, int isShuiyin) {
        String fileName = oriImgPath.substring(oriImgPath.lastIndexOf("/"));
        String oriImgSrc = oriImgPath.substring(0, oriImgPath.lastIndexOf("/"));
        String middleFilePath = oriImgSrc + "/middle/" + fileName;
        String coverFilePath = oriImgSrc + "/cover/" + fileName;
        String smallFilePath = oriImgSrc + "/small/" + fileName;
        String bigFilePath = oriImgSrc + "/big/" + fileName;
        String scrollFilePath = oriImgSrc + "/scroll/" + fileName;
        String detailFilePath = oriImgSrc + "/detail/" + fileName;
        int coverOk = makeCoverPic(oriImgPath, coverFilePath, isShuiyin);
        int scollOk = makeScrollPic(oriImgPath, scrollFilePath);
        //如果是相册上传，需要压小图和大图
        if (type == 1) {
            int bigOk = makeBigPic(oriImgPath, bigFilePath, isShuiyin);
            int smallOk = makeSmallPic(oriImgPath, smallFilePath);
            if (coverOk == 1 && scollOk == 1 && bigOk == 1 && smallOk == 1) return 1;
            else return -1;
        }
        return 0;
    }

    /*
     * 图片目录检查及创建
     * @param oriImgSrc 原始图片路径 ,此方法在上传任何一张图片时调用
     */
    public static void dirCheckAndMake(String oriImgSrc) {
        //通过原图路径获取各个文件夹路径
        //oriImgSrc = oriImgSrc.substring(0, oriImgSrc.lastIndexOf("/"));
        String middleFilePath = oriImgSrc + "/middle/";
        String coverFilePath = oriImgSrc + "/cover/";
        String smallFilePath = oriImgSrc + "/small/";
        String bigFilePath = oriImgSrc + "/big/";
        String scrollFilePath = oriImgSrc + "/scroll/";
        String detailFilePath = oriImgSrc + "/detail/";
        File oriFile = new File(oriImgSrc);
        if (!oriFile.exists()) oriFile.mkdirs();
        File smallFile = new File(smallFilePath);
        if (!smallFile.exists()) smallFile.mkdirs();
        File middleFile = new File(middleFilePath);
        if (!middleFile.exists()) middleFile.mkdirs();
        File coverFile = new File(coverFilePath);
        if (!coverFile.exists()) coverFile.mkdirs();
        File bigFile = new File(bigFilePath);
        if (!bigFile.exists()) bigFile.mkdirs();
        File scrollFile = new File(scrollFilePath);
        if (!scrollFile.exists()) scrollFile.mkdirs();
        File detailFile = new File(detailFilePath);
        if (!detailFile.exists()) detailFile.mkdirs();

    }

    /*
     * 上传图片处理
     * @param oriImgSrc 原始图片路径
     * @param file_upload 上传的图片
     * @param type 1：相册,（相册需要立刻压出中图、详细图）,2，帖子
     * @param isShuiyin:0:不打水印，1：打水印
     * 返回情况：-1：图片处理出问题（压缩、打水印、分发），-2，这个图片格式不在可支持格式内,-3,放入队列失败  ,-4,传入的GIF图片太大了
     */
    public static int picPutInUsefulPath(String oriImgSrc, CommonsMultipartFile file_upload, int type, int isShuiyin) throws Exception {
        String fileName = oriImgSrc.substring(oriImgSrc.lastIndexOf("/") + 1);
        oriImgSrc = oriImgSrc.substring(0, oriImgSrc.lastIndexOf("/"));
        //六种图片路径+原图路径
        String middleFilePath = oriImgSrc + "/middle/" + fileName;
        String coverFilePath = oriImgSrc + "/cover/" + fileName;
        String smallFilePath = oriImgSrc + "/small/" + fileName;
        String bigFilePath = oriImgSrc + "/big/" + fileName;
        String scrollFilePath = oriImgSrc + "/scroll/" + fileName;
        String detailFilePath = oriImgSrc + "/detail/" + fileName;
        String oriFilePath = oriImgSrc + "/" + fileName;
        File oriPicFile = new File(oriFilePath);
        //如果是相册上传
        if (type == 1) {
            dirCheckAndMake(oriImgSrc);
            file_upload.getFileItem().write(oriPicFile);
           makeOriPic(oriFilePath, isShuiyin);
            int bigOk = makeBigPic(oriFilePath, bigFilePath, isShuiyin);
            int smallOk = makeSmallPic(oriFilePath, smallFilePath);
            int coverOk = makeCoverPic(oriFilePath, coverFilePath, isShuiyin);
            int isToBig = makeDetailPic(oriFilePath, detailFilePath, isShuiyin);
            if (isToBig == -2) return -4;
            makeMiddlePic(oriFilePath, middleFilePath);
            makeScrollPic(oriFilePath, scrollFilePath);
            if (bigOk != 1 || smallOk != 1 || coverOk != 1) return -6;
            return 1;
        } else {
            File dirFile = new File(oriImgSrc);
            dirFile.mkdirs();
            file_upload.getFileItem().write(oriPicFile);
            return makeTieZiPic(oriFilePath);
        }
    }

    public static void main(String[] args) {
        try {
            System.out.println("开1始");
            long startTime = System.currentTimeMillis();
            //        	resizeImage(320,460,"z:\\a.gif","z:\\6.gif");
            //        	resizeImage(200,"/home/hdkj/hdkj-tomcat-6.0.20/webapps/pcenter/images/jiangyin/test.jpg","/home/hdkj/hdkj-tomcat-6.0.20/webapps/pcenter/images/jiangyin/test_java_200.jpg");
            //        	addImgText("/home/hdkj/hdkj-tomcat-6.0.20/webapps/pcenter/images/jiangyin/nanjing2014.jpg","/home/hdkj/hdkj-tomcat-6.0.20/webapps/pcenter/images/jiangyin/nanjing2014_shuiyin.jpg");
            //      	cutImage("z:\\1.bmp","z:\\2.bmp",0,0,300,300);
            //        	addImgShuiyin("z:\\3.gif","z:\\1.jpg","z:\\a.gif");
            addImgText("z:\\2.bmp");
            //        	copyImage("z:\\txt.png", "z:\\a\\txt.png");
            //        	overturnAndOverWritePic("/home/hdkj/hdkj-tomcat-6.0.20/webapps/pcenter/images/jiangyin/nanjing2014.jpg",0,90.0);
            //        	overturnAndCreateNewPic("/home/hdkj/hdkj-tomcat-6.0.20/webapps/pcenter/images/jiangyin/nanjing2014.jpg", "/home/hdkj/hdkj-tomcat-6.0.20/webapps/pcenter/images/jiangyin/rotatetest.jpg", 0, 90.0);
            //        	overturnAndOverWritePic("C:\\Documents and Settings\\All Users\\Documents\\My Pictures\\示例图片\\Sunset.jpg",0,90.0);
            //        	overturnAndCreateNewPic("z:\\1.gif", "z:\\7.gif", 0, 90.0);
            //            gifPicCut("z:\\a.gif", "z:\\3.jpg");
            //        	cutImage("z:\\3.gif", "z:\\4.gif", 0, 0, 160, 160);
            String test = "abb/sse/gges/deges.jpg";
            test = test.substring(test.lastIndexOf("/") + 1, test.lastIndexOf("."));
            long endTime = System.currentTimeMillis();
            System.out.println(test);
            long useTime = endTime - startTime;
            //picPutInOtherPath(testMap);
            System.out.println(useTime);
            System.out.println("结束");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
