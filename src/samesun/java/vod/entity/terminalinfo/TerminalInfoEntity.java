package vod.entity.terminalinfo;

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
 * @Description: 终端信息
 * @author zhangdaihao
 * @date 2014-07-11 18:52:33
 * @version V1.0   
 *
 */
@Entity
@Table(name = "terminal_info", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class TerminalInfoEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**终端名*/
	private java.lang.String name;
	/**终端描述*/
	private java.lang.String descript;
	/**mac地址*/
	private java.lang.String macaddress;
	/**IP地址*/
	private java.lang.String ipaddress;
	/**地理位置*/
	private java.lang.String groupid;
	/**当前状态*/
	private java.lang.Integer state;
	/**正在观看节目*/
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
	 *@return: java.lang.String  终端名
	 */
	@Column(name ="NAME",nullable=false,length=20)
	public java.lang.String getName(){
		return this.name;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  终端名
	 */
	public void setName(java.lang.String name){
		this.name = name;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  终端描述
	 */
	@Column(name ="DESCRIPT",nullable=false,length=20)
	public java.lang.String getDescript(){
		return this.descript;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  终端描述
	 */
	public void setDescript(java.lang.String descript){
		this.descript = descript;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  mac地址
	 */
	@Column(name ="MACADDRESS",nullable=false,length=20)
	public java.lang.String getMacaddress(){
		return this.macaddress;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  mac地址
	 */
	public void setMacaddress(java.lang.String macaddress){
		this.macaddress = macaddress;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  IP地址
	 */
	@Column(name ="IPADDRESS",nullable=false,length=20)
	public java.lang.String getIpaddress(){
		return this.ipaddress;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  IP地址
	 */
	public void setIpaddress(java.lang.String ipaddress){
		this.ipaddress = ipaddress;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  地理位置
	 */
	@Column(name ="GROUPID",nullable=false,length=36)
	public java.lang.String getGroupid(){
		return this.groupid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  地理位置
	 */
	public void setGroupid(java.lang.String groupid){
		this.groupid = groupid;
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
	 *@return: java.lang.String  正在观看节目
	 */
	@Column(name ="NOWVIDEO",nullable=true,length=20)
	public java.lang.String getNowvideo(){
		return this.nowvideo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  正在观看节目
	 */
	public void setNowvideo(java.lang.String nowvideo){
		this.nowvideo = nowvideo;
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
