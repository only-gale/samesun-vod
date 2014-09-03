package vod.entity.appointmentmeetinginfo;


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
 * @Description: 会议预约
 * @author zhangdaihao
 * @date 2014-06-18 17:06:03
 * @version V1.0   
 *
 */
@Entity
@Table(name = "appointment_meeting_info", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class AppointmentMeetingInfoEntity implements java.io.Serializable {
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
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  预约时间
	 */
	@Column(name ="APPOINTMENT_STARTTIME",nullable=true)
	public java.util.Date getAppointmentStarttime(){
		return this.appointmentStarttime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  预约时间
	 */
	public void setAppointmentStarttime(java.util.Date appointmentStarttime){
		this.appointmentStarttime = appointmentStarttime;
	}
	
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  直播开始时间
	 */
	@Column(name ="LIVE_TIME",nullable=true)
	public java.util.Date getLiveTime() {
		return liveTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  直播开始时间
	 */
	public void setLiveTime(java.util.Date liveTime) {
		this.liveTime = liveTime;
	}

	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  会议延长时间
	 */
	@Column(name ="DELAY_TIME",nullable=true)
	public java.lang.Integer getDelayTime() {
		return delayTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  会议延长时间
	 */
	public void setDelayTime(java.lang.Integer delayTime) {
		this.delayTime = delayTime;
	}

	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  预约持续时长
	 */
	@Column(name ="APPOINTMENT_DURATION",nullable=true,precision=10,scale=0)
	public java.lang.Integer getAppointmentDuration(){
		return this.appointmentDuration;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  预约持续时长
	 */
	public void setAppointmentDuration(java.lang.Integer appointmentDuration){
		this.appointmentDuration = appointmentDuration;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  预约状态
	 */
	@Column(name ="APPOINTMENT_STATE",nullable=false,precision=3,scale=0)
	public java.lang.Integer getAppointmentState(){
		return this.appointmentState;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  预约状态
	 */
	public void setAppointmentState(java.lang.Integer appointmentState){
		this.appointmentState = appointmentState;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  所属类型
	 */
	@Column(name ="TYPEID",nullable=true,precision=3,scale=0)
	public java.lang.Integer getTypeid(){
		return this.typeid;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  所属类型
	 */
	public void setTypeid(java.lang.Integer typeid){
		this.typeid = typeid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  会议主题
	 */
	@Column(name ="SUBJECT",nullable=true,length=50)
	public java.lang.String getSubject(){
		return this.subject;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  会议主题
	 */
	public void setSubject(java.lang.String subject){
		this.subject = subject;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  会议主持人
	 */
	@Column(name ="COMPERE",nullable=true,length=20)
	public java.lang.String getCompere(){
		return this.compere;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  会议主持人
	 */
	public void setCompere(java.lang.String compere){
		this.compere = compere;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  会议简介
	 */
	@Column(name ="INTRODUCTION",nullable=true,length=1024)
	public java.lang.String getIntroduction(){
		return this.introduction;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  会议简介
	 */
	public void setIntroduction(java.lang.String introduction){
		this.introduction = introduction;
	}
	
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  是否录制
	 */
	@Column(name ="ISRECORD",nullable=true)
	public java.lang.Integer getIsRecord() {
		return isRecord;
	}

	public void setIsRecord(java.lang.Integer isRecord) {
		this.isRecord = isRecord;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String
	 */
	@Column(name ="RIGHTID",nullable=true,length=45)
	public java.lang.String getRightid(){
		return this.rightid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String
	 */
	public void setRightid(java.lang.String rightid){
		this.rightid = rightid;
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
