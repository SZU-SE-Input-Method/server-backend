package com.example.sedemo.controller;


import com.example.sedemo.result.Result;
import com.example.sedemo.service.IImagesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author LiuYe
 * @since 2023-02-28
 */
@Slf4j
@RestController
@RequestMapping("/images")
public class ImagesController {

    @Autowired
    private IImagesService iImagesService;
    //上传图片
    @PostMapping("/upload")
    public Result uploadImg(@RequestParam("file") MultipartFile file,Long cid) throws IOException {
        if (file.isEmpty()){
            return Result.error().msg("文件为空！");
        }

        return iImagesService.upload(file,cid);
    }

    @DeleteMapping("/{iid}")
    public Result deleteImg(@PathVariable Long iid){
        if (iid == null){
            return Result.error().msg("图片id不能为空");
        }
        return iImagesService.deleteImgById(iid);
    }

    @GetMapping("/cases/{cid}")
    public Result getCaseImg(@PathVariable Long cid){
        if (cid == null){
            return Result.error().msg("案例id不能为空");
        }
        return iImagesService.getCaseImg(cid);
    }

}
