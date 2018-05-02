package com.cxg.productionorder.activity.pojo;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.io.Serializable;
import java.util.Hashtable;

/**
 * Description: CS_GMINFO结构 确认入库传入字段
 * author: xg.chen
 * time: 2018/4/24
 * version: 1.0
 */

@DatabaseTable(tableName = "ZpoGminfo")
public class ZpoGminfo implements Serializable,KvmSerializable {

    @DatabaseField
    private String aufnr;//订单号
    @DatabaseField
    private String gltrp;//基本完成日期
    @DatabaseField
    private String sobkz;//特殊库存标识
    @DatabaseField
    private String posnr;//订单项目编号
    @DatabaseField
    private String gamng;//订单数量总计
    @DatabaseField
    private String wemng;//此项订单的收货数量
    @DatabaseField
    private String objnr;//对象号

    public String getAufnr() {
        return aufnr;
    }

    public void setAufnr(String aufnr) {
        this.aufnr = aufnr;
    }

    public String getGltrp() {
        return gltrp;
    }

    public void setGltrp(String gltrp) {
        this.gltrp = gltrp;
    }

    public String getSobkz() {
        return sobkz;
    }

    public void setSobkz(String sobkz) {
        this.sobkz = sobkz;
    }

    public String getPosnr() {
        return posnr;
    }

    public void setPosnr(String posnr) {
        this.posnr = posnr;
    }

    public String getGamng() {
        return gamng;
    }

    public void setGamng(String gamng) {
        this.gamng = gamng;
    }

    public String getWemng() {
        return wemng;
    }

    public void setWemng(String wemng) {
        this.wemng = wemng;
    }

    public String getObjnr() {
        return objnr;
    }

    public void setObjnr(String objnr) {
        this.objnr = objnr;
    }

    @Override
    public String toString() {
        return "ZpoGminfo{" +
                "aufnr='" + aufnr + '\'' +
                ", gltrp='" + gltrp + '\'' +
                ", sobkz='" + sobkz + '\'' +
                ", posnr='" + posnr + '\'' +
                ", gamng='" + gamng + '\'' +
                ", wemng='" + wemng + '\'' +
                ", objnr='" + objnr + '\'' +
                '}';
    }

    @Override
    public Object getProperty(int i) {
        return null;
    }

    @Override
    public int getPropertyCount() {
        return 0;
    }

    @Override
    public void setProperty(int i, Object o) {

    }

    @Override
    public void getPropertyInfo(int i, Hashtable hashtable, PropertyInfo propertyInfo) {

    }
}
