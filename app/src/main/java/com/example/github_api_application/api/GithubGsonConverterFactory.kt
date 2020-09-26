package com.example.github_api_application.api

import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

class GithubGsonConverterFactory : Converter.Factory() {

    override fun responseBodyConverter(type: Type, annotations: Array<Annotation>, retrofit: Retrofit): Converter<ResponseBody, String>? {
        return if(type == String::class.java) {
            Converter {response ->
                response.string()
            }
        } else {
            null
        }
    }

//    override fun responseBodyConverter(type: Type, annotations: Array<out Annotation>?, retrofit: Retrofit?): Converter<ResponseBody, *>? {
//        if (type == String::class.java) {
//            return StringResponseBodyConverter
//        }
//        return null
//    }
}

//object StringResponseBodyConverter : Converter<ResponseBody, String> {
//    override fun convert(value: ResponseBody): String {
//        return value.string()
//    }
//}
