package com.amass.credit.modules.sys;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.amass.credit.BaseTest;
import com.amass.credit.modules.sys.entity.Dict;
import com.amass.credit.modules.sys.service.DictService;

/**
 * 数据字典测试用例 
 * @author HuangTao
 * @version 2015年5月18日
 */
public class DictServiceTest extends BaseTest {
	
	@Autowired
	DictService dictService;
	
	@Test
	public void findTypeList() {
		/*Dict dict = new Dict();
		dict.setDelFlag("0");
		dict.setType("yes_no");
		List<Dict> list = dictService.findList(dict);
		Assert.assertEquals(list.size(), 2);*/
	}
	
}
