package com.amass.credit.modules.sys.entity;

import java.io.Serializable;

import com.amass.credit.common.supcan.annotation.treelist.SupTreeList;

/**
 * 
 * @author Generator
 * @date 2015-08-11 09:15:04
 * @version 1.0.0
 * @copyright facegarden.com
 */
public class Resources implements Serializable {

	private static final long serialVersionUID = 1L;

    /**
     * 编号.
     */
    private Integer id;

    /**
     * 文件ID.
     */
    private String fastId;

    /**
     * 相对路径.
     */
    private String url;

    /**
     * 1:图片  2:视频.
     */
    private String type;

    /**
     * 后缀.
     */
    private String fileextname;

    /**
     * 1:党建 2:社区综网 3:服务平台.
     */
    private String systemTerrace="1";

    /**
     * 0:可用 1:停用.
     */
    private String flag;

    /**
     * dht的Key值.
     */
    private String dhtUrl;

    /**
     * 
     * {@linkplain #id}
     *
     * @return the value of resources.ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * {@linkplain #id}
     * @param id the value for resources.ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 
     * {@linkplain #fastId}
     *
     * @return the value of resources.FAST_ID
     */
    public String getFastId() {
        return fastId;
    }

    /**
     * {@linkplain #fastId}
     * @param fastId the value for resources.FAST_ID
     */
    public void setFastId(String fastId) {
        this.fastId = fastId == null ? null : fastId.trim();
    }

    /**
     * 
     * {@linkplain #url}
     *
     * @return the value of resources.URL
     */
    public String getUrl() {
        return url;
    }

    /**
     * {@linkplain #url}
     * @param url the value for resources.URL
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * 
     * {@linkplain #type}
     *
     * @return the value of resources.TYPE
     */
    public String getType() {
        return type;
    }

    /**
     * {@linkplain #type}
     * @param type the value for resources.TYPE
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * 
     * {@linkplain #fileextname}
     *
     * @return the value of resources.FILEEXTNAME
     */
    public String getFileextname() {
        return fileextname;
    }

    /**
     * {@linkplain #fileextname}
     * @param fileextname the value for resources.FILEEXTNAME
     */
    public void setFileextname(String fileextname) {
        this.fileextname = fileextname == null ? null : fileextname.trim();
    }

    /**
     * 
     * {@linkplain #systemTerrace}
     *
     * @return the value of resources.SYSTEM_TERRACE
     */
    public String getSystemTerrace() {
        return systemTerrace;
    }

    /**
     * {@linkplain #systemTerrace}
     * @param systemTerrace the value for resources.SYSTEM_TERRACE
     */
    public void setSystemTerrace(String systemTerrace) {
        this.systemTerrace = systemTerrace == null ? null : systemTerrace.trim();
    }

    /**
     * 
     * {@linkplain #flag}
     *
     * @return the value of resources.FLAG
     */
    public String getFlag() {
        return flag;
    }

    /**
     * {@linkplain #flag}
     * @param flag the value for resources.FLAG
     */
    public void setFlag(String flag) {
        this.flag = flag == null ? null : flag.trim();
    }

    /**
     * 
     * {@linkplain #dhtUrl}
     *
     * @return the value of resources.DHT_URL
     */
    public String getDhtUrl() {
        return dhtUrl;
    }

    /**
     * {@linkplain #dhtUrl}
     * @param dhtUrl the value for resources.DHT_URL
     */
    public void setDhtUrl(String dhtUrl) {
        this.dhtUrl = dhtUrl == null ? null : dhtUrl.trim();
    }
}