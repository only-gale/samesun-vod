package vod.page.terminalinfo;

import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: 终端信息
 * @author gale
 * @date 2014-07-21 15:52:33
 * @version V1.0   
 *
 */

public class TerminalInfoPage {
	/**主键*/
	private java.lang.String id;
	/**终端名*/
	@Excel(exportFieldWidth=30, exportName="终端名")
	private java.lang.String name;
	/**终端描述*/
	@Excel(exportFieldWidth=50, exportName="终端描述")
	private java.lang.String descript;
	/**mac地址*/
	@Excel(exportFieldWidth=20, exportName="mac地址")
	private java.lang.String macaddress;
	/**IP地址*/
	@Excel(exportFieldWidth=20, exportName="IP地址")
	private java.lang.String ipaddress;
	/**地理位置*/
	private java.lang.String groupid;
	/**地理位置名称*/
	@Excel(exportFieldWidth=20, exportName="组织机构")
	private java.lang.String groupname;
	/**当前状态*/
	private java.lang.Integer status;
	/**当前状态*/
	@Excel(exportFieldWidth=20, exportName="当前状态")
	private java.lang.String statusname;
	/**正在观看节目id*/
	private java.lang.String nowvideo;
	/**会议主题*/
	private java.lang.String subject;
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
	public java.lang.Integer getStatus() {
		return status;
	}
	public void setStatus(java.lang.Integer status) {
		this.status = status;
	}
	public java.lang.String getStatusname() {
		return statusname;
	}
	public void setStatusname(java.lang.String statusname) {
		this.statusname = statusname;
	}
	public java.lang.String getNowvideo() {
		return nowvideo;
	}
	public void setNowvideo(java.lang.String nowvideo) {
		this.nowvideo = nowvideo;
	}
	public java.lang.String getSubject() {
		return subject;
	}
	public void setSubject(java.lang.String subject) {
		this.subject = subject;
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
