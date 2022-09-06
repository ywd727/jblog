package top.jxx.util.upload.channel;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import top.jxx.config.properties.BlogProperties;
import top.jxx.config.properties.UploadProperties;
import top.jxx.entity.ExhibitUploadFile;
import top.jxx.model.vo.Result;
import top.jxx.service.ExhibitUploadFileService;
import top.jxx.util.upload.UploadUtils;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * 本地存储方式
 *
 * @author: Naccl
 * @date: 2022-01-23
 */
@Lazy
@Component
@Slf4j
public class LocalChannel implements FileUploadChannel {
	@Autowired
	private BlogProperties blogProperties;
	@Autowired
	private UploadProperties uploadProperties;

	@Resource
	private ExhibitUploadFileService exhibitUploadFileService;


	/**
	 * 将图片保存到本地，并返回访问本地图片的URL
	 *
	 * @param image 需要保存的图片
	 * @return 访问图片的URL
	 * @throws Exception
	 */
	@Override
	public String upload(UploadUtils.ImageResource image) throws Exception {
		File folder = new File(uploadProperties.getPath());
		if (!folder.exists()) {
			folder.mkdirs();
		}
		String fileName = UUID.randomUUID() + "." + image.getType();
		FileOutputStream fileOutputStream = new FileOutputStream(uploadProperties.getPath() + fileName);
		fileOutputStream.write(image.getData());
		fileOutputStream.close();

		return blogProperties.getApi() + "/image/" + fileName;
	}

	/**
	 *
	 * @param file
	 * @param fileName
	 * @return
	 * @throws FileNotFoundException
	 */
	public Result upload(UploadUtils.ImageResource file, String fileName) throws IOException {

		String uploadUrl = blogProperties.getApi() + "/image/" + fileName;

		//进行查重，如果数据库中存在该文件的路径名，那么就报错
		if (exhibitUploadFileService.checkDuplicates(uploadUrl)) {
			return Result.error("该文件名称已存在，请重新命名");
		}

		File folder = new File(uploadProperties.getPath());

		if (!folder.exists()) {
			folder.mkdirs();
		}

		String uploadFileName = fileName + file.getType();
		String uploadOriginalFileName = file.getOriginalFilename();
		FileOutputStream fileOutputStream = new FileOutputStream(uploadProperties.getPath() + fileName + file.getType());
		fileOutputStream.write(file.getData());
		fileOutputStream.close();

		ExhibitUploadFile exhibitUploadFile = new ExhibitUploadFile();
		exhibitUploadFile.setUploadFileName(uploadFileName);
		exhibitUploadFile.setUploadOriginalFileName(uploadOriginalFileName);
		exhibitUploadFile.setUploadUrl(uploadUrl);
		exhibitUploadFile.setCreatedDate(LocalDateTime.now());
		exhibitUploadFileService.save(exhibitUploadFile);

		return Result.ok("上传成功", uploadUrl);
	}
}
