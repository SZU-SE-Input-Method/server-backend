package com.example.sedemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.sedemo.entity.Images;
import com.example.sedemo.result.Result;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author LiuYe
 * @since 2023-02-28
 */
public interface IImagesService extends IService<Images> {
    public Result upload(MultipartFile file,Long cid) throws IOException;
    public Result deleteImgById(Long iid);
    public Result getCaseImg(Long iid);
}
