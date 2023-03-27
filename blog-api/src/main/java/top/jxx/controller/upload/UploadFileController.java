package top.jxx.controller.upload;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.jxx.entity.ExhibitUploadFile;
import top.jxx.model.vo.Result;
import top.jxx.service.ExhibitUploadFileService;
import top.jxx.util.upload.UploadUtils;
import top.jxx.util.upload.channel.LocalChannel;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Map;

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
@RequestMapping("/admin")
@Api(tags = "upload-文件的上传下载接口")
public class UploadFileController {

    @Resource
    private LocalChannel localChannel;

    @Resource
    private ExhibitUploadFileService exhibitUploadFileService;

    @PostMapping("/upload/file")
    @ApiOperation("用来上传文件的")
    public Result uploadFile(@RequestBody MultipartFile multipartFile,
                             @RequestParam("fileName") String fileName) throws Exception {

        System.out.println("multipartFile = " + multipartFile);
        System.out.println("multipartFile.isEmpty() = " + multipartFile.isEmpty());
//        System.out.println("multipartFile.getOriginalFilename() = " + multipartFile.getContentType());
        UploadUtils.ImageResource imageResource =
                new UploadUtils.ImageResource(multipartFile.getBytes(),
                        multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf(".")),
                        multipartFile.getOriginalFilename(),
                        multipartFile.getContentType());

        return localChannel.upload(imageResource, fileName);
    }

    @GetMapping("/download/{url}")
    @ApiOperation("文件的下载（根据文件的网络地址进行下载）")
    public void download(@PathVariable("url") String url, HttpServletResponse response) throws Exception {

        FileInputStream in = new FileInputStream(url);
        OutputStream out = response.getOutputStream();
        response.reset();
        IOUtils.copy(in, out);
        in.close();
        String utf8 = StandardCharsets.UTF_8.name();
        response.setCharacterEncoding(utf8);
        ExhibitUploadFile infoByUploadUrl = exhibitUploadFileService.getInfoByUploadUrl(url);
        String filename = URLEncoder.encode(infoByUploadUrl.getUploadOriginalFileName(), utf8);
        String attachment = String.format("attachment;filename=%s", filename);
        response.setContentType(MediaType.parseMediaType(infoByUploadUrl.getUploadFileType()).toString());
        response.setCharacterEncoding(utf8);
        response.setHeader("Content-Disposition", attachment);
    }

    @ApiOperation("文件批量下载")
    @PostMapping({"/batchDownload"})
    public void batchDownload(@RequestBody Collection<String> ids, HttpServletResponse response) throws Exception {
        /*String utf8 = StandardCharsets.UTF_8.name();
        String attachment = String.format("attachment;filename=%s", "batchFiles.zip");
        response.reset();
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding(utf8);
        response.setHeader("Content-Disposition", attachment);
        ZipOutputStream zipOut = new ZipOutputStream(response.getOutputStream());
        Collection<UploadFileDM> uploadFiles = ((ModelFilter)UploadFileDM.filter().conditions().lambda().in(UploadFileDM::getId, ids).next()).list();
        if (uploadFiles.isEmpty()) {
            Errors.error().message("未找到合适的记录").raise();
        }

        Map<String, Integer> fileCounter = Maps.newHashMap();
        uploadFiles.forEach((upload) -> {
            try {
                String filename = this.processFilename(fileCounter, upload.getUploadFilename());
                zipOut.putNextEntry(new ZipEntry(filename));
                FileInputStream in = new FileInputStream(upload.getTargetFilepath());
                IOUtils.copy(in, zipOut);
                in.close();
            } catch (Exception var6) {
                Errors.error().cause(var6).raise();
            }

        });
        zipOut.flush();
        zipOut.close();*/
    }

    private String processFilename(Map<String, Integer> fileCounter, String filename) {
        /*if (!fileCounter.containsKey(filename)) {
            fileCounter.put(filename, 1);
            return new String(filename);
        } else {
            Integer count = (Integer)fileCounter.get(filename);
            Integer var4 = count;
            count = count + 1;
            fileCounter.put(filename, var4);
            return FilenameUtils.removeExtension(filename) + "_" + count + "." + FilenameUtils.getExtension(filename);
        }*/
        return "";
    }

//    @PostMapping("/uploadImg")
//    @ApiOperation("用来上传图片的（）")
    public Result uploadImg(@RequestBody MultipartFile multipartFile) throws Exception {

        UploadUtils.ImageResource imageResource =
                new UploadUtils.ImageResource(multipartFile.getBytes(), multipartFile.getOriginalFilename());
        String upload = localChannel.upload(imageResource);
        return Result.ok("上传成功,上传的路径为 ---> ", upload);
    }

}
