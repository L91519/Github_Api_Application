package com.example.github_api_application.api

import okhttp3.ResponseBody
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

/**
 * Created by tong on 2017/12/25.
 */
class GithubGsonConverterFactory() : Converter.Factory() {
    override fun responseBodyConverter(type: Type, annotations: Array<out Annotation>?, retrofit: Retrofit?): Converter<ResponseBody, *>? {
        if (type == String::class.java) {
            return StringResponseBodyConverter
        }
        return null
    }
}

object StringResponseBodyConverter : Converter<ResponseBody, String> {
    override fun convert(value: ResponseBody): String {
        return value.string()
    }
}

object JSONObjectResponseBodyConverter : Converter<ResponseBody, JSONObject> {
    override fun convert(value: ResponseBody): JSONObject {
        return JSONObject(value.string())
    }
}

object JSONArrayResponseBodyConverter : Converter<ResponseBody, JSONArray> {
    override fun convert(value: ResponseBody): JSONArray {
        return JSONArray(value.string())
    }
}
