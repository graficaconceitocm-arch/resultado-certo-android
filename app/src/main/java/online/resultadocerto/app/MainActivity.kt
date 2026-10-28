package online.resultadocerto.app

import android.app.Activity
import android.os.Bundle
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.view.Gravity
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.webkit.WebSettings
import android.widget.*

class MainActivity : Activity() {
 private lateinit var web: WebView
 private lateinit var progress: ProgressBar
 private lateinit var status: TextView
 private val home = "https://resultadocerto.online/"
 override fun onCreate(savedInstanceState: Bundle?) {
  super.onCreate(savedInstanceState)
  window.statusBarColor = Color.rgb(10, 45, 112)
  window.navigationBarColor = Color.rgb(10, 45, 112)
  val root = LinearLayout(this).apply { orientation = LinearLayout.VERTICAL; setBackgroundColor(Color.WHITE) }
  val header = LinearLayout(this).apply { orientation = LinearLayout.HORIZONTAL; gravity = Gravity.CENTER_VERTICAL; setPadding(18, 8, 10, 8); setBackgroundColor(Color.rgb(18,85,201)) }
  val title = TextView(this).apply { text = "RESULTADO CERTO"; setTextColor(Color.WHITE); textSize = 19f; setTypeface(null, 1) }
  header.addView(title, LinearLayout.LayoutParams(0, 48.dp(), 1f))
  fun action(symbol: String, click: () -> Unit) { val b=TextView(this).apply { text=symbol; textSize=23f; setTextColor(Color.WHITE); gravity=Gravity.CENTER; setOnClickListener { click() } }; header.addView(b, LinearLayout.LayoutParams(44.dp(),48.dp())) }
  action("⌂") { web.loadUrl(home) }
  action("↻") { web.reload() }
  root.addView(header)
  progress = ProgressBar(this, null, android.R.attr.progressBarStyleHorizontal).apply { isIndeterminate=true; visibility=View.GONE }
  root.addView(progress, LinearLayout.LayoutParams(-1,3.dp()))
  status=TextView(this).apply { text="Sem conexão. Verifique sua internet e toque em atualizar."; gravity=Gravity.CENTER; visibility=View.GONE; setTextColor(Color.DKGRAY); setPadding(20,30,20,30) }
  root.addView(status)
  web=WebView(this)
  root.addView(web, LinearLayout.LayoutParams(-1,0,1f))
  web.settings.apply { javaScriptEnabled=true; domStorageEnabled=true; loadsImagesAutomatically=true; useWideViewPort=true; loadWithOverviewMode=true; mixedContentMode=WebSettings.MIXED_CONTENT_NEVER_ALLOW; setSupportMultipleWindows(false) }
  web.webChromeClient=object:WebChromeClient() { override fun onProgressChanged(view:WebView?, newProgress:Int) { progress.visibility=if(newProgress<100) View.VISIBLE else View.GONE } }
  web.webViewClient=object:WebViewClient() {
   override fun shouldOverrideUrlLoading(view:WebView?, request:WebResourceRequest?):Boolean {
    val uri=request?.url ?: return false
    if (uri.scheme == "https" && (uri.host == "resultadocerto.online" || uri.host == "www.resultadocerto.online")) return false
    return try { startActivity(Intent(Intent.ACTION_VIEW,uri)); true } catch (_:Exception) { true }
   }
   override fun onReceivedError(view:WebView?, request:WebResourceRequest?, error:android.webkit.WebResourceError?) { if(request?.isForMainFrame==true) status.visibility=View.VISIBLE }
   override fun onPageFinished(view:WebView?, url:String?) { if(url!=null && url.startsWith("https://resultadocerto.online")) status.visibility=View.GONE }
  }
  setContentView(root)
  if(savedInstanceState==null) web.loadUrl(home) else web.restoreState(savedInstanceState)
 }
 private fun Int.dp():Int=(this*resources.displayMetrics.density).toInt()
 @Deprecated("Deprecated in Java") override fun onBackPressed() { if(web.canGoBack()) web.goBack() else super.onBackPressed() }
 override fun onSaveInstanceState(outState:Bundle) { web.saveState(outState); super.onSaveInstanceState(outState) }
 override fun onDestroy() { web.destroy(); super.onDestroy() }
}
