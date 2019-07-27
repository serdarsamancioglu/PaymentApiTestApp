package com.serdar.mytestapp.api

import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import javax.net.ssl.*


@Module
class RetrofitModule {
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val trustManager = object : X509TrustManager {
            override fun checkClientTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {}

            override fun checkServerTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {}

            override fun getAcceptedIssuers(): Array<java.security.cert.X509Certificate> {
                return arrayOf()
            }
        }

        val trustAllCerts = arrayOf<TrustManager>(trustManager)

        val sslContext = SSLContext.getInstance("SSL")
        sslContext.init(null, trustAllCerts, java.security.SecureRandom())

        val httpClient = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(object: Interceptor{
                override fun intercept(chain: Interceptor.Chain): Response {
                    val request = chain.request().newBuilder()
                        .addHeader("x-ibm-client-id", "86a04772-b90e-437d-9529-6a87533315a5")
                        .addHeader("x-ibm-client-secret", "D5yE2jE0aG5xG1dR4hC6uI4jP5pT4wY8pA6kY8nG7pH3rM7pR1")
                        .addHeader("accept", "co")
                        .addHeader("content-type", "application/json")
                        .build()
                    return chain.proceed(request)
                }
            })
            .sslSocketFactory(sslContext.socketFactory, trustManager)
            .hostnameVerifier(object: HostnameVerifier {
                override fun verify(hostname: String?, session: SSLSession?): Boolean {
                    return true //Disable SSL verification
                }
            })
            .writeTimeout(20, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .baseUrl("https://sandbox-api.payosy.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()
    }
}