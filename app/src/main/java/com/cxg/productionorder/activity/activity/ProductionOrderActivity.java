package com.cxg.productionorder.activity.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.AnimationDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.cxg.productionorder.R;
import com.cxg.productionorder.activity.pojo.ZpoGminfo;
import com.cxg.productionorder.activity.pojo.ZpoGsmvt;
import com.cxg.productionorder.activity.pojo.Ztwm004;
import com.cxg.productionorder.activity.provider.DataProviderFactory;
import com.cxg.productionorder.activity.utils.ExitApplication;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Description: 生产订单首页
 * author: xg.chen
 * time: 2018/4/19
 * version: 1.0
 */

public class ProductionOrderActivity extends AppCompatActivity {

    private EditText zipcode, menge, meins, lgort;
    private TextView zkurno, name1, matnr, maktx, zproddate, zcupno, wsmng, zgrdate;
    private Button ok, exit, cancel;
    private Dialog waitingDialog;
    private Dialog overdialog;
    private Map<String, String> map = new HashMap();
    private ZpoGminfo zpoGminfo = new ZpoGminfo();
    private ZpoGsmvt zpoGsmvt = new ZpoGsmvt();
    private Ztwm004 ztwm004 = new Ztwm004();
    private String IvMtart;//物料类型
    private String IvRaube;//存储类型

    //设置常量
    private static final String  MENGE = "80";
    private static final String  MEINS = "箱";
    private static final String  LGORT = "X001";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_production_order);

        //初始化视图
        initViwe();

        //默认值设置
        menge.setText(MENGE);
        meins.setText(MEINS);
        lgort.setText(LGORT);
        Date now = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String date = format.format(now);
        zgrdate.setText(date);

        //初始化视图
        initData();
    }

    /**
     * Description: 初始化视图
     * author: xg.chen
     * time: 2018/4/19
     * version: 1.0
     */
    private void initViwe() {
        //托盘编码
        zipcode = (EditText) findViewById(R.id.zipcode);
        zipcode.setInputType(InputType.TYPE_NULL);//隐藏键盘
        zipcode.setOnEditorActionListener(EnterListener);//回车键监听
        zipcode.addTextChangedListener(textWatcher);//输入完成后监听
        //客户码
        zkurno = (TextView) findViewById(R.id.zkurno);
        //客户名称
        name1 = (TextView) findViewById(R.id.name1);
        //产品代码
        matnr = (TextView) findViewById(R.id.matnr);
        //产品名称
        maktx = (TextView) findViewById(R.id.maktx);
        //生产日期
        zproddate = (TextView) findViewById(R.id.zproddate);
        //批次编号
        zcupno = (TextView) findViewById(R.id.zcupno);
        //未提交数量
        wsmng = (TextView) findViewById(R.id.wsmng);
        //入库日期
        zgrdate = (TextView) findViewById(R.id.zgrdate);

        //入库数量
        menge = (EditText) findViewById(R.id.menge);
        menge.setInputType(InputType.TYPE_NULL);
        //入库单位
        meins = (EditText) findViewById(R.id.meins);
        meins.setInputType(InputType.TYPE_NULL);
        meins.setOnClickListener(BtnClicked);
        //入库地
        lgort = (EditText) findViewById(R.id.lgort);
        lgort.setInputType(InputType.TYPE_NULL);

        findViewById(R.id.ok).setOnClickListener(BtnClicked);
        findViewById(R.id.exit).setOnClickListener(BtnClicked);
        findViewById(R.id.cancel).setOnClickListener(BtnClicked);
    }

    /**
     * Description: 初始化视图
     * author: xg.chen
     * time: 2018/4/19
     * version: 1.0
     */
    private void initData() {
        if(zpoGsmvt != null) {
            zkurno.setText(zpoGsmvt.getZkurno());
            name1.setText(zpoGsmvt.getName1());
            matnr.setText(zpoGsmvt.getMatnr());
            maktx.setText(zpoGsmvt.getMaktx());
            zproddate.setText(zpoGsmvt.getZproddate());
            zcupno.setText(zpoGsmvt.getZcupno());
            wsmng.setText(zpoGsmvt.getWsmng());
        }
        //当扫描以后，自动加载数据
        if (!zipcode.getText().toString().equals("")) {
            try {
                new getOrderTask().execute(zipcode.getText().toString().trim());
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //加载数据获取单位
        if (map.size() == 0) {
            new getMeinsTask().execute();
        }
    }

    /**
     * Description: 加载单位
     * author: xg.chen
     * time: 2018/4/25
     * version: 1.0
     */
    private class getMeinsTask extends AsyncTask<String, Integer, Map<String, String>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showWaitingDialog();
        }

        @Override
        protected Map<String, String> doInBackground(String... params) {
            return DataProviderFactory.getProvider().getMeins();
        }

        @Override
        protected void onPostExecute(Map<String, String> result) {
            dismissWaitingDialog();
            if (result.size() != 0) {
                map = result;
            } else {
                Toast.makeText(getApplicationContext(), "连接超时...退出稍后重试...", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * Description: 扫入托盘编码时的自动键监听
     * author: xg.chen
     * time: 2018/5/2
     * version: 1.0
     */
    private TextWatcher textWatcher = new TextWatcher(){

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (s.length()>=10) {
                try {
                    new getOrderTask().execute(zipcode.getText().toString().trim());
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    };

    /**
     * Description: 扫入托盘编码时的回车键监听
     * author: xg.chen
     * time: 2018/4/19
     * version: 1.0
     */
    private TextView.OnEditorActionListener EnterListener = new TextView.OnEditorActionListener() {

        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if (actionId == EditorInfo.IME_ACTION_SEND || actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_SEARCH ||
                    (event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction()
                    && KeyEvent.KEYCODE_SEARCH == event.getAction())) {
                if (!"".equals(zipcode.getText().toString().trim())) {
                    // 正则判断下是否输入值为数字
                    Pattern p2 = Pattern.compile("\\d");
                    String IZipcode1 = zipcode.getText().toString().trim();
                    Matcher matcher = p2.matcher(IZipcode1);
                    if (matcher.matches()) {
                        Toast.makeText(getApplicationContext(), "请填写准确的托盘编码...", Toast.LENGTH_SHORT).show();
                    }
                    //通过webservice接口获取订单数据
                    new getOrderTask().execute(zipcode.getText().toString().trim());
                } else {
                    Toast.makeText(getApplicationContext(), "请输入托盘编码,然后查询即可!", Toast.LENGTH_SHORT).show();
                }
            }
            return false;
        }
    };

    /**
     * Description:按钮监听
     * author: xg.chen
     * time: 2018/4/19
     * version: 1.0
     */
    private View.OnClickListener BtnClicked = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.meins :
                    overdialog = null;
                    View overdiaView_Meins = View.inflate(ProductionOrderActivity.this,
                            R.layout.dialog_search_msg, null);
                    overdialog = new Dialog(ProductionOrderActivity.this,
                            R.style.dialog_xw);
                    ListView MeinsList = (ListView) overdiaView_Meins
                            .findViewById(R.id.werksList);
                    TextView tv_titleMeins = (TextView) overdiaView_Meins
                            .findViewById(R.id.Title);
                    tv_titleMeins.setText("请选择单位:");
                    List<String> listMeins = new ArrayList<>();
                    for (Map.Entry<String, String> entry : map.entrySet()) {
                        listMeins.add(entry.getValue());//获取单位
                    }
                    SettingAdapterMeins settingAdapterMeins = new SettingAdapterMeins(
                            getApplicationContext(), listMeins);
                    MeinsList.setAdapter(settingAdapterMeins);
                    overdialog.setContentView(overdiaView_Meins);
                    overdialog.setCanceledOnTouchOutside(true);
                    Button overcancelMeins = (Button) overdiaView_Meins
                            .findViewById(R.id.dialog_cancel_btn);
                    overcancelMeins.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            overdialog.cancel();
                        }
                    });
                    overdialog.show();
                    break;
                case R.id.ok:
                    if (!zipcode.getText().toString().equals("")
                            || !menge.getText().toString().equals("")
                            || !meins.getText().toString().equals("")
                            || !lgort.getText().toString().equals("")) {
                        //修改后赋值
                        // 正则判断下是否输入值为数字
                        Pattern p2 = Pattern.compile("\\d");
                        String menge1 = menge.getText().toString().trim();
                        Matcher matcher = p2.matcher(menge1);
                        if (matcher.matches()) {
                            Toast.makeText(getApplicationContext(), "请填写准确的物料码...", Toast.LENGTH_SHORT).show();
                        } else {
                            zpoGsmvt.setMenge(menge.getText().toString());
                        }
                        //单位
                        if (!"".equals(meins.getText().toString().trim())) {
                            if ("个".equals(meins.getText().toString().trim())) {
                                zpoGsmvt.setMeins("GE");
                            } else if ("盒".equals(meins.getText().toString().trim())) {
                                zpoGsmvt.setMeins("HE");
                            } else if ("袋".equals(meins.getText().toString().trim())) {
                                zpoGsmvt.setMeins("DAI");
                            } else if ("公斤".equals(meins.getText().toString().trim())) {
                                zpoGsmvt.setMeins("KG");
                            } else if ("箱".equals(meins.getText().toString().trim())) {
                                zpoGsmvt.setMeins("BOX");
                            } else if ("杯".equals(meins.getText().toString().trim())) {
                                zpoGsmvt.setMeins("BEI");
                            } else if ("套".equals(meins.getText().toString().trim())) {
                                zpoGsmvt.setMeins("TAO");
                            } else if ("锅".equals(meins.getText().toString().trim())) {
                                zpoGsmvt.setMeins("GUO");
                            } else if ("包".equals(meins.getText().toString().trim())) {
                                zpoGsmvt.setMeins("BAO");
                            } else if ("片".equals(meins.getText().toString().trim())) {
                                zpoGsmvt.setMeins("PIA");
                            } else if ("瓶".equals(meins.getText().toString().trim())) {
                                zpoGsmvt.setMeins("PIN");
                            } else if ("提".equals(meins.getText().toString().trim())) {
                                zpoGsmvt.setMeins("TI");
                            } else if ("卷".equals(meins.getText().toString().trim())) {
                                zpoGsmvt.setMeins("JUA");
                            } else {
                                zpoGsmvt.setMeins("BOX");
                            }
                        }
                        zpoGsmvt.setLgort(lgort.getText().toString());
                        Object[] obj = new Object[]{zpoGminfo,zpoGsmvt,ztwm004,IvMtart,IvRaube};
                        try {
                            new creatProductionOrder().execute(obj);
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    } else {
                        Toast.makeText(getApplicationContext(), "托盘编码为空，必填字段为空，不能入库操作，请重新操作！", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.cancel:
                    zkurno.setText(null);
                    name1.setText(null);
                    matnr.setText(null);
                    maktx.setText(null);
                    zproddate.setText(null);
                    zcupno.setText(null);
                    wsmng.setText(null);
                    break;
                case R.id.exit:
                    ExitApplication.getInstance().exit();
                    break;
                default:
                    break;
            }

        }
    };

    /**
     * description: 选择单位
     * author: xg.chen
     * date: 2017/6/26 13:12
     * version: 1.0
     */
    protected class ViewHodlerMeins {
        TextView stringList = null;
    }

    protected void resetViewHolder(ViewHodlerMeins pViewHolder) {
        pViewHolder.stringList.setText(null);
    }

    public class SettingAdapterMeins extends BaseAdapter {
        private List<String> data = new ArrayList<>();
        private LayoutInflater layoutInflater;

        public SettingAdapterMeins(Context context, List<String> data) {
            this.data = data;
            this.layoutInflater = LayoutInflater.from(context);
        }

        public int getCount() {
            return data.size();
        }

        public Object getItem(int position) {
            return data.get(position);
        }

        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHodlerMeins hodler;
            if (convertView == null) {
                // 获取组件布局
                hodler = new ViewHodlerMeins();
                convertView = layoutInflater.inflate(
                        R.layout.dialog_search_list_child, null);
                hodler.stringList = (TextView) convertView
                        .findViewById(R.id.werksName);
                convertView.setTag(hodler);
            } else {
                hodler = (ViewHodlerMeins) convertView.getTag();
                resetViewHolder(hodler);
            }

            hodler.stringList.setText(data.get(position));
            // 绑定数据、以及事件触发
            final int n = position;
            convertView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View arg0) {
                    meins.setText(data.get(n));
                    overdialog.cancel();
                }
            });
            return convertView;
        }
    }

    /**
     * Description：订单确认入库
     * author: xg.chen
     * time: 2018/4/25
     * version: 1.0
     */
    private class creatProductionOrder extends AsyncTask<Object, Integer, List<Object>>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showWaitingDialog();
        }
        @Override
        protected List<Object> doInBackground(Object... params) {
            ZpoGminfo zpoGminfo = (ZpoGminfo) params[0];
            ZpoGsmvt zpoGsmvt = (ZpoGsmvt) params[1];
            Ztwm004 ztwm004 = (Ztwm004) params[2];
            String IvMtart = (String) params[3];
            String IvRaube = (String) params[4];
            return DataProviderFactory.getProvider().creatProductionOrder(zpoGminfo,zpoGsmvt,ztwm004,IvMtart,IvRaube);
        }
        @Override
        protected void onPostExecute(List<Object> result) {
            dismissWaitingDialog();
            if (result.size()!=0) {
                String EvCode = result.get(0).toString();
                if (EvCode.equals("0")) {
                    zipcode.setText(null);
                    zkurno.setText(null);
                    name1.setText(null);
                    matnr.setText(null);
                    maktx.setText(null);
                    zproddate.setText(null);
                    zcupno.setText(null);
                    wsmng.setText(null);
                    Toast.makeText(getApplicationContext(), "入库成功！", Toast.LENGTH_SHORT).show();
                } else if (EvCode.equals("1")) {
                    Toast.makeText(getApplicationContext(), result.get(1).toString(), Toast.LENGTH_SHORT).show();
                } else {

                }
            } else {
                Toast.makeText(getApplicationContext(), "入库失败，网络异常！", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * Description: 根据扫入的条形码获取生产订单信息
     * author: xg.chen
     * time: 2018/4/20
     * version: 1.0
     */
    private class getOrderTask extends AsyncTask<String, Integer, List<Object>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showWaitingDialog();
        }

        @Override
        protected List<Object> doInBackground(String... params) {
            String string = params[0];
            return DataProviderFactory.getProvider().getOrderTask(string);
        }

        @Override
        protected void onPostExecute(List<Object> result) {
            dismissWaitingDialog();
            if (result.size() != 0) {
                IvMtart = result.get(5).toString();//物料类型
                IvRaube = result.get(6).toString();//存储条件
                String EvCode = result.get(3).toString();
                if (EvCode.equals("0")) {
                    zpoGminfo = (ZpoGminfo) result.get(0);
                    //界面显示对象
                    zpoGsmvt = (ZpoGsmvt) result.get(1);
                    if (zpoGsmvt != null) {
                        zkurno.setText(zpoGsmvt.getZkurno());
                        name1.setText(zpoGsmvt.getName1());
                        //产品编码去最后8位
                        matnr.setText(zpoGsmvt.getMatnr().substring(zpoGsmvt.getMatnr().length()-8));
                        maktx.setText(zpoGsmvt.getMaktx());
                        zproddate.setText(zpoGsmvt.getZproddate());
                        zcupno.setText(zpoGsmvt.getZcupno());
                        wsmng.setText(zpoGsmvt.getWsmng());
                    }
                    ztwm004 = (Ztwm004) result.get(2);
                } else if (EvCode.equals("1")) {
                    Toast.makeText(getApplicationContext(), result.get(4).toString(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "获取数据异常！", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getApplicationContext(), "此托盘编码对应的数据为空或者网络异常！", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * description: 加载图片开始
     * author: xg.chen
     * date: 2017/6/26 11:56
     * version: 1.0
     */
    private void showWaitingDialog() {
        if (waitingDialog == null) {

            waitingDialog = new Dialog(this, R.style.TransparentDialog);
            waitingDialog.setContentView(R.layout.login_waiting_dialog);
            DialogInterface.OnShowListener showListener = new DialogInterface.OnShowListener() {
                @Override
                public void onShow(DialogInterface dialog) {
                    ImageView img = (ImageView) waitingDialog.findViewById(R.id.loading);
                    ((AnimationDrawable) img.getDrawable()).start();
                }
            };
            DialogInterface.OnCancelListener cancelListener = new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    // updateButtonLook(false);
                }
            };
            waitingDialog.setOnShowListener(showListener);
            waitingDialog.setCanceledOnTouchOutside(false);
            waitingDialog.setOnCancelListener(cancelListener);
            waitingDialog.show();
        }
    }

    /**
     * description: 加载结束
     * author: xg.chen
     * date: 2017/6/26 11:56
     * version: 1.0
     */
    private void dismissWaitingDialog() {
        if (waitingDialog != null) {
            ImageView img = (ImageView) waitingDialog.findViewById(R.id.loading);
            ((AnimationDrawable) img.getDrawable()).stop();

            waitingDialog.dismiss();
            waitingDialog = null;
        }
    }


}
