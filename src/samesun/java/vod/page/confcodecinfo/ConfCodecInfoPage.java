package vod.page.confcodecinfo;

/**   
 * @Title: Entity
 * @Description: 编码器配置信息
 * @author gale
 * @date 2014-07-16 10:40:44
 * @version V1.0   
 *
 */
public class ConfCodecInfoPage {
	/**主键*/
	private java.lang.String id;
	/**Codec名称*/
	private java.lang.String name;
	/**地理位置*/
	private java.lang.String groupid;
	private java.lang.String groupname;
	/**路数占用情况*/
	private java.lang.String resources;
	/**描述*/
	private java.lang.String descript;
	/**mac地址*/
	private java.lang.String macaddress;
	/**IP地址*/
	private java.lang.String ipaddress;
	/**直播URL*/
	private java.lang.String codecurl;
	/**是否启用*/
	private java.lang.String disable;
	/**与RECORD关联关系ID*/
	private java.lang.String cr;
	/**RECORD*/
	private java.lang.String record;
	/**创建人*/
	private java.lang.String createBy;
	/**创建人名字*/
	private java.lang.String createName;
	/**创建时间*/
	private java.util.Date createDate;
	/**修改人*/
	private java.lang.String updateBy;
	/**修改人名字*/
	private java.lang.String updateName;
	/**修改时间*/
	private java.util.Date updateDate;
	/**删除标记*/
	private java.lang.Integer delflag;
	/**删除时间*/
	private java.util.Date delDate;
	public java.lang.String getId() {
		return id;
	}
	public void setId(java.lang.String id) {
		this.id = id;
	}
	public java.lang.String getName() {
		return name;
	}
	public void setName(java.lang.String name) {
		this.name = name;
	}
	public java.lang.String getGroupid() {
		return groupid;
	}
	public void setGroupid(java.lang.String groupid) {
		this.groupid = groupid;
	}
	public java.lang.String getGroupname() {
		return groupname;
	}
	public void setGroupname(java.lang.String groupname) {
		this.groupname = groupname;
	}
	public java.lang.String getResources() {
		return resources;
	}
	public void setResources(java.lang.String resources) {
		this.resources = resources;
	}
	public java.lang.String getDescript() {
		return descript;
	}
	public void setDescript(java.lang.String descript) {
		this.descript = descript;
	}
	public java.lang.String getMacaddress() {
		return macaddress;
	}
	public void setMacaddress(java.lang.String macaddress) {
		this.macaddress = macaddress;
	}
	public java.lang.String getIpaddress() {
		return ipaddress;
	}
	public void setIpaddress(java.lang.String ipaddress) {
		this.ipaddress = ipaddress;
	}
	public java.lang.String getCodecurl() {
		return codecurl;
	}
	public void setCodecurl(java.lang.String codecurl) {
		this.codecurl = codecurl;
	}
	public java.lang.String getDisable() {
		return disable;
	}
	public void setDisable(java.lang.String disable) {
		this.disable = disable;
	}
	public java.lang.String getCr() {
		return cr;
	}
	public void setCr(java.lang.String cr) {
		this.cr = cr;
	}
	public java.lang.String getRecord() {
		return record;
	}
	public void setRecord(java.lang.String record) {
		this.record = record;
	}
	public java.lang.String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(java.lang.String createBy) {
		this.createBy = createBy;
	}
	public java.lang.String getCreateName() {
		return createName;
	}
	public void setCreateName(java.lang.String createName) {
		this.createName = createName;
	}
	public java.util.Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}
	public java.lang.String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(java.lang.String updateBy) {
		this.updateBy = updateBy;
	}
	public java.lang.String getUpdateName() {
		return updateName;
	}
	public void setUpdateName(java.lang.String updateName) {
		this.updateName = updateName;
	}
	public java.util.Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(java.util.Date updateDate) {
		this.updateDate = updateDate;
	}
	public java.lang.Integer getDelflag() {
		return delflag;
	}
	public void setDelflag(java.lang.Integer delflag) {
		this.delflag = delflag;
	}
	public java.util.Date getDelDate() {
		return delDate;
	}
	public void setDelDate(java.util.Date delDate) {
		this.delDate = delDate;
	}
}
