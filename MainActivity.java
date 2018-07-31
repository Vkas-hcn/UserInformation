package com.geeya.demo.user.userinformation;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.shcmcc.tools.GetSysInfo;
import com.shcmcc.tools.SetSysInfo;
import com.shcmcc.tools.UserAccountInfo;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.serialnumber)
    TextView serialnumber;
    @BindView(R.id.model)
    TextView model;
    @BindView(R.id.login)
    TextView login;
    @BindView(R.id.username)
    TextView username;
    @BindView(R.id.logintoken)
    TextView logintoken;
    @BindView(R.id.CopyrightId)
    TextView CopyrightId;
    @BindView(R.id.ProvincialCode)
    TextView ProvincialCode;
    @BindView(R.id.cd_id)
    TextView cdId;
    @BindView(R.id.BillingID)
    TextView BillingID;
    @BindView(R.id.number_15)
    TextView number15;
    @BindView(R.id.box_infor)
    Button boxInfor;
    @BindView(R.id.Authenaddress)
    TextView Authenaddress;
    @BindView(R.id.Authenusername)
    TextView Authenusername;
    @BindView(R.id.password)
    TextView password;
    @BindView(R.id.box_autheninfor)
    Button boxAutheninfor;
    @BindView(R.id.firmware)
    TextView firmware;
    @BindView(R.id.set_uplogin)
    Button setUplogin;
    @BindView(R.id.setuserid)
    EditText setuserid;
    @BindView(R.id.settoken)
    EditText settoken;
    @BindView(R.id.set_BillingID)
    EditText setBillingID;
    @BindView(R.id.firmwareversion)
    TextView firmwareversion;
    private GetSysInfo sysInfo;
    private SetSysInfo setSysInfo;
    private UserAccountInfo userAccountInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        sysInfo = GetSysInfo.getInstance("10086", "", MainActivity.this);
        setSysInfo = SetSysInfo.getInstance(MainActivity.this, "10086", "");
        userAccountInfo = UserAccountInfo.getInstance(MainActivity.this);
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 11:
                    firmwareversion.setText(sysInfo.getFirmwareVersion());
                    serialnumber.setText(sysInfo.getSnNum());
                    Log.i("sys", sysInfo.getSnNum());
                    model.setText(sysInfo.getTerminalType());
                    firmware.setText(sysInfo.getHardwareVersion());
                    login.setText("" + sysInfo.isEpgLogined());
                    username.setText(sysInfo.getEpgUserId());
                    logintoken.setText(sysInfo.getEpgToken());
                    CopyrightId.setText(sysInfo.getEpgCopyrightId());
                    ProvincialCode.setText(sysInfo.getEpgProvince());
                    cdId.setText(sysInfo.getEpgCpCode());
                    BillingID.setText(sysInfo.getEpgAccountIdentity());
                    number15.setText(sysInfo.getDeviceId());
                    break;
                case 22:
                    Authenusername.setText(userAccountInfo.getLoginAcount());
                    password.setText(userAccountInfo.getLoginPassword());
                    break;
                case 33:
                    if (setuserid != null) {
                        setSysInfo.setEpgUserId(setuserid.getText().toString());
                    }
                    if (settoken != null) {
                        setSysInfo.setEpgToken(settoken.getText().toString());
                    }
                    if (setBillingID != null) {
                        setSysInfo.setEpgAccountIdentity(setBillingID.getText().toString());
                    }
                    break;
            }
        }
    };

    @OnClick({R.id.box_infor, R.id.box_autheninfor, R.id.set_uplogin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.box_infor:
                Message message1 = new Message();
                message1.what = 11;
                mHandler.sendMessage(message1);
                break;
            case R.id.box_autheninfor:
                Message message2 = new Message();
                message2.what = 22;
                mHandler.sendMessage(message2);
                break;
            case R.id.set_uplogin:
                Message message3 = new Message();
                message3.what = 33;
                mHandler.sendMessage(message3);
                break;
        }
    }
}
