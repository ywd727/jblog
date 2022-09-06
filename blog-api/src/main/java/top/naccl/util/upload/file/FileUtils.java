package top.naccl.util.upload.file;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @ProjectName: blog
 * @Package: top.naccl.util.upload.file
 * @ClassName: FIleUtils
 * @Author: 简鑫鑫
 * @Description:
 * @Date: 2022/8/30 15:06
 * @Version: 1.0
 */
@Component
public class FileUtils {
    /**
     * 上传文件
     * @param file
     * @return 返回文件路径（以相对路径放回）
     */
    public static String uploadFile(MultipartFile file, String filePath) {
        if(file.isEmpty()) {
            return "";
        }
        // 获取原文件名
        String originFileName = file.getOriginalFilename();
        // 我们通过UUID 来重新重组文件名
        String uid = UUID.randomUUID().toString();
        originFileName = uid + originFileName;
        String returnPath = filePath + originFileName;
        File newFile = new File(returnPath);
        if(newFile.getParentFile() != null && !newFile.getParentFile().exists()) {
            System.out.println("创建目录ing");
            // 上面的 newFile.getParentFile() 已经保证了不为null.
            if(newFile.getParentFile().mkdirs()) {
                System.out.println("创建目录成功");
            }else {
                System.out.println("创建目录失败");
                return "";
            }
        }
        try {
            //开始上传文件
            file.transferTo(newFile);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
        return returnPath;
    }
}
