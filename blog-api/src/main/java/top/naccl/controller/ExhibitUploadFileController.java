package top.naccl.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import top.naccl.entity.ExhibitUploadFile;
import top.naccl.model.vo.Result;
import top.naccl.service.ExhibitUploadFileService;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 上传文件表 前端控制器
 * </p>
 *
 * @author jxx
 * @since 2022-09-02
 */
@RestController
@RequestMapping("/upload/exhibit-upload-file")
@Api(tags = "upload-显示上传文件的详情信息")
public class ExhibitUploadFileController {

    @Resource
    private ExhibitUploadFileService exhibitUploadFileService;

    /**
     * 进行分页查询的
     * @param startPage
     * @param pageSize
     * @param exhibitUploadFile
     * @return
     */
    @PostMapping("/paginate")
    @ApiOperation("按照条件获取上传文件的分页列表")
    public Result paginate(@RequestParam(defaultValue = "0") Integer startPage,
                           @RequestParam(defaultValue = "9") Integer pageSize,
                           @RequestBody ExhibitUploadFile exhibitUploadFile){

        return Result.ok("msg", exhibitUploadFileService.paginate(startPage, pageSize, exhibitUploadFile));
    }

    /**
     * 保存上传信息
     * @return
     */
    @PostMapping("/save")
    @ApiOperation("保存上传信息")
    public Result save(@RequestBody ExhibitUploadFile exhibitUploadFile) {

        return Result.ok(exhibitUploadFileService.saveExhibitUploadFile(exhibitUploadFile));
    }

    /**
     * 根据id进行删除
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    @ApiOperation("根据id删除")
    public Result delete(@PathVariable("id") String id) {

        return Result.ok(exhibitUploadFileService.deleteById(id));
    }

    /**
     * 根据id进行查询
     * @param id
     * @return
     */
    @GetMapping("/queryById/{id}")
    @ApiOperation("根据id进行查询")
    public Result queryById(@PathVariable("id") String id) {

        return Result.ok(exhibitUploadFileService.queryById(id));
    }

    /**
     * 查询保存的文件的名称在数据库中是否存在
     * @param uploadUrl 查询的条件（文件名称）
     * @return
     */
    @GetMapping("/checkDuplicates/{uploadUrl}")
    @ApiOperation("查询保存文件名称在数据库中是否存在")
    public Boolean checkDuplicates(@PathVariable("uploadUrl") String uploadUrl) {

        return exhibitUploadFileService.checkDuplicates(uploadUrl);
    }

}
