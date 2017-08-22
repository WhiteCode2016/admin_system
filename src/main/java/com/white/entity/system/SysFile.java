package com.white.entity.system;

import com.white.entity.common.DataEntity;

/**
 * 文件管理类
 */
public class SysFile extends DataEntity {

    // 文件原名
    private String originalFileName;
    // 文件后缀
    private String suffix;
    // 文件大小
    private long size;
    // 文件类型
    private String contentType;
    // 用户Id
    private String userId;

    public String getOriginalFileName() {
        return originalFileName;
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
