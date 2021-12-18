package com.wang.controller.download;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xiaowang
 * @time 2021-12-13 21:21
 **/

@RestController
@RequestMapping("/download")
@Slf4j
public class DownloadController {


    /**
     * @param fileName     想要下载的文件的路径
     * @param response
     * @功能描述 下载文件:
     */
    @RequestMapping("/download")
    public void download(String fileName, HttpServletResponse response) {
        try {
            // path是指想要下载的文件的路径
            ClassPathResource pathResource = new ClassPathResource("/resource/file/" + fileName);
            File file = pathResource.getFile();
            log.info("文件路径：{}",pathResource.getPath());
            log.info("文件名称：{}",pathResource.getFilename());
            log.info("文件描述：{}",pathResource.getDescription());
            log.info(file.getPath());
            // 获取文件名
            String filename = file.getName();
            // 获取文件后缀名
            String ext = filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();
            log.info("文件后缀名：" + ext);

            // 将文件写入输入流
            FileInputStream fileInputStream = new FileInputStream(file);
            InputStream fis = new BufferedInputStream(fileInputStream);
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();

            // 清空response
            response.reset();
            // 设置response的Header
            response.setCharacterEncoding("UTF-8");
            //Content-Disposition的作用：告知浏览器以何种方式显示响应返回的文件，用浏览器打开还是以附件的形式下载到本地保存
            //attachment表示以附件方式下载   inline表示在线打开   "Content-Disposition: inline; filename=文件名.mp3"
            // filename表示文件的默认名称，因为网络传输只支持URL编码的相关支付，因此需要将文件名URL编码后进行传输,前端收到后需要反编码才能获取到真正的名称
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));
            // 告知浏览器文件的大小
            response.addHeader("Content-Length", "" + file.length());
            OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            outputStream.write(buffer);
            outputStream.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    /**
     * @param path     指想要下载的文件的路径
     * @param response
     * @功能描述 下载文件:将输入流中的数据循环写入到响应输出流中，而不是一次性读取到内存
     */
    @RequestMapping("/downloadLocal")
    public void downloadLocal(String path, HttpServletResponse response) throws IOException {
        // 读到流中
        InputStream inputStream = new FileInputStream(path);// 文件的存放路径
        response.reset();
        response.setContentType("application/octet-stream");
        String filename = new File(path).getName();
        response.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(filename, "UTF-8"));
        ServletOutputStream outputStream = response.getOutputStream();
        byte[] b = new byte[1024];
        int len;
        //从输入流中读取一定数量的字节，并将其存储在缓冲区字节数组中，读到末尾返回-1
        while ((len = inputStream.read(b)) > 0) {
            outputStream.write(b, 0, len);
        }
        inputStream.close();
    }


    /**
     * @param path       下载后的文件路径和名称
     * @param netAddress 文件所在网络地址
     * @功能描述 网络文件下载到服务器本地
     */
    @RequestMapping("/netDownloadLocal")
    public void downloadNet(String netAddress, String path) throws IOException {
        URL url = new URL(netAddress);
        URLConnection conn = url.openConnection();
        InputStream inputStream = conn.getInputStream();
        FileOutputStream fileOutputStream = new FileOutputStream(path);

        int bytesum = 0;
        int byteread;
        byte[] buffer = new byte[1024];
        while ((byteread = inputStream.read(buffer)) != -1) {
            bytesum += byteread;
            System.out.println(bytesum);
            fileOutputStream.write(buffer, 0, byteread);
        }
        fileOutputStream.close();
    }

    /**
     * @param netAddress
     * @param filename
     * @param isOnLine
     * @param response
     * @功能描述 网络文件获取到服务器后，经服务器处理后响应给前端
     */
    @RequestMapping("/netDownLoadNet")
    public void netDownLoadNet(String netAddress, String filename, boolean isOnLine, HttpServletResponse response) throws Exception {

        URL url = new URL(netAddress);
        URLConnection conn = url.openConnection();
        InputStream inputStream = conn.getInputStream();

        response.reset();
        response.setContentType(conn.getContentType());
        if (isOnLine) {
            // 在线打开方式 文件名应该编码成UTF-8
            response.setHeader("Content-Disposition", "inline; filename=" + URLEncoder.encode(filename, "UTF-8"));
        } else {
            //纯下载方式 文件名应该编码成UTF-8
            response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(filename, "UTF-8"));
        }

        byte[] buffer = new byte[1024];
        int len;
        OutputStream outputStream = response.getOutputStream();
        while ((len = inputStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, len);
        }
        inputStream.close();
    }


    /**
     * 文件下载
     * @param path
     * @return
     */
    public static ResponseEntity<Object> createResponseEntity(String path) {
        //1,构造文件对象
        File file=new File("", path);
        if(file.exists()) {
            //将下载的文件，封装byte[]
            byte[] bytes=null;
            try {
                bytes = FileUtil.readBytes(file);
            } catch (Exception e) {
                e.printStackTrace();
            }

            //创建封装响应头信息的对象
            HttpHeaders header=new HttpHeaders();
            //封装响应内容类型(APPLICATION_OCTET_STREAM 响应的内容不限定)
            header.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            //设置下载的文件的名称
//			header.setContentDispositionFormData("attachment", "123.jpg");
            //创建ResponseEntity对象
            ResponseEntity<Object> entity=
                    new ResponseEntity<Object>(bytes, header, HttpStatus.CREATED);
            return entity;
        }
        return null;
    }


    /**
     * 文件上传
     */
    @RequestMapping("uploadFile")
    public Map<String,Object> uploadFile(MultipartFile mf) {
        //1,得到文件名
        String oldName = mf.getOriginalFilename();
        //2,根据文件名生成新的文件名
        String newName= oldName;
        //3,得到当前日期的字符串
        String dirName= DateUtil.format(new Date(), "yyyy-MM-dd");
        //4,构造文件夹
        File dirFile=new File("",dirName);
        //5,判断当前文件夹是否存在
        if(!dirFile.exists()) {
            dirFile.mkdirs();//创建文件夹
        }
        //6,构造文件对象
        File file=new File(dirFile, newName+"_temp");
        //7,把mf里面的图片信息写入file
        try {
            mf.transferTo(file);
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("path", dirName+"/"+newName+"_temp");
        return map;
    }

}
