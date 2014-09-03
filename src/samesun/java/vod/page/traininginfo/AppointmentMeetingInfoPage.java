package vod.page.traininginfo;

/**   
 * @Title: Entity
 * @Description: 会议预约
 * @author zhangdaihao
 * @date 2014-06-18 17:06:03
 * @version V1.0   
 *
 */
public class AppointmentMeetingInfoPage {
	/**主键*/
	private java.lang.String id;
	/**预约时间*/
	private java.util.Date appointmentStarttime;
	/**直播开始时间*/
	private java.util.Date liveTime;
	/**预约持续时长*/
	private java.lang.Integer appointmentDuration;
	/**会议延长时间*/
	private java.lang.Integer delayTime;
	/**预约状态*/
	private java.lang.Integer appointmentState;
	/**所属类型*/
	private java.lang.Integer typeid;
	/**所属类型*/
	private java.lang.String typename;
	/**会议主题*/
	private java.lang.String subject;
	/**会议主持人*/
	private java.lang.String compere;
	/**会议简介*/
	private java.lang.String introduction;
	/**是否录制*/
	private java.lang.Integer isRecord;
	/**种类：1会议; 2培训*/
	private java.lang.String rightid;
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
	public java.util.Date getAppointmentStarttime() {
		return appointmentStarttime;
	}
	public void setAppointmentStarttime(java.util.Date appointmentStarttime) {
		this.appointmentStarttime = appointmentStarttime;
	}
	public java.util.Date getLiveTime() {
		return liveTime;
	}
	public void setLiveTime(java.util.Date liveTime) {
		this.liveTime = liveTime;
	}
	public java.lang.Integer getAppointmentDuration() {
		return appointmentDuration;
	}
	public void setAppointmentDuration(java.lang.Integer appointmentDuration) {
		this.appointmentDuration = appointmentDuration;
	}
	public java.lang.Integer getDelayTime() {
		return delayTime;
	}
	public void setDelayTime(java.lang.Integer delayTime) {
		this.delayTime = delayTime;
	}
	public java.lang.Integer getAppointmentState() {
		return appointmentState;
	}
	public void setAppointmentState(java.lang.Integer appointmentState) {
		this.appointmentState = appointmentState;
	}
	public java.lang.Integer getTypeid() {
		return typeid;
	}
	public void setTypeid(java.lang.Integer typeid) {
		this.typeid = typeid;
	}
	public java.lang.String getTypename() {
		return typename;
	}
	public void setTypename(java.lang.String typename) {
		this.typename = typename;
	}
	public java.lang.String getSubject() {
		return subject;
	}
	public void setSubject(java.lang.String subject) {
		this.subject = subject;
	}
	public java.lang.String getCompere() {
		return compere;
	}
	public void setCompere(java.lang.String compere) {
		this.compere = compere;
	}
	public java.lang.String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(java.lang.String introduction) {
		this.introduction = introduction;
	}
	public java.lang.Integer getIsRecord() {
		return isRecord;
	}
	public void setIsRecord(java.lang.Integer isRecord) {
		this.isRecord = isRecord;
	}
	public java.lang.String getRightid() {
		return rightid;
	}
	public void setRightid(java.lang.String rightid) {
		this.rightid = rightid;
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
