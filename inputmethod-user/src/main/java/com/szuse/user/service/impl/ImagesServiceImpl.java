package com.example.sedemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.sedemo.entity.Images;
import com.example.sedemo.mapper.ImagesMapper;
import com.example.sedemo.result.Result;
import com.example.sedemo.service.ICasesService;
import com.example.sedemo.service.IImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author LiuYe
 * @since 2023-02-28
 */
@Service
public class ImagesServiceImpl extends ServiceImpl<ImagesMapper, Images> implements IImagesService {

    @Value("${inputMethod.path}")
    private String path;

    @Autowired
    private ICasesService casesService;

    @Override
    public Result upload(MultipartFile file,Long cid) throws IOException {
        if (casesService.getById(cid) == null){
            return Result.error().msg("无此案例");
        }
        //防止重名
        String originalFilename = file.getOriginalFilename();
        String hzName = originalFilename.substring(originalFilename.lastIndexOf("."));
        String fileName = UUID.randomUUID().toString() + hzName;

        //获取服务器中photo目录的路径
//        String imagePath = ImagePathUtils.getPath() + File.separator + "photo";
        String imagePath = path;
        //加入到jar包同级目录
        File newFile = new File(imagePath);
        if(!newFile.exists()){
            newFile.mkdirs();
        }
        String finalPath = imagePath + File.separator + fileName;
        System.out.println(finalPath);
        //实现上传功能
        file.transferTo(new File(finalPath));

        //存入数据库
        Images images = new Images();
        images.setPath(finalPath);
        images.setCid(cid);
        save(images);

        return Result.success().msg("图片上传成功");
    }

    @Override
    public Result deleteImgById(Long iid){
        Images image = this.getById(iid);
        if (Objects.isNull(image)){
            return Result.error().msg("未查询到对应图片");
        }
        this.removeById(iid);
        return Result.success().msg("删除图片成功");
    }

    @Override
    public Result getCaseImg(Long cid) {
        //初始化分页信息
        Page<Images> imagesPage = new Page<>(1, 10);
        //构造查询条件
        LambdaQueryWrapper<Images> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(cid != null, Images::getCid, cid);
        this.page(imagesPage,queryWrapper);
        if (Objects.isNull(imagesPage)){
            return Result.error().msg("无此案例图片");
        }
        return Result.success().msg("获取案例图片成功").data(imagesPage);
    }
}
