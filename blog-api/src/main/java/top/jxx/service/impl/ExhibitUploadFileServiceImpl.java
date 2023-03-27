package top.jxx.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.jxx.entity.ExhibitUploadFile;
import top.jxx.mapper.ExhibitUploadFileMapper;
import top.jxx.service.ExhibitUploadFileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 上传文件表 服务实现类
 * </p>
 *
 * @author jxx
 * @since 2022-09-02
 */
@Service
public class ExhibitUploadFileServiceImpl extends ServiceImpl<ExhibitUploadFileMapper, ExhibitUploadFile> implements ExhibitUploadFileService {

    @Override
    public List<ExhibitUploadFile> paginate(Integer startPage, Integer pageSize, ExhibitUploadFile exhibitUploadFile) {

        return lambdaQuery()
                .like(ExhibitUploadFile::getUploadFileName, exhibitUploadFile.getUploadFileName())
                .page(new Page<>(startPage, pageSize))
                .getRecords();

    }

    @Override
    public Boolean checkDuplicates(String uploadUrl) {

        return lambdaQuery()
                .eq(ExhibitUploadFile::getUploadUrl, uploadUrl)
                .count() > 0;

    }

    @Override
    public Boolean saveExhibitUploadFile(ExhibitUploadFile exhibitUploadFile) {

        return save(exhibitUploadFile);
    }

    @Override
    public Boolean deleteById(String id) {

        return removeById(id);
    }

    @Override
    public ExhibitUploadFile queryById(String id) {

        return lambdaQuery()
                    .eq(ExhibitUploadFile::getId, id)
                .one();
    }

    @Override
    public ExhibitUploadFile getInfoByUploadUrl(String url) {

        return lambdaQuery()
                    .eq(ExhibitUploadFile::getUploadUrl, url)
                .one();

    }
}
