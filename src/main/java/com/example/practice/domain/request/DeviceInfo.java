package com.example.practice.domain.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * 设备信息
 *
 * @author : HP
 * @date : 2023/4/20
 */
@Data
@ApiModel("设备信息")
public class DeviceInfo implements Serializable {

    private static final long serialVersionUID = -8640096543574735889L;

    /**
     * 设备名称
     */
    private String deviceName;

    private String deviceModel;

    private String serialNumber;

    private String productionDate;
}
