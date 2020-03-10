package com.hzz.基础逻辑算法;

import net.fxft.common.jdbc.DbId;
import net.fxft.common.jdbc.DbTable;

import java.util.Date;

@DbTable(value = "gps_hisdata.gpsinfo", camelToUnderline = false)
public class GpsInfo {

    private static long serialVersionUID = 1L;

    @DbId
    private long gpsId;
    private long uuid;
    //车终端卡号
    private String simNo;
    private int simNoHash;
    //车牌号
    private long vehicleId;
    //刹车转向等状态
    private int signalState;
    //发送时间
    private Date sendTime;
    //经度
    private double longitude;
    //纬度
    private double latitude;
    //速度
    private double velocity;
    //方向
    private int direction;
    //状态
    private int status;
    //报警位状态
    private int alarmState;
    //里程
    private double mileage;
    //油量
    private double gas;
    //行驶记录仪速度
    private double recordVelocity;
    private double altitude;
    private boolean valid;

    private Date createDate;

    private int abnormalType;

    public GpsInfo() {
    }

    public long getGpsId() {
        return gpsId;
    }

    public void setGpsId(long gpsId) {
        this.gpsId = gpsId;
    }

    public long getUuid() {
        return uuid;
    }

    public void setUuid(long uuid) {
        this.uuid = uuid;
    }

    public int getSimNoHash() {
        return simNoHash;
    }

    public void setSimNoHash(int simNoHash) {
        this.simNoHash = simNoHash;
    }

    public String getSimNo() {
        return simNo;
    }

    public void setSimNo(String value) {
        simNo = value;
    }

    public long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date value) {
        sendTime = value;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double value) {
        longitude = value;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double value) {
        latitude = value;
    }

    public double getVelocity() {
        return velocity;
    }

    public void setVelocity(double value) {
        velocity = value;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int value) {
        direction = value;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int value) {
        status = value;
    }

    public int getAlarmState() {
        return alarmState;
    }

    public void setAlarmState(int value) {
        alarmState = value;
    }

    public double getMileage() {
        return mileage;
    }

    public void setMileage(double value) {
        mileage = value;
    }

    public double getGas() {
        return gas;
    }

    public void setGas(double value) {
        gas = value;
    }

    public double getRecordVelocity() {
        return recordVelocity;
    }

    public void setRecordVelocity(double value) {
        recordVelocity = value;
    }

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public int getSignalState() {
        return signalState;
    }

    public void setSignalState(int signalState) {
        this.signalState = signalState;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }


    public int getAbnormalType() {
        return abnormalType;
    }

    public void setAbnormalType(int abnormalType) {
        this.abnormalType = abnormalType;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("GpsInfoFZold").append(" [");
        sb.append("uuid=").append(uuid);
        sb.append(", createDate=").append(createDate);
        sb.append(", alarmState=").append(alarmState);
        sb.append(", altitude=").append(altitude);
        sb.append(", direction=").append(direction);
        sb.append(", gas=").append(gas);
        sb.append(", latitude=").append(latitude);
        sb.append(", longitude=").append(longitude);
        sb.append(", mileage=").append(mileage);
        sb.append(", vehicleId=").append(vehicleId);
        sb.append(", recordVelocity=").append(recordVelocity);
        sb.append(", sendTime=").append(sendTime);
        sb.append(", simNo=").append(simNo);
        sb.append(", status=").append(status);
        sb.append(", valid=").append(valid);
        sb.append(", velocity=").append(velocity);
        sb.append("]");
        return sb.toString();
    }
}
