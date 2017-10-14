/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.amass.credit.modules.renthouse.entity.leasecred;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.amass.credit.common.persistence.DataEntity;

/**
 * 出租屋租赁信息Entity
 * @author yangyq
 * @version 2017-06-23
 */
public class RenthouseLeasecred extends DataEntity<RenthouseLeasecred> {
	
	private static final long serialVersionUID = 1L;
	private Long subId;		// 出租屋id，对应出租屋表id
	private Long ownerId;		// 出租人姓名
	private String ownerFlag;		// 出租人是否业主，通过该信息判断是业主还是二房东：1-是 0-否
	private String owerAddr;		// 出租人现居住地址
	private Date rentSdate;		// 租赁开始时间
	private Date rentEdate;		// 租赁结束时间
	private String rentCode;		// 租赁备案登记号
	private String rentArea;		// 出租面积
	private String rentUse;		// 出租屋用途
	private String rentPart;		// 出租部位
	private String landhouseCred;		// 土地房屋权证号
	private String houseCred;		// 房屋所有权证号
	private String landCred;		// 土地使用证号
	private String realEstateCred;		// 不动产权证书
	private String hasGuard;		// 是否已签门禁合同：1-已办 0-未办
	
	public RenthouseLeasecred() {
		super();
	}

	public RenthouseLeasecred(String id){
		super(id);
	}

	public Long getSubId() {
		return subId;
	}

	public void setSubId(Long subId) {
		this.subId = subId;
	}
	
	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}
	
	@Length(min=0, max=11, message="出租人是否业主，通过该信息判断是业主还是二房东：1-是 0-否长度必须介于 0 和 11 之间")
	public String getOwnerFlag() {
		return ownerFlag;
	}

	public void setOwnerFlag(String ownerFlag) {
		this.ownerFlag = ownerFlag;
	}
	
	@Length(min=0, max=255, message="出租人现居住地址长度必须介于 0 和 255 之间")
	public String getOwerAddr() {
		return owerAddr;
	}

	public void setOwerAddr(String owerAddr) {
		this.owerAddr = owerAddr;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getRentSdate() {
		return rentSdate;
	}

	public void setRentSdate(Date rentSdate) {
		this.rentSdate = rentSdate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getRentEdate() {
		return rentEdate;
	}

	public void setRentEdate(Date rentEdate) {
		this.rentEdate = rentEdate;
	}
	
	@Length(min=0, max=20, message="租赁备案登记号长度必须介于 0 和 20 之间")
	public String getRentCode() {
		return rentCode;
	}

	public void setRentCode(String rentCode) {
		this.rentCode = rentCode;
	}
	
	@Length(min=0, max=20, message="出租面积长度必须介于 0 和 20 之间")
	public String getRentArea() {
		return rentArea;
	}

	public void setRentArea(String rentArea) {
		this.rentArea = rentArea;
	}
	
	@Length(min=0, max=21, message="出租屋用途长度必须介于 0 和 21 之间")
	public String getRentUse() {
		return rentUse;
	}

	public void setRentUse(String rentUse) {
		this.rentUse = rentUse;
	}
	
	@Length(min=0, max=20, message="出租部位长度必须介于 0 和 20 之间")
	public String getRentPart() {
		return rentPart;
	}

	public void setRentPart(String rentPart) {
		this.rentPart = rentPart;
	}
	
	@Length(min=0, max=20, message="土地房屋权证号长度必须介于 0 和 20 之间")
	public String getLandhouseCred() {
		return landhouseCred;
	}

	public void setLandhouseCred(String landhouseCred) {
		this.landhouseCred = landhouseCred;
	}
	
	@Length(min=0, max=20, message="房屋所有权证号长度必须介于 0 和 20 之间")
	public String getHouseCred() {
		return houseCred;
	}

	public void setHouseCred(String houseCred) {
		this.houseCred = houseCred;
	}
	
	@Length(min=0, max=20, message="土地使用证号长度必须介于 0 和 20 之间")
	public String getLandCred() {
		return landCred;
	}

	public void setLandCred(String landCred) {
		this.landCred = landCred;
	}
	
	@Length(min=0, max=20, message="不动产权证书长度必须介于 0 和 20 之间")
	public String getRealEstateCred() {
		return realEstateCred;
	}

	public void setRealEstateCred(String realEstateCred) {
		this.realEstateCred = realEstateCred;
	}
	
	@Length(min=0, max=1, message="是否已签门禁合同：1-已办 0-未办长度必须介于 0 和 1 之间")
	public String getHasGuard() {
		return hasGuard;
	}

	public void setHasGuard(String hasGuard) {
		this.hasGuard = hasGuard;
	}
	
}