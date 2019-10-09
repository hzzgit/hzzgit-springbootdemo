package com.hzz.lamdba;

import net.fxft.common.jdbc.DbColumn;
import net.fxft.common.jdbc.DbId;
import net.fxft.common.jdbc.DbTable;

import java.util.Date;

/**
 * 车辆基本静态信息
 * @author admin
 *
 */

//@Entity
//@Table(name="Vehicle")
//@org.hibernate.annotations.Proxy(lazy = false)
//@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@DbTable(value="Vehicle", camelToUnderline = false)
public class VehicleData
{
	@DbId
	@DbColumn(columnName = "vehicleId")
	private long entityId;
	public  long getEntityId() {
		return entityId;
	}
	public  void setEntityId(long value) {
		entityId = value;
	}

	private long vehicleId;

	public long getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(long vehicleId) {
		this.vehicleId = vehicleId;
	}

	//车牌号
//	@Column(name = "plateNo")
	private String plateNo;
	public final String getPlateNo()
	{
		return plateNo;
	}
	public final void setPlateNo(String value)
	{
		plateNo = value;
	}
	//车辆类型,在基础数据表中定义各个车型
//	@Column(name = "VehicleType")
	private String vehicleType;
	//厂商型号
	private String vendor;
	//使用类型
	private String useType;
	//车籍地
	private String region;
	//经营许可
	private String operPermit;
	//所属业户Id,默认为零
//	@Column(name = "memberId",nullable = false)
	private long memberId = 0;
	//通信sim卡号
	private String flowRateNo;
	//3.25 车辆密码用于个人登录app
//	@Column(name = "vehiclePassWord",nullable = false)
	private String vehiclePassWord = "000000";

	public String getVehiclePassWord() {
		return vehiclePassWord;
	}

	public void setVehiclePassWord(String vehiclePassWord) {
		this.vehiclePassWord = vehiclePassWord;
	}
	//private boolean registered;

	//所属行业，在基础数据里定义
//	@Column(name = "industry")
	private String industry;
	/**
	 * 服务开始时间
	 */
	private Date startDate;
	/**
	 * 服务结束时间
	 */
	private Date endDate;
	/**
	 * 音视频通道数量
	 */
	private int videoChannelNum;
	/**
	 * 视频存储器序号，0~11，代表主存储器序号，12~15代表备用存储器序号,多个存储器时，用逗号隔开，如0,1,2
	 */
	private String videoDisk;
	/**
	 * 视频设备通道名称字符串，格式由通道号，名称; 组成的字符串, 如 1,通道1；2,通道2
	 */
	private String videoChannelNames;

	/**
	 * 里程调整
	 */
	private Double mileageAdjustment;

	/**
	 * 百公里油耗
	 */
	private Double fuelConsumption;

	//行驶状态，正常normal，报废scrape，维修maintaining，等等
//	@Column(name = "runStatus")
	private String runStatus;

	//车牌颜色
	private int plateColor;

	//终端Id 终端表 主键id
	private long termId;

	//所属部门ID
	private long depId;
	public final long getDepId()
	{
		return depId;
	}
	public final void setDepId(long value)
	{
		depId = value;
	}

	//发动机号
	private String motorID;
	public final String getMotorID()
	{
		return motorID;
	}
	public final void setMotorID(String value)
	{
		motorID = value;
	}

	//GPS手机卡号
	private String simNo;

	//上级平台的营运类型代码
	private  String  superTransType;

	//吨（座）位
	private  String  seatingCapacity;

	//道路运输证号
	private  String  transserialno;

	//营运线路
	private  String  routeName;

	/**
	 * 服务开始时间
	 * @author Lirenhui
	 */
	private Date serviceStartDate;

	/**
	 * 服务结束时间
	 * @author Lirenhui
	 */
	private Date serviceEndDate;

	private Long createStaff;

	private Long updateStaff;

	private Date updateDate;

	/**
	 * 车辆厂商
	 *
	 */
	private String vehicleManufacturer;

	public String getSimNo() {
		return simNo;
	}
	public void setSimNo(String simNo) {
		this.simNo = simNo;
	}
	public int getPlateColor() {
		return plateColor;
	}
	public void setPlateColor(int plateColor) {
		this.plateColor = plateColor;
	}
	public String getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
	public String getVendor() {
		return vendor;
	}
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	public long getTermId() {
		return termId;
	}
	public void setTermId(long termId) {
		this.termId = termId;
	}
	public String getOperPermit() {
		return operPermit;
	}
	public void setOperPermit(String operPermit) {
		this.operPermit = operPermit;
	}
	public String getUseType() {
		return useType;
	}
	public void setUseType(String useType) {
		this.useType = useType;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public long getMemberId() {
		return memberId;
	}
	public void setMemberId(long memberId) {
		this.memberId = memberId;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public final String getRunStatus()
	{
		return runStatus;
	}
	public final void setRunStatus(String value)
	{
		runStatus = value;
	}
	public final String getIndustry()
	{
		return industry;
	}
	public final void setIndustry(String value)
	{
		industry = value;
	}

	public String getFlowRateNo() {
		return flowRateNo;
	}

	public void setFlowRateNo(String flowRateNo) {
		this.flowRateNo = flowRateNo;
	}

	/**
	 * 视频通道数量
	 */
	public int getVideoChannelNum() {
		return videoChannelNum;
	}

	public void setVideoChannelNum(int videoChannelNum) {
		this.videoChannelNum = videoChannelNum;
	}

	/**
	 * 视频存储器序号，0~11，代表主存储器序号，12~15代表备用存储器序号
	 */
	public String getVideoDisk() {
		return videoDisk;
	}

	public void setVideoDisk(String videoDisk) {
		this.videoDisk = videoDisk;
	}

	public String getVideoChannelNames() {
		return videoChannelNames;
	}

	public void setVideoChannelNames(String videoChannelNames) {
		this.videoChannelNames = videoChannelNames;
	}

	public String getSuperTransType() {
		return superTransType;
	}

	public void setSuperTransType(String superTransType) {
		this.superTransType = superTransType;
	}

	public String getSeatingCapacity() {
		return seatingCapacity;
	}

	public void setSeatingCapacity(String seatingCapacity) {
		this.seatingCapacity = seatingCapacity;
	}

	public String getTransserialno() {
		return transserialno;
	}

	public void setTransserialno(String transserialno) {
		this.transserialno = transserialno;
	}

	public String getRouteName() {
		return routeName;
	}

	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}

	public Date getServiceStartDate() {
		return serviceStartDate;
	}

	public void setServiceStartDate(Date serviceStartDate) {
		this.serviceStartDate = serviceStartDate;
	}

	public Date getServiceEndDate() {
		return serviceEndDate;
	}

	public void setServiceEndDate(Date serviceEndDate) {
		this.serviceEndDate = serviceEndDate;
	}

	public Double getMileageAdjustment() {
		return mileageAdjustment;
	}

	public void setMileageAdjustment(Double mileageAdjustment) {
		this.mileageAdjustment = mileageAdjustment;
	}

	public Double getFuelConsumption() {
		return fuelConsumption;
	}

	public void setFuelConsumption(Double fuelConsumption) {
		this.fuelConsumption = fuelConsumption;
	}

	public Long getCreateStaff() {
		return createStaff;
	}

	public void setCreateStaff(Long createStaff) {
		this.createStaff = createStaff;
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

	public String getVehicleManufacturer() {
		return vehicleManufacturer;
	}

	public void setVehicleManufacturer(String vehicleManufacturer) {
		this.vehicleManufacturer = vehicleManufacturer;
	}

}