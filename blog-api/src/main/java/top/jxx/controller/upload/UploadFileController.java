package top.jxx.controller.upload;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.jxx.model.vo.Result;
import top.jxx.util.upload.UploadUtils;
import top.jxx.util.upload.channel.LocalChannel;

import javax.annotation.Resource;

/**
 * @ProjectName: blog
 * @Package: top.jxx.controller.upload
 * @ClassName: UploadFile
 * @Author: 简鑫鑫
 * @Description:
 * @Date: 2022/8/30 15:24
 * @Version: 1.0
 */
@RestController
@RequestMapping("/upload")
@Api(tags = "upload-文件的上传下载接口")
public class UploadFileController {

    @Resource
    private LocalChannel localChannel;

    @PostMapping("/upload/file")
    @ApiOperation("用来上传文件的")
    public Result uploadFile(@RequestBody MultipartFile multipartFile,
                             @RequestParam("fileName") String fileName) throws Exception {

        System.out.println("multipartFile.getOriginalFilename() = " + multipartFile.getContentType());
        UploadUtils.ImageResource imageResource =
                new UploadUtils.ImageResource(multipartFile.getBytes(),
                        multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf(".")),
                        multipartFile.getOriginalFilename());

        return localChannel.upload(imageResource, fileName);
    }

    @PostMapping("/uploadImg")
//    @ApiOperation("用来上传图片的（）")
    public Result uploadImg(@RequestBody MultipartFile multipartFile) throws Exception {

        UploadUtils.ImageResource imageResource =
                new UploadUtils.ImageResource(multipartFile.getBytes(), multipartFile.getOriginalFilename());
        String upload = localChannel.upload(imageResource);
        return Result.ok("上传成功,上传的路径为 ---> ", upload);
    }

}
