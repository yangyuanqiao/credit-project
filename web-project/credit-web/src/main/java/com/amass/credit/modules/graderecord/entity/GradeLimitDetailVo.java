package com.amass.credit.modules.graderecord.entity;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import com.amass.credit.modules.gradelimit.entity.category.GradeLimit;
import com.amass.credit.modules.gradelimit.entity.detail.GradeLimitDetail;

public class GradeLimitDetailVo {

	private GradeLimit limit;

	private GradeLimitDetail detail;

	public GradeLimit getLimit() {
		return limit;
	}

	public void setLimit(GradeLimit limit) {
		this.limit = limit;
	}

	public GradeLimitDetail getDetail() {
		return detail;
	}

	public void setDetail(GradeLimitDetail detail) {
		this.detail = detail;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

}
