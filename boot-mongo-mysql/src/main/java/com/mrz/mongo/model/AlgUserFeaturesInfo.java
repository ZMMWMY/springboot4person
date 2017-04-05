package com.mrz.mongo.model;


import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author:ziye_huang
 * @date:2016年1月15日
 *
 */
public class AlgUserFeaturesInfo implements Serializable
{
	//流水id 主键
	@Id
	private Integer fid;
	private Long pid;
	private Long cid;
	//用户唯一标识, uuid
	private String userId;
	//特征值
	private String featuresBinary;
	//特征值长度
	private Integer featuresLength;
	//图片系数（个数）
	private Integer picNumber;
	//用户图片地址
	private String userPicPath;
	//平台号
	private String type;
	//子平台号
	private String subType;
	private Date createTime;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSubType() {
		return subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}

	public Integer getFid()
	{
		return fid;
	}

	public void setFid(Integer fid)
	{
		this.fid = fid;
	}

	public String getUserId()
	{
		return userId;
	}

	public void setUserId(String userId)
	{
		this.userId = userId;
	}

	public String getFeaturesBinary()
	{
		return featuresBinary;
	}

	public void setFeaturesBinary(String featuresBinary)
	{
		this.featuresBinary = featuresBinary;
	}

	public Integer getFeaturesLength()
	{
		return featuresLength;
	}

	public void setFeaturesLength(Integer featuresLength)
	{
		this.featuresLength = featuresLength;
	}

	public Integer getPicNumber()
	{
		return picNumber;
	}

	public void setPicNumber(Integer picNumber)
	{
		this.picNumber = picNumber;
	}

	public Long getPid()
	{
		return pid;
	}

	public void setPid(Long pid)
	{
		this.pid = pid;
	}

	public Long getCid()
	{
		return cid;
	}

	public void setCid(Long cid)
	{
		this.cid = cid;
	}

	public String getUserPicPath()
	{
		return userPicPath;
	}

	public void setUserPicPath(String userPicPath)
	{
		this.userPicPath = userPicPath;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		AlgUserFeaturesInfo that = (AlgUserFeaturesInfo) o;

		if (fid != null ? !fid.equals(that.fid) : that.fid != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		return fid != null ? fid.hashCode() : 0;
	}
}
