package com.cxg.productionorder.activity.utils;

import com.cxg.productionorder.activity.pojo.ZpoGminfo;
import com.cxg.productionorder.activity.pojo.ZpoGsmvt;
import com.cxg.productionorder.activity.pojo.Zswm003;
import com.cxg.productionorder.activity.pojo.Ztwm004;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description: webservice工具类
 * author: xg.chen
 * time: 2018/4/19
 * version: 1.0
 */

public class WebserviceUtils {

    //命名空间
    public static String NAMESPACE = "urn:sap-com:document:sap:soap:functions:mc-style";
    //请求方法名
    public static String METHOD_NAME_001= "ZproductionOrderPalletInfo";//根据托盘编码获取相关的信息
    public static String METHOD_NAME_002= "ZwmRfcIts004";//获取单位
    public static String METHOD_NAME_003= "ZproductionOrderGoodsmvt";//确认入库

    //请求路径
    public static String SOAP_ACTION_001 = NAMESPACE + "/" + METHOD_NAME_001;
    public static String SOAP_ACTION_002 = NAMESPACE + "/" + METHOD_NAME_002;
    public static String SOAP_ACTION_003 = NAMESPACE + "/" + METHOD_NAME_003;

    //请求的webservice路径
    public static final String URL_001 = "http://192.168.0.16:8000/sap/bc/srt/rfc/sap/zproduction_order_pallet_info/700/zproduction_order_pallet_info/binding?sap-client=700&sap-user=rfc&sap-password=poiuyt";
    public static final String URL_002 = "http://192.168.0.16:8000/sap/bc/srt/rfc/sap/zwmits4/700/zwmits4/binding?sap-client=700&sap-user=rfc&sap-password=poiuyt";
    public static final String URL_003 = "http://192.168.0.16:8000/sap/bc/srt/rfc/sap/zproduction_order_goodsmvt/700/zproduction_order_goodsmvt/binding?sap-client=700&sap-user=rfc&sap-password=poiuyt";


    /**
     * 确认入库
     * @param url
     * @param methodName
     * @param zpoGminfo
     * @param zpoGsmvt
     * @param ztwm004
     * @param ivMtart
     * @param ivRaube
     * @return
     */
    public static List<Object> callWebService003(String url, final String methodName, ZpoGminfo zpoGminfo, ZpoGsmvt zpoGsmvt, Ztwm004 ztwm004, String ivMtart, String ivRaube) {
        //返回的结果集
        List<Object> resultList = new ArrayList<>();
        // set up
        SoapObject request = new SoapObject(NAMESPACE, methodName);

        // SoapObject添加参数
        SoapObject itGminfo = new SoapObject("","ItGminfo");
        SoapObject so1 = new SoapObject("","item");
        so1.addProperty("Aufnr",zpoGminfo.getAufnr());
        so1.addProperty("Gltrp",zpoGminfo.getGltrp());
        so1.addProperty("Sobkz",zpoGminfo.getSobkz());
        so1.addProperty("Posnr",zpoGminfo.getPosnr());
        so1.addProperty("Gamng",zpoGminfo.getGamng());
        so1.addProperty("Wemng",zpoGminfo.getWemng());
        so1.addProperty("Objnr",zpoGminfo.getObjnr());
        itGminfo.addSoapObject(so1);

        SoapObject itGsmvt = new SoapObject("","ItGsmvt");
        SoapObject so2 = new SoapObject("","item");
        so2.addProperty("Zipcode",zpoGsmvt.getZipcode());
        so2.addProperty("Zkurno",zpoGsmvt.getZkurno());
        so2.addProperty("Name1",zpoGsmvt.getName1());
        so2.addProperty("Matnr",zpoGsmvt.getMatnr());
        so2.addProperty("Maktx",zpoGsmvt.getMaktx());
        so2.addProperty("Zproddate",zpoGsmvt.getZproddate());
        so2.addProperty("Zcupno",zpoGsmvt.getZcupno());
        so2.addProperty("Lgort",zpoGsmvt.getLgort());
        so2.addProperty("Wsmng",zpoGsmvt.getWsmng());
        so2.addProperty("Menge",zpoGsmvt.getMenge());
        so2.addProperty("Meins",zpoGsmvt.getMeins());
        itGsmvt.addSoapObject(so2);

        SoapObject itZtwm004 = new SoapObject("","ItZtwm004");
        SoapObject so3 = new SoapObject("","item");
        so3.addProperty("Mandt",ztwm004.getMandt());
        so3.addProperty("Zipcode",ztwm004.getZipcode());
        so3.addProperty("Charg",ztwm004.getCharg());
        so3.addProperty("Zcupno",ztwm004.getZcupno());
        so3.addProperty("Zkurno",ztwm004.getZkurno());
        so3.addProperty("Zbc",ztwm004.getZbc());
        so3.addProperty("Zlinecode",ztwm004.getZlinecode());
        so3.addProperty("Zproddate",ztwm004.getZproddate());
        so3.addProperty("Zinstock",ztwm004.getZinstock());
        so3.addProperty("Zoutstock",ztwm004.getZoutstock());
        so3.addProperty("Mblnr",ztwm004.getMblnr());
        so3.addProperty("Mjahr",ztwm004.getMjahr());
        so3.addProperty("Menge",ztwm004.getMenge());
        so3.addProperty("Meins",ztwm004.getMeins());
        so3.addProperty("Tanum",ztwm004.getTanum());
        so3.addProperty("Zptflg",ztwm004.getZptflg());
        so3.addProperty("Zgrdate",ztwm004.getZgrdate());
        so3.addProperty("Zlichn",ztwm004.getZlichn());
        so3.addProperty("Lifnr",ztwm004.getLifnr());
        so3.addProperty("Znum",ztwm004.getZnum());
        so3.addProperty("Zqcnum",ztwm004.getZqcnum());
        itZtwm004.addSoapObject(so3);

        request.addSoapObject(itGminfo);
        request.addSoapObject(itGsmvt);
        request.addSoapObject(itZtwm004);
        request.addProperty("IvMtart", ivMtart);
        request.addProperty("IvRaube", ivRaube);

        // put all required data into a soap
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER10);
        envelope.dotNet = false;
        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransport = new HttpTransportSE(url);
        httpTransport.debug = true;
        try {
            httpTransport.call(SOAP_ACTION_003, envelope);
            if (envelope.bodyIn instanceof SoapObject) { // SoapObject = SUCCESS
                SoapObject soapObject = (SoapObject) envelope.bodyIn;
                //解析后的返回list
                resultList = parseSoapObject003(soapObject);
                return resultList;
            } else if (envelope.bodyIn instanceof SoapFault) {
                SoapFault soapFault = (SoapFault) envelope.bodyIn;
                try {
                    throw new Exception(soapFault.getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (SocketException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultList;
    }

    /**
     * 解析返回值
     * @param result
     * @return
     */
    public static List<Object> parseSoapObject003(SoapObject result) {
        List<Object> list = new ArrayList<>();

        String EvCode = result.getProperty("EvCode").toString();
        String EvMsg = result.getProperty("EvMsg").toString();

        list.add(EvCode);
        list.add(EvMsg);

        return list;
    }

    /**
     * 根据托盘编码获取相关的信息
     * @param url
     * @param methodName
     * @param properties
     * @return
     */
    public static List<Object> callWebService001(String url, final String methodName, String properties) {
        //返回的结果集
        List<Object> resultList = new ArrayList<>();
        // set up
        SoapObject request = new SoapObject(NAMESPACE, methodName);
        // SoapObject添加参数
        request.addProperty("CtGminfo", new ZpoGminfo());
        request.addProperty("CtGsmvt", new ZpoGsmvt());
        request.addProperty("CtZtwm004", new Ztwm004());
        request.addProperty("IvZipcode", properties);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER10); // put all required data into a soap
        envelope.dotNet = false;
        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransport = new HttpTransportSE(url);
        httpTransport.debug = true;
        try {
            httpTransport.call(SOAP_ACTION_001, envelope);
            if (envelope.bodyIn instanceof SoapObject) { // SoapObject = SUCCESS
                SoapObject soapObject = (SoapObject) envelope.bodyIn;
                //解析后的返回list
                resultList = parseSoapObject(soapObject);
                return resultList;
            } else if (envelope.bodyIn instanceof SoapFault) {
                SoapFault soapFault = (SoapFault) envelope.bodyIn;
                try {
                    throw new Exception(soapFault.getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (SocketException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultList;
    }

    /**
     * Soap Object解析返回值
     *
     * @param result 获取到值
     * @return list
     */
    public static List<Object> parseSoapObject(SoapObject result) {
        List<Object> list = new ArrayList<>();

        //ZpoGminfo生订单对象解析
        SoapObject CsGminfo = (SoapObject) result.getProperty("CtGminfo");
        ZpoGminfo zpoGminfo = parseSoapObject4ZpoGminfo(CsGminfo);
        list.add(zpoGminfo);

        //ZpoGsmvt托盘编码获取到的信息
        SoapObject CsGsmvt = (SoapObject) result.getProperty("CtGsmvt");
        ZpoGsmvt zpoGsmvt = parseSoapObject4ZpoGsmvt(CsGsmvt);
        list.add(zpoGsmvt);

        //Ztwm004入库成品信息
        SoapObject CsZtwm004 = (SoapObject) result.getProperty("CtZtwm004");
        Ztwm004 ztwm004 = parseSoapObject4Ztwm004(CsZtwm004);
        list.add(ztwm004);

        String EvCode = result.getProperty("EvCode").toString();
        String EvMsg = result.getProperty("EvMsg").toString();
        String EvMtart = result.getProperty("EvMtart").toString();
        String EvRaube = result.getProperty("EvRaube").toString();

        list.add(EvCode);//返回码：数值0代表成功；数值为非0代表不成功
        list.add(EvMsg);//返回信息
        list.add(EvMtart);//物料类型
        list.add(EvRaube);//存储条件

        return list;
    }

    /**
     * Description: 解析生产订单成品信息
     * author: xg.chen
     * time: 2018/4/24
     * version: 1.0
     */
    public static Ztwm004 parseSoapObject4Ztwm004(SoapObject CsZtwm004){
        Ztwm004 ztwm004 = new Ztwm004();
        if(CsZtwm004 == null){
            return null;
        }
        for (int i = 0; i < CsZtwm004.getPropertyCount(); i++) {
            SoapObject soapObject = (SoapObject) CsZtwm004.getProperty(i);
            String Mandt = soapObject.getProperty("Mandt").toString();
            String Zipcode = soapObject.getProperty("Zipcode").toString();
            String Charg = soapObject.getProperty("Charg").toString();
            String Zcupno = soapObject.getProperty("Zcupno").toString();
            String Werks = soapObject.getProperty("Werks").toString();
            String Zkurno = soapObject.getProperty("Zkurno").toString();
            String Zbc = soapObject.getProperty("Zbc").toString();
            String Zlinecode = soapObject.getProperty("Zlinecode").toString();
            String Matnr = soapObject.getProperty("Matnr").toString();
            String Zproddate = soapObject.getProperty("Zproddate").toString();
            String Zinstock = soapObject.getProperty("Zinstock").toString();
            String Zoutstock = soapObject.getProperty("Zoutstock").toString();
            String Mblnr = soapObject.getProperty("Mblnr").toString();
            String Mjahr = soapObject.getProperty("Mjahr").toString();
            String Menge = soapObject.getProperty("Menge").toString();
            String Meins = soapObject.getProperty("Meins").toString();
            String Tanum = soapObject.getProperty("Tanum").toString();
            String Zptflg = soapObject.getProperty("Zptflg").toString();
            String Zgrdate = soapObject.getProperty("Zgrdate").toString();
            String Zlichn = soapObject.getProperty("Zlichn").toString();
            String Lifnr = soapObject.getProperty("Lifnr").toString();
            String Znum = soapObject.getProperty("Znum").toString();
            String Zqcnum = soapObject.getProperty("Zqcnum").toString();

            String anyType = "anyType{}";
            ztwm004.setMandt((anyType.equals(Mandt))?"":Mandt);
            ztwm004.setZipcode((anyType.equals(Zipcode))?"":Zipcode);
            ztwm004.setCharg((anyType.equals(Charg))?"":Charg);
            ztwm004.setZcupno((anyType.equals(Zcupno))?"":Zcupno);
            ztwm004.setWerks((anyType.equals(Werks))?"":Werks);
            ztwm004.setZkurno((anyType.equals(Zkurno))?"":Zkurno);
            ztwm004.setZbc((anyType.equals(Zbc))?"":Zbc);
            ztwm004.setZlinecode((anyType.equals(Zlinecode))?"":Zlinecode);
            ztwm004.setMatnr((anyType.equals(Matnr))?"":Matnr);
            ztwm004.setZproddate((anyType.equals(Zproddate))?"":Zproddate);
            ztwm004.setZinstock((anyType.equals(Zinstock))?"":Zinstock);
            ztwm004.setZoutstock((anyType.equals(Zoutstock))?"":Zoutstock);
            ztwm004.setMblnr((anyType.equals(Mblnr))?"":Mblnr);
            ztwm004.setMjahr((anyType.equals(Mjahr))?"":Mjahr);
            ztwm004.setMenge((anyType.equals(Menge))?"":Menge);
            ztwm004.setMeins((anyType.equals(Meins))?"":Meins);
            ztwm004.setTanum((anyType.equals(Tanum))?"":Tanum);
            ztwm004.setZptflg((anyType.equals(Zptflg))?"":Zptflg);
            ztwm004.setZgrdate((anyType.equals(Zgrdate))?"":Zgrdate);
            ztwm004.setZlichn((anyType.equals(Zlichn))?"":Zlichn);
            ztwm004.setLifnr((anyType.equals(Lifnr))?"":Lifnr);
            ztwm004.setZnum((anyType.equals(Znum))?"":Znum);
            ztwm004.setZqcnum((anyType.equals(Zqcnum))?"":Zqcnum);
        }
        return ztwm004;
    }

    /**
     * Description: 托盘编码获取到的信息
     * author: xg.chen
     * time: 2018/4/24
     * version: 1.0
     */
    public static ZpoGsmvt parseSoapObject4ZpoGsmvt(SoapObject CsGsmvt) {
        ZpoGsmvt zpoGsmvt = new ZpoGsmvt();
        if(CsGsmvt == null){
            return null;
        }
        for (int i = 0; i < CsGsmvt.getPropertyCount(); i++) {
            SoapObject soapObject = (SoapObject) CsGsmvt.getProperty(i);
            String Zipcode = soapObject.getProperty("Zipcode").toString();
            String Zkurno = soapObject.getProperty("Zkurno").toString();
            String Name1 = soapObject.getProperty("Name1").toString();
            String Matnr = soapObject.getProperty("Matnr").toString();
            String Maktx = soapObject.getProperty("Maktx").toString();
            String Zproddate = soapObject.getProperty("Zproddate").toString();
            String Zcupno = soapObject.getProperty("Zcupno").toString();
            String Lgort = soapObject.getProperty("Lgort").toString();
            String Wsmng = soapObject.getProperty("Wsmng").toString();
            String Menge = soapObject.getProperty("Menge").toString();
            String Meins = soapObject.getProperty("Meins").toString();

            String anyType = "anyType{}";
            zpoGsmvt.setZipcode((anyType.equals(Zipcode))?"":Zipcode);
            zpoGsmvt.setZkurno((anyType.equals(Zkurno))?"":Zkurno);
            zpoGsmvt.setName1((anyType.equals(Name1))?"": Name1);
            zpoGsmvt.setMatnr((anyType.equals(Matnr))?"": Matnr);
            zpoGsmvt.setMaktx((anyType.equals(Maktx))?"": Maktx);
            zpoGsmvt.setZproddate((anyType.equals(Zproddate))?"": Zproddate);
            zpoGsmvt.setZcupno((anyType.equals(Zcupno))?"": Zcupno);
            zpoGsmvt.setLgort((anyType.equals(Lgort))?"": Lgort);
            zpoGsmvt.setWsmng((anyType.equals(Wsmng))?"": Wsmng);
            zpoGsmvt.setMenge((anyType.equals(Menge))?"": Menge);
            zpoGsmvt.setMeins((anyType.equals(Meins))?"": Meins);
        }
        return zpoGsmvt;
    }

    /**
     * Description: poGminfo生订单对象解析
     * author: xg.chen
     * time: 2018/4/24
     * version: 1.0
     */
    public static ZpoGminfo parseSoapObject4ZpoGminfo(SoapObject CsGminfo){
        ZpoGminfo zpoGminfo = new ZpoGminfo();
        if (CsGminfo == null) {
            return null;
        }
        for (int i = 0; i < CsGminfo.getPropertyCount(); i++) {
            SoapObject soapObject = (SoapObject) CsGminfo.getProperty(i);

            String Aufnr = soapObject.getProperty("Aufnr").toString();
            String Gltrp = soapObject.getProperty("Gltrp").toString();
            String Sobkz = soapObject.getProperty("Sobkz").toString();
            String Posnr = soapObject.getProperty("Posnr").toString();
            String Gamng = soapObject.getProperty("Gamng").toString();
            String Wemng = soapObject.getProperty("Wemng").toString();
            String Objnr = soapObject.getProperty("Objnr").toString();

            String anyType = "anyType{}";
            zpoGminfo.setAufnr((anyType.equals(Aufnr))?"": Aufnr );
            zpoGminfo.setGltrp((anyType.equals(Gltrp))?"": Gltrp);
            zpoGminfo.setSobkz((anyType.equals(Sobkz))?"": Sobkz);
            zpoGminfo.setPosnr((anyType.equals(Posnr))?"": Posnr);
            zpoGminfo.setGamng((anyType.equals(Gamng))?"": Gamng);
            zpoGminfo.setWemng((anyType.equals(Wemng))?"": Wemng);
            zpoGminfo.setObjnr((anyType.equals(Objnr))?"": Objnr);
        }
        return zpoGminfo;
    }

    /**
     * 请求ZwmRfcIts004接口获取单位列表
     *
     * @param url        请求URL
     * @param methodName 请求的参数名
     * @return map
     */
    public static Map<String, String> callWebServiceFor002(String url, final String methodName) {
        //返回的结果集
        Map<String, String> map = new HashMap<>();
        // set up
        SoapObject request = new SoapObject(NAMESPACE, methodName);
        // SoapObject添加参数
        Zswm003 zswm003 = new Zswm003();
        request.addProperty("EtZswm003", zswm003);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER10); // put all required data into a soap
        envelope.dotNet = false;
        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransport = new HttpTransportSE(url);
        httpTransport.debug = true;
        try {
            httpTransport.call(SOAP_ACTION_002, envelope);
            if (envelope.bodyIn instanceof SoapObject) {
                SoapObject soapObject = (SoapObject) envelope.bodyIn;
                //解析后的返回list
                map = parseSoapObject004(soapObject);
                return map;
            } else if (envelope.bodyIn instanceof SoapFault) {
                SoapFault soapFault = (SoapFault) envelope.bodyIn;
                try {
                    throw new Exception(soapFault.getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (SocketException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * Soap Object解析返回值
     *
     * @param result 获取到的值
     * @return map
     */
    public static Map<String, String> parseSoapObject004(SoapObject result) {
        Map<String, String> map = new HashMap<>();
        SoapObject provinceSoapObject1 = (SoapObject) result.getProperty("EtZswm003");
        if (provinceSoapObject1 == null) {
            return null;
        }
        for (int i = 0; i < provinceSoapObject1.getPropertyCount(); i++) {
            SoapObject soapObject = (SoapObject) provinceSoapObject1.getProperty(i);
            String Msehi = soapObject.getProperty("Msehi").toString();
            String Mseh3 = soapObject.getProperty("Mseh3").toString();
            map.put(Msehi, Mseh3);
        }
        return map;
    }

}
