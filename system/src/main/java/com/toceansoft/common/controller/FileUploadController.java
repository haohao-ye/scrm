package com.toceansoft.common.controller;

import cn.hutool.core.io.FileUtil;
import com.toceansoft.common.util.R;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/common/file")
public class FileUploadController {
    @RequestMapping()
    public R upload(@RequestParam(name="file")MultipartFile multipartFile){
        //获得原文件后缀
        String originName=multipartFile.getOriginalFilename();
        String suffix=FileUtil.getSuffix(originName);

        //构建一个唯一的新文件名
        String fileName= UUID.randomUUID().toString()+"."+suffix;


        String saveDirPath="D:\\test\\publicfile";
        File saveDir=new File(saveDirPath);
        if(saveDir.exists()){
            saveDir.mkdirs();
        }
        File saveFile=new File(saveDir,fileName);//保存的绝对路径

        try {
            multipartFile.transferTo(saveFile);//保存到指定路径
            Map<String,Object> data=new HashMap<>();
            data.put("fileName",fileName);
            data.put("filePath","/upload"+fileName);
            return  R.ok(20000,"chenggong");

        } catch (IOException e) {
            return R.fail(50000,e.getMessage());
        }

    }

//    @RequestMapping("/download")
//    public
}
