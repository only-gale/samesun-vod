package vod.entity.livesectionrecord;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

/**   
 * @Title: Entity
 * @Description: 直播会议录制分表
 * @author zhangdaihao
 * @date 2014-07-11 12:39:44
 * @version V1.0   
 *
 */
@Entity
@Table(name = "meeting_live_section_record", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class LiveSectionRecordEntity implements java.io.Serializable {
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
	/**Rtsp服务器地址*/
	private java.lang.String rtsprelativedir;
	/**录制的文件名称*/
	private java.lang.String filename;
	/**录制状态*/
	private java.lang.Integer recState;
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
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  主键
	 */
	
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name ="ID",nullable=false,length=36)
	public java.lang.String getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  主键
	 */
	public void setId(java.lang.String id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  会议ID
	 */
	@Column(name ="MEETINGID",nullable=false,length=36)
	public java.lang.String getMeetingid(){
		return this.meetingid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  会议ID
	 */
	public void setMeetingid(java.lang.String meetingid){
		this.meetingid = meetingid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  频道ID
	 */
	@Column(name ="CHANNELID",nullable=false,length=36)
	public java.lang.String getChannelid(){
		return this.channelid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  频道ID
	 */
	public void setChannelid(java.lang.String channelid){
		this.channelid = channelid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  SessionID号
	 */
	@Column(name ="SESSIONID",nullable=true,length=36)
	public java.lang.String getSessionid(){
		return this.sessionid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  SessionID号
	 */
	public void setSessionid(java.lang.String sessionid){
		this.sessionid = sessionid;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  Codec主备标志
	 */
	@Column(name ="CODECPRIORITYFLG",nullable=true,precision=3,scale=0)
	public java.lang.Integer getCodecpriorityflg(){
		return this.codecpriorityflg;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  Codec主备标志
	 */
	public void setCodecpriorityflg(java.lang.Integer codecpriorityflg){
		this.codecpriorityflg = codecpriorityflg;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  Codec服务器ID号
	 */
	@Column(name ="CODECSRVID",nullable=true,length=36)
	public java.lang.String getCodecsrvid(){
		return this.codecsrvid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  Codec服务器ID号
	 */
	public void setCodecsrvid(java.lang.String codecsrvid){
		this.codecsrvid = codecsrvid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  录播服务器ID
	 */
	@Column(name ="RECORDSRVID",nullable=false,length=36)
	public java.lang.String getRecordsrvid(){
		return this.recordsrvid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  录播服务器ID
	 */
	public void setRecordsrvid(java.lang.String recordsrvid){
		this.recordsrvid = recordsrvid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  Rtsp服务器ID
	 */
	@Column(name ="RTSPSRVID",nullable=false,length=36)
	public java.lang.String getRtspsrvid(){
		return this.rtspsrvid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  Rtsp服务器ID
	 */
	public void setRtspsrvid(java.lang.String rtspsrvid){
		this.rtspsrvid = rtspsrvid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  Rtsp服务器地址
	 */
	@Column(name ="RTSPRELATIVEDIR",nullable=true,length=36)
	public java.lang.String getRtsprelativedir(){
		return this.rtsprelativedir;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  Rtsp服务器地址
	 */
	public void setRtsprelativedir(java.lang.String rtsprelativedir){
		this.rtsprelativedir = rtsprelativedir;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  录制的文件名称
	 */
	@Column(name ="FILENAME",nullable=true,length=36)
	public java.lang.String getFilename(){
		return this.filename;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  录制的文件名称
	 */
	public void setFilename(java.lang.String filename){
		this.filename = filename;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  录制状态
	 */
	@Column(name ="REC_STATE",nullable=true,precision=3,scale=0)
	public java.lang.Integer getRecState(){
		return this.recState;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  录制状态
	 */
	public void setRecState(java.lang.Integer recState){
		this.recState = recState;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  录制备注
	 */
	@Column(name ="REC_MESSAGE",nullable=true,length=1000)
	public java.lang.String getRecMessage(){
		return this.recMessage;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  录制备注
	 */
	public void setRecMessage(java.lang.String recMessage){
		this.recMessage = recMessage;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  起始时间
	 */
	@Column(name ="REC_START_DT",nullable=false)
	public java.util.Date getRecStartDt(){
		return this.recStartDt;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  起始时间
	 */
	public void setRecStartDt(java.util.Date recStartDt){
		this.recStartDt = recStartDt;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  结束时间
	 */
	@Column(name ="REC_END_DT",nullable=true)
	public java.util.Date getRecEndDt(){
		return this.recEndDt;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  结束时间
	 */
	public void setRecEndDt(java.util.Date recEndDt){
		this.recEndDt = recEndDt;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  持续时长
	 */
	@Column(name ="BILLDURATION",nullable=true,precision=10,scale=0)
	public java.lang.Integer getBillduration(){
		return this.billduration;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  持续时长
	 */
	public void setBillduration(java.lang.Integer billduration){
		this.billduration = billduration;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  资源URL
	 */
	@Column(name ="ASFURL",nullable=true,length=100)
	public java.lang.String getAsfurl(){
		return this.asfurl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  资源URL
	 */
	public void setAsfurl(java.lang.String asfurl){
		this.asfurl = asfurl;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人
	 */
	@Column(name ="CREATE_BY",nullable=true,length=36)
	public java.lang.String getCreateBy(){
		return this.createBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人
	 */
	public void setCreateBy(java.lang.String createBy){
		this.createBy = createBy;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人名字
	 */
	@Column(name ="CREATE_NAME",nullable=true,length=32)
	public java.lang.String getCreateName(){
		return this.createName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人名字
	 */
	public void setCreateName(java.lang.String createName){
		this.createName = createName;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  创建时间
	 */
	@Column(name ="CREATE_DATE",nullable=true)
	public java.util.Date getCreateDate(){
		return this.createDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  创建时间
	 */
	public void setCreateDate(java.util.Date createDate){
		this.createDate = createDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  修改人
	 */
	@Column(name ="UPDATE_BY",nullable=true,length=36)
	public java.lang.String getUpdateBy(){
		return this.updateBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  修改人
	 */
	public void setUpdateBy(java.lang.String updateBy){
		this.updateBy = updateBy;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  修改人名字
	 */
	@Column(name ="UPDATE_NAME",nullable=true,length=32)
	public java.lang.String getUpdateName(){
		return this.updateName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  修改人名字
	 */
	public void setUpdateName(java.lang.String updateName){
		this.updateName = updateName;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  修改时间
	 */
	@Column(name ="UPDATE_DATE",nullable=true)
	public java.util.Date getUpdateDate(){
		return this.updateDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  修改时间
	 */
	public void setUpdateDate(java.util.Date updateDate){
		this.updateDate = updateDate;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  删除标记
	 */
	@Column(name ="DELFLAG",nullable=true,precision=10,scale=0)
	public java.lang.Integer getDelflag(){
		return this.delflag;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  删除标记
	 */
	public void setDelflag(java.lang.Integer delflag){
		this.delflag = delflag;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  删除时间
	 */
	@Column(name ="DEL_DATE",nullable=true)
	public java.util.Date getDelDate(){
		return this.delDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  删除时间
	 */
	public void setDelDate(java.util.Date delDate){
		this.delDate = delDate;
	}
}
