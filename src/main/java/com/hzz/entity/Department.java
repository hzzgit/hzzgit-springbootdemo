package com.hzz.entity;


import java.util.Date;

/**
 * 车组/部门类  
 * @author Administrator
 *
 */

public class Department {
	private static final long serialVersionUID = -3005019099093973743L;
	//公司
	public static final String CORP = "corporation";
	//分公司
	public static final String COMPANY = "company";
	//车队
	public static final String DEPARTMENT = "department";


	private long entityId;
	//上级部门id
	private Long parentId;
	//部门名称
	private String name;
	//部门类型 ，参见上面的常量类型定义
	private String type;
	//备注
	private String memNo = "";

	//经营许可证字
	private String roadPermitWord;
	//经营许可证号
	private String roadPermitNo;
	private String region;
	//经营范围
	private String businessScope;
	//联系人
	private String assoMan;
	//联系电话
	private String assoTel;
	//数据更新时间
	private Date updateTime = new Date();
	//车辆上线数量，非数据库字段
//	@Transient
	private int onlineNum ;
	//车辆总数，非数据库字段
//	@Transient
	private int totalNum;

	/**
	 * 更新人
	 * @author Lirenhui
	 */
	private Long updateStaff;

	public Department() {
		this.type = DEPARTMENT;//默认为车队
	}

//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "depId", unique = true, nullable = false)
	public long getEntityId() {
		return this.entityId;
	}

	public void setEntityId(long id) {
		this.entityId = id;
	}

//	@Column(name = "name", unique = true, nullable = false, length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMemNo() {
		return this.memNo;
	}

	public void setMemNo(String memNo) {
		this.memNo = memNo;
	}


	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}


	public String getRoadPermitWord() {
		return this.roadPermitWord;
	}

	public void setRoadPermitWord(String roadPermitWord) {
		this.roadPermitWord = roadPermitWord;
	}

	public String getRoadPermitNo() {
		return this.roadPermitNo;
	}

	public void setRoadPermitNo(String roadPermitNo) {
		this.roadPermitNo = roadPermitNo;
	}


	public String getBusinessScope() {
		return this.businessScope;
	}

	public void setBusinessScope(String businessScope) {
		this.businessScope = businessScope;
	}

	public String getAssoMan() {
		return this.assoMan;
	}

	public void setAssoMan(String assoMan) {
		this.assoMan = assoMan;
	}

	public String getAssoTel() {
		return this.assoTel;
	}

	public void setAssoTel(String assoTel) {
		this.assoTel = assoTel;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

//	@Transient
	public int getOnlineNum() {
		return onlineNum;
	}

	public void setOnlineNum(int onlineNum) {
		this.onlineNum = onlineNum;
	}

//	@Transient
	public int getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public Long getUpdateStaff() {
		return updateStaff;
	}

	public void setUpdateStaff(Long updateStaff) {
		this.updateStaff = updateStaff;
	}
}
