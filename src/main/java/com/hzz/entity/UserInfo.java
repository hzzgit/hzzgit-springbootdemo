package com.hzz.entity;
import java.beans.Transient;
import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * 用户信息
 * @author admin
 *
 */
//@Entity
//@org.hibernate.annotations.Proxy(lazy = false)
//@Table(name = "UserInfo")

public class UserInfo  implements Serializable {
	private static final long serialVersionUID = -4356713910438316680L;

	public static String TYPE_OPERATION = "operation"; 
	public static String TYPE_MONITOR = "monitor"; 

	/**
	 * 超级管理员用户
	 */
	public static final String USER_FLAG_SUPER_ADMIN = "admin";
	/**
	 * 普通用户
	 */
	public static final String USER_FLAG_WEBGIS = "common";

	public static String STATE_SUSPEND = "suspend"; //暂停该用户
	public static String STATE_NORMAL = "normal"; //可用状态


	private long entityId;
	
	//中文姓名
	private String name;
	//唯一登录ID
	private String loginName = "";
	private String password = "";
	//账户状态，停用或正常
	private String userState ;
	//地图中心
	private double mapCenterLat;
	private double mapCenterLng;
	private int mapLevel;
	//地图类型 
	private String mapType;

	private String ip;
	/**
	 * 用户类型，admin代表超级用户
	 */
	private String userType;
	//用户分配的车组部门
	private List<Department> departments = null;

	//所属区域或部门Id
	//private int regionId;
	//联系电话
	private String phoneNo;

	private Date updateTime = new Date();

	/**
	 * 更新人
	 * @author Lirenhui
	 */
	private Long updateStaff;

	/**
	 * 更新时间
	 * @author Lirenhui
	 */
	private Date updateDate;

	//@Transient
	//private String orgNo;

	public UserInfo() {
		userState = STATE_NORMAL;
		userType = TYPE_OPERATION;
		this.mapType = "baidu";
		//this.regionId = 1;
	}
//	@Transient
	public boolean isSuperAdmin()
	{
		return  UserInfo.USER_FLAG_SUPER_ADMIN.equals(this.userType);
	}


	public long getEntityId() {
		return this.entityId;
	}

	public void setEntityId(long id) {
		this.entityId = id;
	}

//	@Column(name = "loginName", unique = true, nullable = false, length = 50)
	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String name) {
		this.loginName = name;
	}

//	@Column(name = "password", nullable = false, length = 16)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
//	@Column(name = "userState")
	public String getUserState() {
		return this.userState;
	}

	public void setUserState(String useState) {
		this.userState = useState;
	}

//	@Transient
	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

//	@ManyToMany(fetch = FetchType.EAGER)
//	@JoinTable(name = "UserDepartment", joinColumns = { @javax.persistence.JoinColumn(name = "userId") }, inverseJoinColumns = { @javax.persistence.JoinColumn(name = "depId") })
	public List<Department> getDepartments() {
//		if (this.departments == null) {
//			String sql = "select b.* from userdepartment a, department b where b.deleted = false and a.depId = b.depId and a.userid = ?";
//			List<Department> deplist = JdbcUtil.getDefault().sql(sql)
//					.addIndexParam(this.getEntityId())
//					.query(Department.class);
//			this.departments = deplist;
//		}
		return this.departments;
	}

	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}


	@Transient
	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getUserType() {
		return this.userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public double getMapCenterLat() {
		return this.mapCenterLat;
	}

	public void setMapCenterLat(double mapCenterLat) {
		this.mapCenterLat = mapCenterLat;
	}


	public int getMapLevel() {
		return this.mapLevel;
	}

	public void setMapLevel(int mapLevel) {
		this.mapLevel = mapLevel;
	}

	public String getMapType() {
		return this.mapType;
	}

	public void setMapType(String mapType) {
		this.mapType = mapType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getMapCenterLng() {
		return mapCenterLng;
	}

	public Long getUpdateStaff() {
		return updateStaff;
	}

	public void setUpdateStaff(Long updateStaff) {
		this.updateStaff = updateStaff;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public void setMapCenterLng(double mapCenterLng) {
		this.mapCenterLng = mapCenterLng;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

}
