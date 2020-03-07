package com.tim.ishou.site.po;

import java.util.Date;

public class SiteHome {

  private String id;

  private Date createTime;

  private String creatorId;

  private Date modifiedTime;

  private String modifierId;

  private String name;

  private String url;

  private String remark;

  private String tag;

  private Integer sortNum;

  private Integer visitCount;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id == null ? null : id.trim();
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public String getCreatorId() {
    return creatorId;
  }

  public void setCreatorId(String creatorId) {
    this.creatorId = creatorId == null ? null : creatorId.trim();
  }

  public Date getModifiedTime() {
    return modifiedTime;
  }

  public void setModifiedTime(Date modifiedTime) {
    this.modifiedTime = modifiedTime;
  }

  public String getModifierId() {
    return modifierId;
  }

  public void setModifierId(String modifierId) {
    this.modifierId = modifierId == null ? null : modifierId.trim();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name == null ? null : name.trim();
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url == null ? null : url.trim();
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark == null ? null : remark.trim();
  }

  public String getTag() {
    return tag;
  }

  public void setTag(String tag) {
    this.tag = tag == null ? null : tag.trim();
  }

  public Integer getSortNum() {
    return sortNum;
  }

  public void setSortNum(Integer sortNum) {
    this.sortNum = sortNum;
  }

  public Integer getVisitCount() {
    return visitCount;
  }

  public void setVisitCount(Integer visitCount) {
    this.visitCount = visitCount;
  }
}