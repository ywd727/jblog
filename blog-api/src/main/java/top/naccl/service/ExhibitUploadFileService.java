package top.naccl.service;

import top.naccl.entity.ExhibitUploadFile;
import com.baomidou.mybatisplus.extension.service.IService;
import top.naccl.model.vo.Result;

import java.util.List;

/**
 * <p>
 * 上传文件表 服务类
 * </p>
 *
 * @author jxx
 * @since 2022-09-02
 */
public interface ExhibitUploadFileService extends IService<ExhibitUploadFile> {

    List<ExhibitUploadFile> paginate(Integer startPage, Integer pageSize, ExhibitUploadFile exhibitUploadFile);

    Boolean checkDuplicates(String uploadUrl);

    Boolean saveExhibitUploadFile(ExhibitUploadFile exhibitUploadFile);

    Boolean deleteById(String id);

    ExhibitUploadFile queryById(String id);
}
