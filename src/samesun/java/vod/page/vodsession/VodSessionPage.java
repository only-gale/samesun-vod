package vod.page.vodsession;

/**   
 * @Title: Entity
 * @Description: 点播信息会话
 * @author zhangdaihao
 * @date 2014-07-15 17:46:55
 * @version V1.0   
 *
 */

public class VodSessionPage {
	/**主键*/
	private java.lang.String id;
	/**会议ID*/
	private java.lang.String meetingid;
	/**live session id*/
	private java.lang.String liveSession;
	/**会议类型*/
	private java.lang.Integer typeid;
	private java.lang.String typename;
	/**会议主题*/
	private java.lang.String subject;
	/**会议主持人*/
	private java.lang.String compere;
	/**会议简介*/
	private java.lang.String introduction;
	/**开始录制时间*/
	private java.util.Date begindt;
	/**结束录制时间*/
	private java.util.Date enddt;
	/**结束录制时间*/
	private Integer duration;
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
	public java.lang.String getMeetingid() {
		return meetingid;
	}
	public void setMeetingid(java.lang.String meetingid) {
		this.meetingid = meetingid;
	}
	public java.lang.String getLiveSession() {
		return liveSession;
	}
	public void setLiveSession(java.lang.String liveSession) {
		this.liveSession = liveSession;
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
	public java.util.Date getBegindt() {
		return begindt;
	}
	public void setBegindt(java.util.Date begindt) {
		this.begindt = begindt;
	}
	public java.util.Date getEnddt() {
		return enddt;
	}
	public void setEnddt(java.util.Date enddt) {
		this.enddt = enddt;
	}
	public Integer getDuration() {
		return duration;
	}
	public void setDuration(Integer duration) {
		this.duration = duration;
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
