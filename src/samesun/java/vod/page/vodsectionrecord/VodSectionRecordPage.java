package vod.page.vodsectionrecord;

/**   
 * @Title: Entity
 * @Description: 点播信息
 * @author zhangdaihao
 * @date 2014-07-15 17:48:17
 * @version V1.0   
 *
 */
public class VodSectionRecordPage {
	/**主键*/
	private java.lang.String id;
	/**会议ID*/
	private java.lang.String meetingid;
	/**频道ID*/
	private java.lang.String channelid;
	/**SessionID号*/
	private java.lang.String sessionid;
	/**Codec主备标志*/
	private java.lang.Integer codecpriorityflg;
	/**Codec服务器ID号*/
	private java.lang.String codecsrvid;
	/**录播服务器ID*/
	private java.lang.String recordsrvid;
	/**Rtsp服务器ID*/
	private java.lang.String rtspsrvid;
	/**Rtsp服务器名称*/
	private java.lang.String rtspsrvname;
	/**相对目录*/
	private java.lang.String rtsprelativedir;
	/**录制的文件名称*/
	private java.lang.String filename;
	/**自定义权限组ID*/
	private java.lang.String authortiyGroupCid;
	/**自定义权限组名称*/
	private java.lang.String authortiyGroupName;
	/**分配终端权限组（匿名类）*/
	private java.lang.String authortiyTerminlgroupCid;
	/**用户自定义权限组*/
	private java.lang.String authortiyUsergroupCid;
	/**用户自定义权限组*/
	private java.lang.String authortiyUsergroupName;
	/**录制状态*/
	private java.lang.Integer recState;
	/**录制状态名称*/
	private java.lang.String recStateName;
	/**录制备注*/
	private java.lang.String recMessage;
	/**起始时间*/
	private java.util.Date recStartDt;
	/**结束时间*/
	private java.util.Date recEndDt;
	/**持续时长*/
	private java.lang.Integer billduration;
	/**资源URL*/
	private java.lang.String asfurl;
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
	public java.lang.String getChannelid() {
		return channelid;
	}
	public void setChannelid(java.lang.String channelid) {
		this.channelid = channelid;
	}
	public java.lang.String getSessionid() {
		return sessionid;
	}
	public void setSessionid(java.lang.String sessionid) {
		this.sessionid = sessionid;
	}
	public java.lang.Integer getCodecpriorityflg() {
		return codecpriorityflg;
	}
	public void setCodecpriorityflg(java.lang.Integer codecpriorityflg) {
		this.codecpriorityflg = codecpriorityflg;
	}
	public java.lang.String getCodecsrvid() {
		return codecsrvid;
	}
	public void setCodecsrvid(java.lang.String codecsrvid) {
		this.codecsrvid = codecsrvid;
	}
	public java.lang.String getRecordsrvid() {
		return recordsrvid;
	}
	public void setRecordsrvid(java.lang.String recordsrvid) {
		this.recordsrvid = recordsrvid;
	}
	public java.lang.String getRtspsrvid() {
		return rtspsrvid;
	}
	public void setRtspsrvid(java.lang.String rtspsrvid) {
		this.rtspsrvid = rtspsrvid;
	}
	public java.lang.String getRtspsrvname() {
		return rtspsrvname;
	}
	public void setRtspsrvname(java.lang.String rtspsrvname) {
		this.rtspsrvname = rtspsrvname;
	}
	public java.lang.String getRtsprelativedir() {
		return rtsprelativedir;
	}
	public void setRtsprelativedir(java.lang.String rtsprelativedir) {
		this.rtsprelativedir = rtsprelativedir;
	}
	public java.lang.String getFilename() {
		return filename;
	}
	public void setFilename(java.lang.String filename) {
		this.filename = filename;
	}
	public java.lang.String getAuthortiyGroupCid() {
		return authortiyGroupCid;
	}
	public void setAuthortiyGroupCid(java.lang.String authortiyGroupCid) {
		this.authortiyGroupCid = authortiyGroupCid;
	}
	public java.lang.String getAuthortiyGroupName() {
		return authortiyGroupName;
	}
	public void setAuthortiyGroupName(java.lang.String authortiyGroupName) {
		this.authortiyGroupName = authortiyGroupName;
	}
	public java.lang.String getAuthortiyTerminlgroupCid() {
		return authortiyTerminlgroupCid;
	}
	public void setAuthortiyTerminlgroupCid(
			java.lang.String authortiyTerminlgroupCid) {
		this.authortiyTerminlgroupCid = authortiyTerminlgroupCid;
	}
	public java.lang.String getAuthortiyUsergroupCid() {
		return authortiyUsergroupCid;
	}
	public void setAuthortiyUsergroupCid(java.lang.String authortiyUsergroupCid) {
		this.authortiyUsergroupCid = authortiyUsergroupCid;
	}
	public java.lang.String getAuthortiyUsergroupName() {
		return authortiyUsergroupName;
	}
	public void setAuthortiyUsergroupName(java.lang.String authortiyUsergroupName) {
		this.authortiyUsergroupName = authortiyUsergroupName;
	}
	public java.lang.Integer getRecState() {
		return recState;
	}
	public void setRecState(java.lang.Integer recState) {
		this.recState = recState;
	}
	public java.lang.String getRecStateName() {
		return recStateName;
	}
	public void setRecStateName(java.lang.String recStateName) {
		this.recStateName = recStateName;
	}
	public java.lang.String getRecMessage() {
		return recMessage;
	}
	public void setRecMessage(java.lang.String recMessage) {
		this.recMessage = recMessage;
	}
	public java.util.Date getRecStartDt() {
		return recStartDt;
	}
	public void setRecStartDt(java.util.Date recStartDt) {
		this.recStartDt = recStartDt;
	}
	public java.util.Date getRecEndDt() {
		return recEndDt;
	}
	public void setRecEndDt(java.util.Date recEndDt) {
		this.recEndDt = recEndDt;
	}
	public java.lang.Integer getBillduration() {
		return billduration;
	}
	public void setBillduration(java.lang.Integer billduration) {
		this.billduration = billduration;
	}
	public java.lang.String getAsfurl() {
		return asfurl;
	}
	public void setAsfurl(java.lang.String asfurl) {
		this.asfurl = asfurl;
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
