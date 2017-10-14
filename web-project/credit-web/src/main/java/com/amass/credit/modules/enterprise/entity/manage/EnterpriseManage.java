/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.amass.credit.modules.enterprise.entity.manage;

import org.hibernate.validator.constraints.Length;

import com.amass.credit.common.persistence.DataEntity;
import com.amass.credit.common.utils.excel.annotation.ExcelField;

/**
 * 企业经营管理信息Entity
 * @author yangyq
 * @version 2017-06-20
 */
public class EnterpriseManage extends DataEntity<EnterpriseManage> {
	
	private static final long serialVersionUID = 1L;
	private Long subId;		// 企业编号
	private String subName;		// 企业名称
	private String registryNo;  //企业注册号
	private String creditNo;		// 企业统一信用代码
	private String income;		// 年业务收入
	private String deptProfit;		// 资产负债率
	private String mainProfit;		// 主营业务利润率
	private String assetsProfit;		// 总资产增长率
	private String increaseProfit;		// 营业收入增长率
	private String totalAssets;		// 资产总额
	private String happenYear;		// 经营年份
	private String ext1;		// 备注1
	private String ext2;		// 备注2
	private String ext3;		// 备注3
	private String ext4;		// 备注4
	
	public EnterpriseManage() {
		super();
	}

	public EnterpriseManage(String id){
		super(id);
	}

	public Long getSubId() {
		return subId;
	}

	public void setSubId(Long subId) {
		this.subId = subId;
	}
	
	@Length(min=0, max=50, message="主体名称长度必须介于 0 和 50 之间")
	@ExcelField(title="企业名称", align=2, sort=1, type=0)
	public String getSubName() {
		return subName;
	}

	public void setSubName(String subName) {
		this.subName = subName;
	}
	@ExcelField(title="企业注册号", align=2, sort=1, type=0)
	public String getRegistryNo() {
		return registryNo;
	}

	public void setRegistryNo(String registryNo) {
		this.registryNo = registryNo;
	}

	@Length(min=0, max=50, message="企业统一信用代码长度必须介于 0 和 50 之间")
	@ExcelField(title="企业统一信用代码", align=2, sort=1, type=0)
	public String getCreditNo() {
		return creditNo;
	}

	public void setCreditNo(String creditNo) {
		this.creditNo = creditNo;
	}
	
	@Length(min=0, max=50, message="年业务收入长度必须介于 0 和 50 之间")
	@ExcelField(title="年业务收入", align=2, sort=2, type=0)
	public String getIncome() {
		return income;
	}

	public void setIncome(String income) {
		this.income = income;
	}
	
	@Length(min=0, max=50, message="资产负债率长度必须介于 0 和 50 之间")
	@ExcelField(title="资产负债率", align=2, sort=2, type=0)
	public String getDeptProfit() {
		return deptProfit;
	}

	public void setDeptProfit(String deptProfit) {
		this.deptProfit = deptProfit;
	}
	
	@Length(min=0, max=50, message="主营业务利润率长度必须介于 0 和 50 之间")
	@ExcelField(title="主营业务利润率", align=2, sort=2, type=0)
	public String getMainProfit() {
		return mainProfit;
	}

	public void setMainProfit(String mainProfit) {
		this.mainProfit = mainProfit;
	}
	
	@Length(min=0, max=50, message="总资产增长率长度必须介于 0 和 50 之间")
	@ExcelField(title="总资产增长率", align=2, sort=2, type=0)
	public String getAssetsProfit() {
		return assetsProfit;
	}

	public void setAssetsProfit(String assetsProfit) {
		this.assetsProfit = assetsProfit;
	}
	
	@Length(min=0, max=50, message="营业收入增长率长度必须介于 0 和 50 之间")
	@ExcelField(title="营业收入增长率", align=2, sort=2, type=0)
	public String getIncreaseProfit() {
		return increaseProfit;
	}

	public void setIncreaseProfit(String increaseProfit) {
		this.increaseProfit = increaseProfit;
	}
	
	@Length(min=0, max=50, message="资产总额长度必须介于 0 和 50 之间")
	@ExcelField(title="资产总额", align=2, sort=2, type=0)
	public String getTotalAssets() {
		return totalAssets;
	}

	public void setTotalAssets(String totalAssets) {
		this.totalAssets = totalAssets;
	}
	
	@Length(min=0, max=50, message="经营年份长度必须介于 0 和 50 之间")
	@ExcelField(title="时间年份", align=2, sort=2, type=0)
	public String getHappenYear() {
		return happenYear;
	}

	public void setHappenYear(String happenYear) {
		this.happenYear = happenYear;
	}
	
	@Length(min=0, max=50, message="备注1长度必须介于 0 和 50 之间")
	public String getExt1() {
		return ext1;
	}

	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}
	
	@Length(min=0, max=50, message="备注2长度必须介于 0 和 50 之间")
	public String getExt2() {
		return ext2;
	}

	public void setExt2(String ext2) {
		this.ext2 = ext2;
	}
	
	@Length(min=0, max=50, message="备注3长度必须介于 0 和 50 之间")
	public String getExt3() {
		return ext3;
	}

	public void setExt3(String ext3) {
		this.ext3 = ext3;
	}
	
	@Length(min=0, max=50, message="备注4长度必须介于 0 和 50 之间")
	public String getExt4() {
		return ext4;
	}

	public void setExt4(String ext4) {
		this.ext4 = ext4;
	}
	
}