package vod.entity.meetinglog;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;

/**   
 * @Title: Entity
 * @Description: 会议日志
 * @author zhangdaihao
 * @date 2014-06-17 16:38:42
 * @version V1.0   
 *
 */
@Entity
@Table(name = "meeting_log", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class MeetingLogEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**终端ID*/
	private java.lang.String edgeid;
	/**终端MAC*/
	private java.lang.String edgemac;
	/**终端名称*/
	private java.lang.String edgename;
	/**当前状态*/
	private java.lang.Integer state;
	/**会议ID*/
	private java.lang.String meetingid;
	/**是否是直播*/
	private java.lang.String isliveflag;
	/**会议主题*/
	private java.lang.String subject;
	/**日期*/
	private java.util.Date date;
	/**观看时间*/
	private java.lang.Integer times;
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
	 *@return: java.lang.String  终端ID
	 */
	@Column(name ="EDGEID",nullable=false,length=36)
	public java.lang.String getEdgeid(){
		return this.edgeid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  终端ID
	 */
	public void setEdgeid(java.lang.String edgeid){
		this.edgeid = edgeid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  终端MAC
	 */
	@Column(name ="EDGEMAC",nullable=false,length=20)
	public java.lang.String getEdgemac(){
		return this.edgemac;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  终端MAC
	 */
	public void setEdgemac(java.lang.String edgemac){
		this.edgemac = edgemac;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  终端名称
	 */
	@Column(name ="EDGENAME",nullable=true,length=20)
	public java.lang.String getEdgename(){
		return this.edgename;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  终端名称
	 */
	public void setEdgename(java.lang.String edgename){
		this.edgename = edgename;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  当前状态
	 */
	@Column(name ="STATE",nullable=true,precision=10,scale=0)
	public java.lang.Integer getState(){
		return this.state;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  当前状态
	 */
	public void setState(java.lang.Integer state){
		this.state = state;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  会议ID
	 */
	@Column(name ="MEETINGID",nullable=true,length=20)
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
	 *@return: java.lang.String  是否是直播
	 */
	@Column(name ="ISLIVEFLAG",nullable=true,length=1)
	public java.lang.String getIsliveflag(){
		return this.isliveflag;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否是直播
	 */
	public void setIsliveflag(java.lang.String isliveflag){
		this.isliveflag = isliveflag;
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
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  日期
	 */
	@Column(name ="DATE",nullable=false)
	public java.util.Date getDate(){
		return this.date;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  日期
	 */
	public void setDate(java.util.Date date){
		this.date = date;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  观看时间
	 */
	@Column(name ="TIMES",nullable=true,precision=10,scale=0)
	public java.lang.Integer getTimes(){
		return this.times;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  观看时间
	 */
	public void setTimes(java.lang.Integer times){
		this.times = times;
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
