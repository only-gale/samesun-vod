package vod.entity.appointmentchannelinfo;

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
 * @Description: 预约频道信息
 * @author zhangdaihao
 * @date 2014-06-20 12:00:34
 * @version V1.0   
 *
 */
@Entity
@Table(name = "appointment_channel_info", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class AppointmentChannelInfoEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**预约会议ID*/
	private java.lang.String appointmentid;
	/**会议ID*/
	private java.lang.String meetingid;
	/**主Codec1*/
	private java.lang.String codec1id;
	/**Codec1是否录制*/
	private java.lang.Integer isrecord1;
	/**备Codec2*/
	private java.lang.String codec2id;
	/**Codec2是否录制*/
	private java.lang.Integer isrecord2;
	/**自定义权限组ID*/
	private java.lang.String authortiyGroupCid;
	/**类型*/
	private java.lang.String authortiyGroupType;
	/**分配终端权限组（匿名类）*/
	private java.lang.String authortiyTerminlgroupCid;
	/**用户自定义权限组*/
	private java.lang.String authortiyUsergroupCid;
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
	 *@return: java.lang.String  频道名
	 */
	@Column(name ="APPOINTMENTID",nullable=true,length=36)
	public java.lang.String getAppointmentid() {
		return appointmentid;
	}

	public void setAppointmentid(java.lang.String appointmentid) {
		this.appointmentid = appointmentid;
	}

	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  会议ID
	 */
	@Column(name ="MEETINGID",nullable=true,length=36)
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
	 *@return: java.lang.String  主Codec1
	 */
	@Column(name ="CODEC1ID",nullable=true,length=36)
	public java.lang.String getCodec1id(){
		return this.codec1id;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  主Codec1
	 */
	public void setCodec1id(java.lang.String codec1id){
		this.codec1id = codec1id;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  Codec1是否录制
	 */
	@Column(name ="ISRECORD1",nullable=true,precision=3,scale=0)
	public java.lang.Integer getIsrecord1(){
		return this.isrecord1;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  Codec1是否录制
	 */
	public void setIsrecord1(java.lang.Integer isrecord1){
		this.isrecord1 = isrecord1;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  备Codec2
	 */
	@Column(name ="CODEC2ID",nullable=true,length=36)
	public java.lang.String getCodec2id(){
		return this.codec2id;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  备Codec2
	 */
	public void setCodec2id(java.lang.String codec2id){
		this.codec2id = codec2id;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  Codec2是否录制
	 */
	@Column(name ="ISRECORD2",nullable=true,precision=3,scale=0)
	public java.lang.Integer getIsrecord2(){
		return this.isrecord2;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  Codec2是否录制
	 */
	public void setIsrecord2(java.lang.Integer isrecord2){
		this.isrecord2 = isrecord2;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  自定义权限组ID
	 */
	@Column(name ="AUTHORTIY_GROUP_CID",nullable=true,length=36)
	public java.lang.String getAuthortiyGroupCid(){
		return this.authortiyGroupCid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  自定义权限组ID
	 */
	public void setAuthortiyGroupCid(java.lang.String authortiyGroupCid){
		this.authortiyGroupCid = authortiyGroupCid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  类型
	 */
	@Column(name ="AUTHORTIY_GROUP_TYPE",nullable=true,length=1)
	public java.lang.String getAuthortiyGroupType(){
		return this.authortiyGroupType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  类型
	 */
	public void setAuthortiyGroupType(java.lang.String authortiyGroupType){
		this.authortiyGroupType = authortiyGroupType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  分配终端权限组（匿名类）
	 */
	@Column(name ="AUTHORTIY_TERMINLGROUP_CID",nullable=true,length=36)
	public java.lang.String getAuthortiyTerminlgroupCid(){
		return this.authortiyTerminlgroupCid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  分配终端权限组（匿名类）
	 */
	public void setAuthortiyTerminlgroupCid(java.lang.String authortiyTerminlgroupCid){
		this.authortiyTerminlgroupCid = authortiyTerminlgroupCid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  用户自定义权限组
	 */
	@Column(name ="AUTHORTIY_USERGROUP_CID",nullable=true,length=36)
	public java.lang.String getAuthortiyUsergroupCid(){
		return this.authortiyUsergroupCid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  用户自定义权限组
	 */
	public void setAuthortiyUsergroupCid(java.lang.String authortiyUsergroupCid){
		this.authortiyUsergroupCid = authortiyUsergroupCid;
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
