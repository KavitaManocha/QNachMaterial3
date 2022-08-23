package com.chola.app.data.remote

import com.squareup.moshi.Moshi
import com.chola.app.data.local.SessionManager
import com.chola.app.data.remote.cryptology.DecryptionImpl
import com.chola.app.data.remote.cryptology.EncryptionImpl
import com.chola.app.data.remote.cryptology.intercepter.DecryptionInterceptor
import com.chola.app.data.remote.cryptology.intercepter.EncryptionInterceptor
import com.chola.app.data.remote.moshiFactories.MyKotlinJsonAdapterFactory
import com.example.qnachlocal.BuildConfig
import com.example.qnachlocal.data.remote.moshiFactories.MyStandardJsonAdapters
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Sunil
 */

private const val timeoutRead = 30   //In seconds
private const val contentType = "Content-Type"
private const val contentTypeValue = "application/json"
private const val deviceType = "deviceType"
private const val apiKey = "api-key"
private const val token_ = "Token"
private const val deviceTypeValue = "Android"
private const val timeoutConnect = 30   //In seconds

@Singleton
class ServiceGenerator @Inject constructor(sessionManager: SessionManager) {
    private val okHttpBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
    private val retrofit: Retrofit
    private var token: String = ""


    private var headerInterceptor = Interceptor { chain ->
        if(sessionManager.getUserDetail()!=null){
          //  println("====userDetail========${sessionManager.getUserDetail()}")
            token= sessionManager.getUserDetail()!!.data.token
        }
        val original = chain.request()
        val request = original.newBuilder()
                .header(contentType, contentTypeValue)
                .addHeader(deviceType, deviceTypeValue)
                .addHeader(apiKey,"R3JhbUNvdmVyTnVwYXk=")
                .addHeader(token_,token)
                .method(original.method, original.body)
                .build()

        chain.proceed(request)
    }

    private val logger: HttpLoggingInterceptor
        get() {
            val loggingInterceptor = HttpLoggingInterceptor()
            if (BuildConfig.DEBUG) {
                loggingInterceptor.apply { level = HttpLoggingInterceptor.Level.BODY }
            }
            return loggingInterceptor
        }

    init {
        //Encryption Interceptor
      //  val encryptionInterceptor = EncryptionInterceptor(EncryptionImpl())
        //Decryption Interceptor
      //  val decryptionInterceptor = DecryptionInterceptor(DecryptionImpl())
        okHttpBuilder.addInterceptor(headerInterceptor)
       // okHttpBuilder.addInterceptor(encryptionInterceptor)
       // okHttpBuilder.addInterceptor(decryptionInterceptor)
        okHttpBuilder.addInterceptor(logger)
        okHttpBuilder.connectTimeout(timeoutConnect.toLong(), TimeUnit.SECONDS)
        okHttpBuilder.readTimeout(timeoutRead.toLong(), TimeUnit.SECONDS)
        val client = okHttpBuilder.build()
        retrofit = Retrofit.Builder()
                .baseUrl("https://autonachuat.nupaybiz.com/api/").client(client)
                .addConverterFactory(GsonConverterFactory.create())
               // .addConverterFactory(MoshiConverterFactory.create(getMoshi()))
                .build()
    }

    fun <S> createService(serviceClass: Class<S>): S {
        return retrofit.create(serviceClass)
    }

    private fun getMoshi(): Moshi {
        return Moshi.Builder()
                .add(MyKotlinJsonAdapterFactory())
                .add(MyStandardJsonAdapters.FACTORY)
                .build()
    }
}
