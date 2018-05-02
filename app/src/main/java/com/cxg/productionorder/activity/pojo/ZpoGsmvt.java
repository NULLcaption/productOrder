package com.cxg.productionorder.activity.pojo;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.io.Serializable;
import java.util.Hashtable;

/**
 * Description: 生产订单实体类
 * author: xg.chen
 * time: 2018/4/24
 * version: 1.0
 */

@DatabaseTable(tableName = "ZpoGsmvt")
public class ZpoGsmvt implements Serializable,KvmSerializable {

    @DatabaseField
    private String zipcode;
    @DatabaseField
    private String zkurno;
    @DatabaseField
    private String name1;
    @DatabaseField
    private String matnr;
    @DatabaseField
    private String maktx;
    @DatabaseField
    private String zproddate;
    @DatabaseField
    private String zcupno;
    @DatabaseField
    private String menge;
    @DatabaseField
    private String meins;
    @DatabaseField
    private String lgort;
    @DatabaseField
    private String zinstock;
    @DatabaseField
    private String zgrdate;
    @DatabaseField
    private String mjahr;
    @DatabaseField
    private String mblnr;
    @DatabaseField
    private String Wsmng;

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getZkurno() {
        return zkurno;
    }

    public void setZkurno(String zkurno) {
        this.zkurno = zkurno;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getMatnr() {
        return matnr;
    }

    public void setMatnr(String matnr) {
        this.matnr = matnr;
    }

    public String getMaktx() {
        return maktx;
    }

    public void setMaktx(String maktx) {
        this.maktx = maktx;
    }

    public String getZproddate() {
        return zproddate;
    }

    public void setZproddate(String zproddate) {
        this.zproddate = zproddate;
    }

    public String getZcupno() {
        return zcupno;
    }

    public void setZcupno(String zcupno) {
        this.zcupno = zcupno;
    }

    public String getMenge() {
        return menge;
    }

    public void setMenge(String menge) {
        this.menge = menge;
    }

    public String getMeins() {
        return meins;
    }

    public void setMeins(String meins) {
        this.meins = meins;
    }

    public String getLgort() {
        return lgort;
    }

    public void setLgort(String lgort) {
        this.lgort = lgort;
    }

    public String getZinstock() {
        return zinstock;
    }

    public void setZinstock(String zinstock) {
        this.zinstock = zinstock;
    }

    public String getZgrdate() {
        return zgrdate;
    }

    public void setZgrdate(String zgrdate) {
        this.zgrdate = zgrdate;
    }

    public String getMjahr() {
        return mjahr;
    }

    public void setMjahr(String mjahr) {
        this.mjahr = mjahr;
    }

    public String getMblnr() {
        return mblnr;
    }

    public void setMblnr(String mblnr) {
        this.mblnr = mblnr;
    }

    public String getWsmng() {
        return Wsmng;
    }

    public void setWsmng(String wsmng) {
        Wsmng = wsmng;
    }

    @Override
    public String toString() {
        return "ZpoGsmvt{" +
                "zipcode='" + zipcode + '\'' +
                ", zkurno='" + zkurno + '\'' +
                ", name1='" + name1 + '\'' +
                ", matnr='" + matnr + '\'' +
                ", maktx='" + maktx + '\'' +
                ", zproddate='" + zproddate + '\'' +
                ", zcupno='" + zcupno + '\'' +
                ", menge='" + menge + '\'' +
                ", meins='" + meins + '\'' +
                ", lgort='" + lgort + '\'' +
                ", zinstock='" + zinstock + '\'' +
                ", zgrdate='" + zgrdate + '\'' +
                ", mjahr='" + mjahr + '\'' +
                ", mblnr='" + mblnr + '\'' +
                ", Wsmng='" + Wsmng + '\'' +
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
