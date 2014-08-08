package vod.entity.meetinghistory;

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
 * @Description: 历史会议
 * @author zhangdaihao
 * @date 2014-07-15 17:45:07
 * @version V1.0   
 *
 */
@Entity
@Table(name = "meeting_info_history", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class MeetingHistoryEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**VRS_ID号*/
	private java.lang.String billid;
	/**设备名*/
	private java.lang.String billname;
	/**时间*/
	private java.util.Date billstarttime;
	/**持续时长*/
	private java.lang.Integer billduration;
	/**Live类型*/
	private java.lang.Integer isasflive;
	/**是否录音*/
	private java.lang.Integer isrecord;
	/**会议状态*/
	private java.lang.Integer meetingstate;
	/**会议有限状态机*/
	private java.lang.String fsmstate;
	/**预约录制时间*/
	private java.lang.String appointmentdt;
	/**预约状态*/
	private java.lang.Integer appointmentstate;
	/**资源URL*/
	private java.lang.String asfurl;
	/**会议名称*/
	private java.lang.String name;
	/**所属类型*/
	private java.lang.Integer typeid;
	/**终端分组*/
	private java.lang.String rightid;
	/**会议主题*/
	private java.lang.String subject;
	/**会议主持人*/
	private java.lang.String compere;
	/**会议简介*/
	private java.lang.String introduction;
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
	 *@return: java.lang.String  VRS_ID号
	 */
	@Column(name ="BILLID",nullable=true,length=36)
	public java.lang.String getBillid(){
		return this.billid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  VRS_ID号
	 */
	public void setBillid(java.lang.String billid){
		this.billid = billid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  设备名
	 */
	@Column(name ="BILLNAME",nullable=true,length=100)
	public java.lang.String getBillname(){
		return this.billname;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  设备名
	 */
	public void setBillname(java.lang.String billname){
		this.billname = billname;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  时间
	 */
	@Column(name ="BILLSTARTTIME",nullable=true)
	public java.util.Date getBillstarttime(){
		return this.billstarttime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  时间
	 */
	public void setBillstarttime(java.util.Date billstarttime){
		this.billstarttime = billstarttime;
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
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  Live类型
	 */
	@Column(name ="ISASFLIVE",nullable=true,precision=3,scale=0)
	public java.lang.Integer getIsasflive(){
		return this.isasflive;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  Live类型
	 */
	public void setIsasflive(java.lang.Integer isasflive){
		this.isasflive = isasflive;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  是否录音
	 */
	@Column(name ="ISRECORD",nullable=true,precision=3,scale=0)
	public java.lang.Integer getIsrecord(){
		return this.isrecord;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  是否录音
	 */
	public void setIsrecord(java.lang.Integer isrecord){
		this.isrecord = isrecord;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  会议状态
	 */
	@Column(name ="MEETINGSTATE",nullable=true,precision=3,scale=0)
	public java.lang.Integer getMeetingstate(){
		return this.meetingstate;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  会议状态
	 */
	public void setMeetingstate(java.lang.Integer meetingstate){
		this.meetingstate = meetingstate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  会议有限状态机
	 */
	@Column(name ="FSMSTATE",nullable=true,length=1)
	public java.lang.String getFsmstate(){
		return this.fsmstate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  会议有限状态机
	 */
	public void setFsmstate(java.lang.String fsmstate){
		this.fsmstate = fsmstate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预约录制时间
	 */
	@Column(name ="APPOINTMENTDT",nullable=true,length=5)
	public java.lang.String getAppointmentdt(){
		return this.appointmentdt;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预约录制时间
	 */
	public void setAppointmentdt(java.lang.String appointmentdt){
		this.appointmentdt = appointmentdt;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  预约状态
	 */
	@Column(name ="APPOINTMENTSTATE",nullable=true,precision=3,scale=0)
	public java.lang.Integer getAppointmentstate(){
		return this.appointmentstate;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  预约状态
	 */
	public void setAppointmentstate(java.lang.Integer appointmentstate){
		this.appointmentstate = appointmentstate;
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
	 *@return: java.lang.String  会议名称
	 */
	@Column(name ="NAME",nullable=true,length=20)
	public java.lang.String getName(){
		return this.name;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  会议名称
	 */
	public void setName(java.lang.String name){
		this.name = name;
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
	 *@return: java.lang.String  终端分组
	 */
	@Column(name ="RIGHTID",nullable=true,length=100)
	public java.lang.String getRightid(){
		return this.rightid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  终端分组
	 */
	public void setRightid(java.lang.String rightid){
		this.rightid = rightid;
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
