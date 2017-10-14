package com.amass.credit.modules.sys.entity;

import com.amass.credit.common.persistence.DataEntity;
/**
 * 资源列表中间表，与其他表建立关系的Entity
 * @author lzw
 * @version 2015-08-20
 */
public class ResourcesIntermediate extends DataEntity<ResourcesIntermediate> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 文件名
	 */
	private String fastId ;
	/**
	 *父表id
	 */
	private String primaryKey;
	/**
	 * 父表名
	 */
    private String tableName;
    /**
     * 附表名
     */
    private Resources resource;
    
    public ResourcesIntermediate(String fastid, String primary_key,String tablename){
    	this.fastId = fastid;
    	this.primaryKey = primary_key;
    	this.tableName = tablename;
    }
    public ResourcesIntermediate(String primary_key,String tablename){
    	this.primaryKey = primary_key;
    	this.tableName = tablename;
    }
    public ResourcesIntermediate(){}
	

	public String getFastId() {
		return fastId;
	}



	public void setFastId(String fastId) {
		this.fastId = fastId;
	}



	public String getPrimaryKey() {
		return primaryKey;
	}



	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}



	public String getTableName() {
		return tableName;
	}



	public void setTableName(String tableName) {
		this.tableName = tableName;
	}



	public Resources getResource() {
		return resource;
	}

	public void setResource(Resources resource) {
		this.resource = resource;
	}
    
    
}
