package com.example.webtoapp


import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.addCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.webtoapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.webview.settings.javaScriptEnabled=true
        binding.webview.loadUrl("https://www.daraz.com.bd/#hp-flash-sale")

        binding.webview.webViewClient=object :WebViewClient(){

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                binding.progressHorizontal.visibility=View.VISIBLE
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                binding.progressHorizontal.visibility=View.GONE
            }


        }

        binding.webview.webChromeClient=object :WebChromeClient(){

            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
                binding.progressHorizontal.progress=newProgress
            }

        }

        onBackPressedDispatcher.addCallback(this) {
            if (binding.webview.canGoBack()) {
                binding.webview.goBack()
            } else {
                val builder= AlertDialog.Builder(this@MainActivity)
                builder.setIcon(R.drawable.img)
                builder.setTitle("Do you want to exit?")
                builder.setPositiveButton("Yes") { _, _ ->
                    finish()
                }
                builder.setNegativeButton("No"){_,_ ->
                }
                val alertDialog=builder.create()
                alertDialog.show()

            }
        }







    }


}