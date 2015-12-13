package com.xiaoliwu.yll.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.xiaoliwu.yll.R;

public class DingDanActivity extends AppCompatActivity {
    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ding_dan);
        webView= (WebView) findViewById(R.id.web2);

        getSupportActionBar().hide();
        init();


//        webView.setWebViewClient(new WebViewClient() {
//            // Load opened URL in the application instead of standard browser
//            // application
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                view.loadUrl("https://buyertrade.taobao.com/trade/itemlist/list_bought_items.htm?spm=a21bo.7724922.1997525045.2.kkmiWl");
//                return true;
//            }
//        });
//
//        webView.setWebChromeClient(new WebChromeClient() {
//            // Set progress bar during loading
//            public void onProgressChanged(WebView view, int progress) {
//                DingDanActivity.this.setProgress(progress * 100);
//            }
//        });
//
//        // Enable some feature like Javascript and pinch zoom
//        WebSettings websettings = webView.getSettings();
//        websettings.setJavaScriptEnabled(true);     // Warning! You can have XSS vulnerabilities!
//        websettings.setBuiltInZoomControls(true);
//
////        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
//        webView.getSettings().setJavaScriptEnabled(true); //设置支持javascript的例子
//
//        //通过WebChromeClient可以处理JS对话框，titles, 进度，等，这个例子，我们处理，我们将websit下载的进度同步到acitity的进度条上。
//
//        webView.setWebChromeClient(new WebChromeClient() {
//
//            public void onProgressChanged(WebView view, int newProgress) {
//
//                //activity的进度是0 to 10000 (both inclusive),所以要*100
//
//                DingDanActivity.this.setProgress(newProgress * 100);
//
//            }
//
//        });
//
//        webView.loadUrl("https://buyertrade.taobao.com/trade/itemlist/list_bought_items.htm?spm=a21bo.7724922.1997525045.2.kkmiWl");
//
//
//
////        startActivity(intent);
//
    }
    private void init(){
        webView = (WebView) findViewById(R.id.web2);
        //WebView加载web资源
        webView.loadUrl("http://h5.m.taobao.com/mlapp/olist.html?spm=a2141.7756461.2.1&tabCode=waitPay");
//        webView.loadUrl("https://item.taobao.com/item.htm?id=39042265048&qq-pf-to=pcqq.temporaryc2c");
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        //启用支持javascript
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                // TODO Auto-generated method stub
                if (newProgress == 100) {
                    // 网页加载完成

                } else {
                    // 加载中

                }

            }
        });
        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });
    }
    //改写物理按键——返回的逻辑
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        // TODO Auto-generated method stub
//        if(keyCode== KeyEvent.KEYCODE_BACK)
//        {
//            if(webView.canGoBack())
//            {
//                webView.goBack();//返回上一页面
//                return true;
//            }
//            else
//            {
//                System.exit(0);//退出程序
//            }
//        }
//        return super.onKeyDown(keyCode, event);
//    }

}
