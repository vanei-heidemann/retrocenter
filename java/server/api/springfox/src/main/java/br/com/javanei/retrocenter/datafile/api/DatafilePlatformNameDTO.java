package br.com.javanei.retrocenter.datafile.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("DatafilePlatformName")
public class DatafilePlatformNameDTO {
    @ApiModelProperty(value = "Platform name", required = true)
    private String platformName;

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    @Override
    public String toString() {
        return platformName;
    }
}
