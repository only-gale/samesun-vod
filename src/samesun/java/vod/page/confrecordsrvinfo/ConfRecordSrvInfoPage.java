package vod.page.confrecordsrvinfo;

/**   
 * @Title: Entity
 * @Description: 录制服务器
 * @author gale
 * @date 2014-07-16 14:06:46
 * @version V1.0   
 *
 */
public class ConfRecordSrvInfoPage {
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
	/**RTSP服务器*/
	private java.lang.String rtsp;
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
	public java.lang.String getId() {
		return id;
	}
	public void setId(java.lang.String id) {
		this.id = id;
	}
	public java.lang.String getName() {
		return name;
	}
	public void setName(java.lang.String name) {
		this.name = name;
	}
	public java.lang.String getDescript() {
		return descript;
	}
	public void setDescript(java.lang.String descript) {
		this.descript = descript;
	}
	public java.lang.String getMacaddress() {
		return macaddress;
	}
	public void setMacaddress(java.lang.String macaddress) {
		this.macaddress = macaddress;
	}
	public java.lang.String getIpaddress() {
		return ipaddress;
	}
	public void setIpaddress(java.lang.String ipaddress) {
		this.ipaddress = ipaddress;
	}
	public java.lang.Integer getPort() {
		return port;
	}
	public void setPort(java.lang.Integer port) {
		this.port = port;
	}
	public java.lang.String getPath() {
		return path;
	}
	public void setPath(java.lang.String path) {
		this.path = path;
	}
	public java.lang.String getDisable() {
		return disable;
	}
	public void setDisable(java.lang.String disable) {
		this.disable = disable;
	}
	public java.lang.String getRtsp() {
		return rtsp;
	}
	public void setRtsp(java.lang.String rtsp) {
		this.rtsp = rtsp;
	}
	public java.lang.String getCrtuser() {
		return crtuser;
	}
	public void setCrtuser(java.lang.String crtuser) {
		this.crtuser = crtuser;
	}
	public java.lang.String getCrtuserName() {
		return crtuserName;
	}
	public void setCrtuserName(java.lang.String crtuserName) {
		this.crtuserName = crtuserName;
	}
	public java.util.Date getCreateDt() {
		return createDt;
	}
	public void setCreateDt(java.util.Date createDt) {
		this.createDt = createDt;
	}
	public java.lang.String getModifier() {
		return modifier;
	}
	public void setModifier(java.lang.String modifier) {
		this.modifier = modifier;
	}
	public java.lang.String getModifierName() {
		return modifierName;
	}
	public void setModifierName(java.lang.String modifierName) {
		this.modifierName = modifierName;
	}
	public java.util.Date getModifyDt() {
		return modifyDt;
	}
	public void setModifyDt(java.util.Date modifyDt) {
		this.modifyDt = modifyDt;
	}
	public java.lang.Integer getDelflag() {
		return delflag;
	}
	public void setDelflag(java.lang.Integer delflag) {
		this.delflag = delflag;
	}
	public java.util.Date getDelDt() {
		return delDt;
	}
	public void setDelDt(java.util.Date delDt) {
		this.delDt = delDt;
	}
}
