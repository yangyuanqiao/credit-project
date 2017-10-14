/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.amass.credit.modules.estatehousing.entity.basicinfo;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.amass.credit.common.persistence.DataEntity;

/**
 * 小区楼盘Entity
 * @author yangyq
 * @version 2017-08-15
 */
public class HousingEstate extends DataEntity<HousingEstate> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 楼盘名称
	private String serialNumber;		// 楼盘编号
	private String propType;		// 楼盘类别
	private String developer;		// 开发商名
	private String address;		// 楼盘地址
	private String periods;		// 楼盘期数
	private Date openDate;		// 开盘日期
	private String plotRatio;		// 容积率
	private String greeningRate;		// 绿化率
	private String yearTerm;		// 产权年限
	private String useCount;		// 规划户数
	
	public HousingEstate() {
		super();
	}

	public HousingEstate(String id){
		super(id);
	}

	@Length(min=0, max=50, message="楼盘名称长度必须介于 0 和 50 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=50, message="楼盘编号长度必须介于 0 和 50 之间")
	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	
	@Length(min=0, max=50, message="楼盘类别长度必须介于 0 和 50 之间")
	public String getPropType() {
		return propType;
	}

	public void setPropType(String propType) {
		this.propType = propType;
	}
	
	@Length(min=0, max=50, message="开发商名长度必须介于 0 和 50 之间")
	public String getDeveloper() {
		return developer;
	}

	public void setDeveloper(String developer) {
		this.developer = developer;
	}
	
	@Length(min=0, max=200, message="楼盘地址长度必须介于 0 和 200 之间")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Length(min=0, max=50, message="楼盘期数长度必须介于 0 和 50 之间")
	public String getPeriods() {
		return periods;
	}

	public void setPeriods(String periods) {
		this.periods = periods;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getOpenDate() {
		return openDate;
	}

	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}
	
	public String getPlotRatio() {
		return plotRatio;
	}

	public void setPlotRatio(String plotRatio) {
		this.plotRatio = plotRatio;
	}
	
	public String getGreeningRate() {
		return greeningRate;
	}

	public void setGreeningRate(String greeningRate) {
		this.greeningRate = greeningRate;
	}
	
	@Length(min=0, max=11, message="产权年限长度必须介于 0 和 11 之间")
	public String getYearTerm() {
		return yearTerm;
	}

	public void setYearTerm(String yearTerm) {
		this.yearTerm = yearTerm;
	}
	
	@Length(min=0, max=11, message="规划户数长度必须介于 0 和 11 之间")
	public String getUseCount() {
		return useCount;
	}

	public void setUseCount(String useCount) {
		this.useCount = useCount;
	}
	
}