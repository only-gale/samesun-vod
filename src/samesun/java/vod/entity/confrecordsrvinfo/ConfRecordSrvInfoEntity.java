package vod.entity.confrecordsrvinfo;

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
 * @Description: 录制服务器
 * @author zhangdaihao
 * @date 2014-07-11 14:06:46
 * @version V1.0   
 *
 */
@Entity
@Table(name = "conf_recordsrv_info", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class ConfRecordSrvInfoEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**录制服务器名称*/
	private java.lang.String name;
	/**描述*/
	private java.lang.String descript;
	/**mac地址*/
	private java.lang.String macaddress;
	/**IP地址*/
	private java.lang.String ipaddress;
	/**端口*/
	private java.lang.Integer port;
	/**相对目录*/
	private java.lang.String path;
	/**是否启用*/
	private java.lang.String disable;
	/**创建人*/
	private java.lang.String crtuser;
	/**创建人名字*/
	private java.lang.String crtuserName;
	/**创建时间*/
	private java.util.Date createDt;
	/**修改人*/
	private java.lang.String modifier;
	/**修改人名字*/
	private java.lang.String modifierName;
	/**修改时间*/
	private java.util.Date modifyDt;
	/**删除标记*/
	private java.lang.Integer delflag;
	/**删除时间*/
	private java.util.Date delDt;
	
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
	 *@return: java.lang.String  录制服务器名称
	 */
	@Column(name ="NAME",nullable=false,length=20)
	public java.lang.String getName(){
		return this.name;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  录制服务器名称
	 */
	public void setName(java.lang.String name){
		this.name = name;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  描述
	 */
	@Column(name ="DESCRIPT",nullable=false,length=20)
	public java.lang.String getDescript(){
		return this.descript;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  描述
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
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  端口
	 */
	@Column(name ="PORT",nullable=false,precision=19,scale=0)
	public java.lang.Integer getPort(){
		return this.port;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  端口
	 */
	public void setPort(java.lang.Integer port){
		this.port = port;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  相对目录
	 */
	@Column(name ="PATH",nullable=false,length=20)
	public java.lang.String getPath(){
		return this.path;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  相对目录
	 */
	public void setPath(java.lang.String path){
		this.path = path;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否启用
	 */
	@Column(name ="DISABLE",nullable=false,length=1)
	public java.lang.String getDisable(){
		return this.disable;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否启用
	 */
	public void setDisable(java.lang.String disable){
		this.disable = disable;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人
	 */
	@Column(name ="CRTUSER",nullable=true,length=36)
	public java.lang.String getCrtuser(){
		return this.crtuser;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人
	 */
	public void setCrtuser(java.lang.String crtuser){
		this.crtuser = crtuser;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人名字
	 */
	@Column(name ="CRTUSER_NAME",nullable=true,length=32)
	public java.lang.String getCrtuserName(){
		return this.crtuserName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人名字
	 */
	public void setCrtuserName(java.lang.String crtuserName){
		this.crtuserName = crtuserName;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  创建时间
	 */
	@Column(name ="CREATE_DT",nullable=true)
	public java.util.Date getCreateDt(){
		return this.createDt;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  创建时间
	 */
	public void setCreateDt(java.util.Date createDt){
		this.createDt = createDt;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  修改人
	 */
	@Column(name ="MODIFIER",nullable=true,length=36)
	public java.lang.String getModifier(){
		return this.modifier;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  修改人
	 */
	public void setModifier(java.lang.String modifier){
		this.modifier = modifier;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  修改人名字
	 */
	@Column(name ="MODIFIER_NAME",nullable=true,length=32)
	public java.lang.String getModifierName(){
		return this.modifierName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  修改人名字
	 */
	public void setModifierName(java.lang.String modifierName){
		this.modifierName = modifierName;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  修改时间
	 */
	@Column(name ="MODIFY_DT",nullable=true)
	public java.util.Date getModifyDt(){
		return this.modifyDt;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  修改时间
	 */
	public void setModifyDt(java.util.Date modifyDt){
		this.modifyDt = modifyDt;
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
	@Column(name ="DEL_DT",nullable=true)
	public java.util.Date getDelDt(){
		return this.delDt;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  删除时间
	 */
	public void setDelDt(java.util.Date delDt){
		this.delDt = delDt;
	}
}
