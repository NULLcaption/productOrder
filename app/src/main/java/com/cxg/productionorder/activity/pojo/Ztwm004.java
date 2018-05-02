package com.cxg.productionorder.activity.pojo;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.io.Serializable;
import java.util.Hashtable;

/**
 * Description: 成品 CS_ZTWM004结构 确认入库传入字段
 * author: xg.chen
 * time: 2018/4/24
 * version: 1.0
 */
@DatabaseTable(tableName = "Ztwm004")
public class Ztwm004  implements Serializable,KvmSerializable {

    @DatabaseField
    private String mandt;
    @DatabaseField
    private String zipcode;
    @DatabaseField
    private String charg;
    @DatabaseField
    private String zcupno;
    @DatabaseField
    private String werks;
    @DatabaseField
    private String zkurno;
    @DatabaseField
    private String zbc;
    @DatabaseField
    private String zlinecode;
    @DatabaseField
    private String matnr;
    @DatabaseField
    private String zproddate;
    @DatabaseField
    private String zinstock;
    @DatabaseField
    private String zoutstock;
    @DatabaseField
    private String mblnr;
    @DatabaseField
    private String mjahr;
    @DatabaseField
    private String menge;
    @DatabaseField
    private String meins;
    @DatabaseField
    private String tanum;
    @DatabaseField
    private String zptflg;
    @DatabaseField
    private String zgrdate;
    @DatabaseField
    private String zlichn;
    @DatabaseField
    private String lifnr;
    @DatabaseField
    private String znum;
    @DatabaseField
    private String zqcnum;

    public String getMandt() {
        return mandt;
    }

    public void setMandt(String mandt) {
        this.mandt = mandt;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCharg() {
        return charg;
    }

    public void setCharg(String charg) {
        this.charg = charg;
    }

    public String getZcupno() {
        return zcupno;
    }

    public void setZcupno(String zcupno) {
        this.zcupno = zcupno;
    }

    public String getWerks() {
        return werks;
    }

    public void setWerks(String werks) {
        this.werks = werks;
    }

    public String getZkurno() {
        return zkurno;
    }

    public void setZkurno(String zkurno) {
        this.zkurno = zkurno;
    }

    public String getZbc() {
        return zbc;
    }

    public void setZbc(String zbc) {
        this.zbc = zbc;
    }

    public String getZlinecode() {
        return zlinecode;
    }

    public void setZlinecode(String zlinecode) {
        this.zlinecode = zlinecode;
    }

    public String getMatnr() {
        return matnr;
    }

    public void setMatnr(String matnr) {
        this.matnr = matnr;
    }

    public String getZproddate() {
        return zproddate;
    }

    public void setZproddate(String zproddate) {
        this.zproddate = zproddate;
    }

    public String getZinstock() {
        return zinstock;
    }

    public void setZinstock(String zinstock) {
        this.zinstock = zinstock;
    }

    public String getZoutstock() {
        return zoutstock;
    }

    public void setZoutstock(String zoutstock) {
        this.zoutstock = zoutstock;
    }

    public String getMblnr() {
        return mblnr;
    }

    public void setMblnr(String mblnr) {
        this.mblnr = mblnr;
    }

    public String getMjahr() {
        return mjahr;
    }

    public void setMjahr(String mjahr) {
        this.mjahr = mjahr;
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

    public String getTanum() {
        return tanum;
    }

    public void setTanum(String tanum) {
        this.tanum = tanum;
    }

    public String getZptflg() {
        return zptflg;
    }

    public void setZptflg(String zptflg) {
        this.zptflg = zptflg;
    }

    public String getZgrdate() {
        return zgrdate;
    }

    public void setZgrdate(String zgrdate) {
        this.zgrdate = zgrdate;
    }

    public String getZlichn() {
        return zlichn;
    }

    public void setZlichn(String zlichn) {
        this.zlichn = zlichn;
    }

    public String getLifnr() {
        return lifnr;
    }

    public void setLifnr(String lifnr) {
        this.lifnr = lifnr;
    }

    public String getZnum() {
        return znum;
    }

    public void setZnum(String znum) {
        this.znum = znum;
    }

    public String getZqcnum() {
        return zqcnum;
    }

    public void setZqcnum(String zqcnum) {
        this.zqcnum = zqcnum;
    }

    @Override
    public String toString() {
        return "Ztwm004{" +
                "mandt='" + mandt + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", charg='" + charg + '\'' +
                ", zcupno='" + zcupno + '\'' +
                ", werks='" + werks + '\'' +
                ", zkurno='" + zkurno + '\'' +
                ", zbc='" + zbc + '\'' +
                ", zlinecode='" + zlinecode + '\'' +
                ", matnr='" + matnr + '\'' +
                ", zproddate='" + zproddate + '\'' +
                ", zinstock='" + zinstock + '\'' +
                ", zoutstock='" + zoutstock + '\'' +
                ", mblnr='" + mblnr + '\'' +
                ", mjahr='" + mjahr + '\'' +
                ", menge='" + menge + '\'' +
                ", meins='" + meins + '\'' +
                ", tanum='" + tanum + '\'' +
                ", zptflg='" + zptflg + '\'' +
                ", zgrdate='" + zgrdate + '\'' +
                ", zlichn='" + zlichn + '\'' +
                ", lifnr='" + lifnr + '\'' +
                ", znum='" + znum + '\'' +
                ", zqcnum='" + zqcnum + '\'' +
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
