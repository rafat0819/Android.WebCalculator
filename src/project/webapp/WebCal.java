package project.webapp;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

@SuppressLint("JavascriptInterface")
public class WebCal extends Activity {
	
	WebView webview=null;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webcalview);
        
        webview = (WebView)findViewById(R.id.webView);
        
        WebSettings websettings = webview.getSettings();
        websettings.setJavaScriptEnabled(true);
        webview.addJavascriptInterface(new WebInterface(this), "Android");
        webview.loadUrl("file:///android_asset/index.html");
    }
}