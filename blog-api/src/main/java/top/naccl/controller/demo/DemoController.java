package top.naccl.controller.demo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.naccl.model.vo.Result;
import top.naccl.util.upload.UploadUtils;
import top.naccl.util.upload.channel.LocalChannel;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @ProjectName: blog
 * @Package: top.naccl.controller
 * @ClassName: DemoController
 * @Author: 简鑫鑫
 * @Description:   用于测试的controller
 * @Date: 2022/9/1 10:28
 * @Version: 1.0
 */
@RestController
@RequestMapping("/demo")
@Api(tags = "aaa-demo-测试")
public class DemoController {

    @Resource
    private LocalChannel localChannel;

    @PostMapping("/demo/uploadImg")
    @ApiOperation("测试文件上传的方法")
    public Result uploadImg(MultipartFile multipartFile) throws Exception {

        UploadUtils.ImageResource imageResource =
                new UploadUtils.ImageResource(multipartFile.getBytes(), multipartFile.getOriginalFilename());
        String upload = localChannel.upload(imageResource);
        return Result.ok("上传成功,上传的路径为 ---> ", upload);
    }
}
