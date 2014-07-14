package vod.page.appointmentchannelinfo;

/**   
 * @Title: Page
 * @Description: 预约频道信息
 * @author gale
 * @date 2014-06-20 12:00:34
 * @version V1.0   
 *
 */
@SuppressWarnings("serial")
public class AppointmentChannelInfoPage implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**预约会议ID*/
	private java.lang.String appointmentid;
	/**会议ID*/
	private java.lang.String meetingid;
	/**主Codec1*/
	private java.lang.String codec1id;
	/**主Codec1名称*/
	private java.lang.String codec1name;
	/**Codec1是否录制*/
	private java.lang.Integer isrecord1;
	/**备Codec2*/
	private java.lang.String codec2id;
	/**备Codec2名称*/
	private java.lang.String codec2name;
	/**Codec2是否录制*/
	private java.lang.Integer isrecord2;
	/**自定义权限组ID*/
	private java.lang.String authortiyGroupCid;
	/**自定义权限组名称*/
	private java.lang.String authortiyGroupCname;
	/**类型*/
	private java.lang.String authortiyGroupType;
	/**分配终端权限组（匿名类）*/
	private java.lang.String authortiyTerminlgroupCid;
	/**分配终端权限组（匿名类）*/
	private java.lang.String authortiyTerminlgroupCname;
	/**用户自定义权限组*/
	private java.lang.String authortiyUsergroupCid;
	/**用户自定义权限组*/
	private java.lang.String authortiyUsergroupCname;
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
	public java.lang.String getAppointmentid() {
		return appointmentid;
	}
	public void setAppointmentid(java.lang.String appointmentid) {
		this.appointmentid = appointmentid;
	}
	public java.lang.String getMeetingid() {
		return meetingid;
	}
	public void setMeetingid(java.lang.String meetingid) {
		this.meetingid = meetingid;
	}
	public java.lang.String getCodec1id() {
		return codec1id;
	}
	public void setCodec1id(java.lang.String codec1id) {
		this.codec1id = codec1id;
	}
	public java.lang.String getCodec1name() {
		return codec1name;
	}
	public void setCodec1name(java.lang.String codec1name) {
		this.codec1name = codec1name;
	}
	public java.lang.Integer getIsrecord1() {
		return isrecord1;
	}
	public void setIsrecord1(java.lang.Integer isrecord1) {
		this.isrecord1 = isrecord1;
	}
	public java.lang.String getCodec2id() {
		return codec2id;
	}
	public void setCodec2id(java.lang.String codec2id) {
		this.codec2id = codec2id;
	}
	public java.lang.String getCodec2name() {
		return codec2name;
	}
	public void setCodec2name(java.lang.String codec2name) {
		this.codec2name = codec2name;
	}
	public java.lang.Integer getIsrecord2() {
		return isrecord2;
	}
	public void setIsrecord2(java.lang.Integer isrecord2) {
		this.isrecord2 = isrecord2;
	}
	public java.lang.String getAuthortiyGroupCid() {
		return authortiyGroupCid;
	}
	public void setAuthortiyGroupCid(java.lang.String authortiyGroupCid) {
		this.authortiyGroupCid = authortiyGroupCid;
	}
	public java.lang.String getAuthortiyGroupCname() {
		return authortiyGroupCname;
	}
	public void setAuthortiyGroupCname(java.lang.String authortiyGroupCname) {
		this.authortiyGroupCname = authortiyGroupCname;
	}
	public java.lang.String getAuthortiyGroupType() {
		return authortiyGroupType;
	}
	public void setAuthortiyGroupType(java.lang.String authortiyGroupType) {
		this.authortiyGroupType = authortiyGroupType;
	}
	public java.lang.String getAuthortiyTerminlgroupCid() {
		return authortiyTerminlgroupCid;
	}
	public void setAuthortiyTerminlgroupCid(
			java.lang.String authortiyTerminlgroupCid) {
		this.authortiyTerminlgroupCid = authortiyTerminlgroupCid;
	}
	public java.lang.String getAuthortiyTerminlgroupCname() {
		return authortiyTerminlgroupCname;
	}
	public void setAuthortiyTerminlgroupCname(
			java.lang.String authortiyTerminlgroupCname) {
		this.authortiyTerminlgroupCname = authortiyTerminlgroupCname;
	}
	public java.lang.String getAuthortiyUsergroupCid() {
		return authortiyUsergroupCid;
	}
	public void setAuthortiyUsergroupCid(java.lang.String authortiyUsergroupCid) {
		this.authortiyUsergroupCid = authortiyUsergroupCid;
	}
	public java.lang.String getAuthortiyUsergroupCname() {
		return authortiyUsergroupCname;
	}
	public void setAuthortiyUsergroupCname(java.lang.String authortiyUsergroupCname) {
		this.authortiyUsergroupCname = authortiyUsergroupCname;
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
