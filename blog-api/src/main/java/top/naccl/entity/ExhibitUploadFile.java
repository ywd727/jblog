package top.naccl.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 上传文件表
 * </p>
 *
 * @author jxx
 * @since 2022-09-02
 */
@Getter
@Setter
@TableName("blog_exhibit_upload_file")
@ApiModel(value = "ExhibitUploadFile对象", description = "上传文件表")
public class ExhibitUploadFile implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId("id")
    private String id;

    @ApiModelProperty("上传后返回的路径")
    @TableField("upload_url")
    private String uploadUrl;

    @ApiModelProperty("创建时间")
    @TableField("created_date")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdDate;

    @ApiModelProperty("创建人id")
    @TableField("created_by")
    private String createdBy;

    @ApiModelProperty("更新时间")
    @TableField("updated_date")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedDate;

    @ApiModelProperty("更新人id")
    @TableField("updated_by")
    private String updatedBy;

    @ApiModelProperty("逻辑删除")
    @TableField("deleted")
    private Boolean deleted;

    @ApiModelProperty("数据版本")
    @TableField("version")
    private Integer version;

    @ApiModelProperty("自定义后文件的名称")
    @TableField("upload_file_name")
    private String uploadFileName;

    @ApiModelProperty("文件的原始名称")
    @TableField("upload_original_file_name")
    private String uploadOriginalFileName;

    @Override
    public String toString() {
        return "ExhibitUploadFile{" +
                "id='" + id + '\'' +
                ", uploadUrl='" + uploadUrl + '\'' +
                ", createdDate=" + createdDate +
                ", createdBy='" + createdBy + '\'' +
                ", updatedDate=" + updatedDate +
                ", updatedBy='" + updatedBy + '\'' +
                ", deleted=" + deleted +
                ", version=" + version +
                ", uploadFileName='" + uploadFileName + '\'' +
                ", uploadOriginalFileName='" + uploadOriginalFileName + '\'' +
                '}';
    }
}
