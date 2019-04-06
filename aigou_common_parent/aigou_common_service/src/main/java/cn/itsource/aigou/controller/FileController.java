package cn.itsource.aigou.controller;

import cn.itsource.aigou.commom.AjaxResult;
import cn.itsource.aigou.util.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
public class FileController {

    //文件上传
    @RequestMapping(value = "/file",method = RequestMethod.POST)
    public AjaxResult upload(@RequestBody MultipartFile file) {
        //byte[] file,String extName
        try {
            byte[] bytes = file.getBytes();
            String originalFilename = file.getOriginalFilename();
            String extName = FilenameUtils.getExtension(originalFilename);
            String url = FileUtils.upload(bytes, extName);
            return AjaxResult.me().setMsg("上传成功").setObject(url);
        } catch (IOException e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMsg("上传失败");
        }
    }

    //文件删除
    @RequestMapping(value = "/file",method = RequestMethod.DELETE)
    public AjaxResult delete(@RequestParam String filePath) {
        //String groupName,String fileName  /group1/M00/00/01/rBAQjFyjlI2ADP6nAAFok50KvqY066.jpg
        //截取groupName
        String tempStr1 = filePath.substring(1);
        String groupName = tempStr1.substring(0, tempStr1.indexOf("/"));
        System.out.println(groupName);

        //截取fileName
        String fileName = tempStr1.substring(tempStr1.indexOf("/") + 1);
        System.out.println(fileName);

        //删除
        int deleteResult = FileUtils.delete(groupName, fileName);
        //返回删除结果
        if(deleteResult==0){
            return AjaxResult.me().setMsg("删除成功");
        }
        return AjaxResult.me().setSuccess(false).setMsg("删除失败");
    }

    //文件下载
    @RequestMapping(value = "/file",method = RequestMethod.GET)
    public void download(@RequestParam String filePath, HttpServletResponse response){
        //String groupName,String fileName  /group1/M00/00/01/rBAQjFyjlI2ADP6nAAFok50KvqY066.jpg
        //截取groupName
        String tempStr1 = filePath.substring(1);
        String groupName = tempStr1.substring(0, tempStr1.indexOf("/"));
        System.out.println(groupName);

        //截取fileName
        String fileName = tempStr1.substring(tempStr1.indexOf("/") + 1);
        System.out.println(fileName);

        InputStream inputStream = null;
        ServletOutputStream outputStream = null;

        try {
            //获取下载流
            byte[] bytes = FileUtils.download(groupName, fileName);
            inputStream = new ByteArrayInputStream(bytes);

            //获取响应流
            outputStream = response.getOutputStream();

            //响应
            IOUtils.copy(inputStream, outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
                //关流
                if(inputStream!=null){
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if(outputStream!=null){
                    try {
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        }

    }

    public static void main(String[] args) {
        String url="/group1/M00/00/01/rBAQjFyjlI2ADP6nAAFok50KvqY066.jpg";
        //截取groupName
        String tempStr1 = url.substring(1);
        String groupName = tempStr1.substring(0, tempStr1.indexOf("/"));

        //截取fileName
        String fileName = tempStr1.substring(tempStr1.indexOf("/") + 1);

        System.out.println(groupName);
        System.out.println(fileName);
    }

}
