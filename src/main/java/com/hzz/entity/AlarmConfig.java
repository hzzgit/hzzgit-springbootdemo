package com.hzz.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author DELL
 * 报警参数配置，配置某一类型的报警，是否启动，是否弹窗，是否有声音，是否统计起止时间
 */

@TableName("alarmconfig")
@Data
public class AlarmConfig implements Serializable {
    public AlarmConfig() {
		risklevel = "none";
    }
    @TableId
	private Integer id;
	private Integer depid;
	private String alarmsource;
	private String alarmtype;
	private Integer enabled;
	private String name;
	private Integer popupenabled;
	private Integer soundenabled;
	private String parent;
	private Integer statisticenabled;
	private Integer alarmonce;
	private String takepicturechannels;
	private String videomonitorchannels;
	private String risklevel;
	private Integer autoprocess;
	private Integer points;
	private String textfordriver;
	private String remark;
}
