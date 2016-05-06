package com.binwen.sharedemo.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.binwen.sharedemo.R;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXTextObject;
import com.tencent.mm.sdk.modelmsg.WXWebpageObject;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

/**
 * Created by zbw on 2016/4/24.
 */
public class WXEntryActivity extends Activity implements IWXAPIEventHandler {

    private Button share_btn;//reg_btn将自己的app注册到微信的按钮，share_btn进行分享<br>

    private IWXAPI api;

    private final String APP_ID = "wxef97cc733cc33180";

    @Override

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        //注册
        api = WXAPIFactory.createWXAPI(this, APP_ID, false);

        api.registerApp(APP_ID);


        //分享
        share_btn=(Button)findViewById(R.id.button2);

        share_btn.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                share();

             /*   // 初始化一个WXTextObject对象

                WXTextObject textObj = new WXTextObject();



                textObj.text = "hello";

                // 用WXTextObject对象初始化一个WXMediaMessage对象

                WXMediaMessage msg = new WXMediaMessage();

                msg.mediaObject = textObj;

                // 发送文本类型的消息时，title字段不起作用

                // msg.title = "Will be ignored";

                msg.description = "hello";



                // 构造一个Req

                SendMessageToWX.Req req = new SendMessageToWX.Req();

                req.transaction = "transaction"+System.currentTimeMillis(); // transaction字段用于唯一标识一个请求

                req.message = msg;

                req.scene = true ? SendMessageToWX.Req.WXSceneTimeline : SendMessageToWX.Req.WXSceneSession;



                // 调用api接口发送数据到微信

                api.sendReq(req);

                finish();*/

            }

        });

    }



    @Override

    protected void onNewIntent(Intent intent) {

        super.onNewIntent(intent);



        setIntent(intent);

        api.handleIntent(intent, this);

    }



    @Override

    public void onReq(BaseReq baseReq) {
    }



    @Override

    public void onResp(BaseResp baseResp) {



    }


    public void  share(){
        WXWebpageObject webpage = new WXWebpageObject();
        webpage.webpageUrl = "http://www.baidu.com";
        WXMediaMessage msg = new WXMediaMessage(webpage);
        msg.title = "来自超级APP的分享";
        msg.description = "欢迎您使用本APP分享";
        Bitmap thumb = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        msg.thumbData = thumb.getNinePatchChunk();

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = "transaction"+System.currentTimeMillis();
        req.message = msg;
        req.scene =true? SendMessageToWX.Req.WXSceneTimeline : SendMessageToWX.Req.WXSceneSession;
        api.sendReq(req);

        finish();

    }

}

