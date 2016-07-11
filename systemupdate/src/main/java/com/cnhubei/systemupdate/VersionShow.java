package com.cnhubei.systemupdate;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.cnhubei.systemupdate.service.DownLoadService;

/**
 * Created by dy on 2016/5/3.
 */
public class VersionShow {

    public static void showAppVersionCheckDialog(final Activity context, final VersionBean item) {
        if (item == null)
            return;
        if (context == null || context.isFinishing()) {
            return;
        }
        LayoutInflater inflater = LayoutInflater.from(context);
        View v;
        if (item.isForce()) {
            v = inflater.inflate(R.layout.tool_dialog_msg_twobtn_force, null);// 得到加载view
        } else {
            v = inflater.inflate(R.layout.tool_dialog_msg_twobtn, null);// 得到加载view
        }

        TextView tvMsg = (TextView) v.findViewById(R.id.tv_msm);
        tvMsg.setMovementMethod(ScrollingMovementMethod.getInstance());

        StringBuffer sb = new StringBuffer();
        sb.append(context.getString(R.string.sys_updater_version_name) + item.versionname);
        sb.append("\n");
        sb.append(context.getString(R.string.sys_updater_version_descripty) +"\n"+ item.content);
        sb.append("\n");
        tvMsg.setText(sb);

        Button btnNO = (Button) v.findViewById(R.id.btn_no);
        Button btn_ok = (Button) v.findViewById(R.id.btn_ok);

        final CheckBox checkBox = (CheckBox) v.findViewById(R.id.checkBox);

        if(item.isCheckUpdater()){
            checkBox.setVisibility(View.GONE);
        }


        final Dialog dialogCreateAppVersion = new Dialog(context, R.style.Theme_CustomDialog);// 创建自定义样式dialog
        dialogCreateAppVersion.setContentView(v, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));// 设置布局
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                dialogCreateAppVersion.dismiss();
                //开启下载
//                DownLoad.getDownLoad().startServices(item.url);
                Intent intent = new Intent(context, DownLoadService.class);
                intent.putExtra("url", item.url);
                context.startService(intent);
            }
        });
        btnNO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                dialogCreateAppVersion.dismiss();
            }
        });

//        dialogCreateAppVersion.setOnDismissListener(new DialogInterface.OnDismissListener() {
//            @Override
//            public void onDismiss(DialogInterface dialog) {
//                if( !VersionPreferences.getInstance().getIgnore(item.versionname) && !item.isCheckUpdater()){
//                    VersionPreferences.getInstance().setIgnore(checkBox.isChecked(), item.versionname);
//                }
//            }
//        });

        if (item.isForce()) {//如果强制更新，不显示忽略该版本
            dialogCreateAppVersion.setCancelable(false);
        }
        dialogCreateAppVersion.show();
    }
}
