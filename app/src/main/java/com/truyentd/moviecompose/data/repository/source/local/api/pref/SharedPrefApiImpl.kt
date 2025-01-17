package com.truyentd.moviecompose.data.repository.source.local.api.pref

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.truyentd.moviecompose.data.repository.source.local.api.SharedPrefApi

class SharedPrefApiImpl(context: Context, private val gson: Gson) : SharedPrefApi {
    private val sharedPreferences by lazy {
        try {
            val masterKeyAlias = MasterKey.Builder(context)
                .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                .build()
            EncryptedSharedPreferences.create(
                context,
                PREF_ENCRYPTED_FILE_NAME,
                masterKeyAlias,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM,
            )
        } catch (e: Exception) {
            Log.e(TAG, "$TAG error when create encrypted shared preference")
            // this make sure the app working when some devices is error to generate key.
            context.getSharedPreferences(PREF_NORMAL_FILE_NAME, Context.MODE_PRIVATE)
        }
    }

    override fun registerOnSharedPreferenceChangeListener(
        listener: SharedPreferences.OnSharedPreferenceChangeListener,
    ) {
        sharedPreferences.registerOnSharedPreferenceChangeListener(listener)
    }

    override fun unregisterOnSharedPreferenceChangeListener(
        listener: SharedPreferences.OnSharedPreferenceChangeListener,
    ) {
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(listener)
    }

    override fun <T> put(key: String, data: T) {
        val editor = sharedPreferences.edit()
        when (data) {
            is String -> editor.putString(key, data)
            is Boolean -> editor.putBoolean(key, data)
            is Float -> editor.putFloat(key, data)
            is Int -> editor.putInt(key, data)
            is Long -> editor.putLong(key, data)
            else -> editor.putString(key, gson.toJson(data))
        }
        editor.apply()
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T> get(key: String, type: Class<T>, default: T?): T? {
        return when (type) {
            String::class.java -> sharedPreferences.getString(key, default as? String) as? T
            Boolean::class.java -> java.lang.Boolean.valueOf(
                sharedPreferences.getBoolean(key, default as? Boolean ?: false),
            ) as? T

            Float::class.java -> java.lang.Float.valueOf(
                sharedPreferences.getFloat(key, default as? Float ?: 0f),
            ) as? T

            Int::class.java -> Integer.valueOf(
                sharedPreferences.getInt(key, default as? Int ?: 0),
            ) as? T

            Long::class.java -> java.lang.Long.valueOf(
                sharedPreferences.getLong(key, default as? Long ?: 0L),
            ) as? T

            else -> gson.fromJson(sharedPreferences.getString(key, default as? String), type)
        }
    }

    override fun <T> putList(key: String, list: List<T>) {
        sharedPreferences.edit().putString(key, gson.toJson(list)).apply()
    }

    override fun <T> getList(key: String, clazz: Class<T>): List<T>? {
        val typeOfT = TypeToken.getParameterized(List::class.java, clazz).type
        return gson.fromJson<List<T>>(get(key, String::class.java), typeOfT)
    }

    override fun removeKey(key: String) {
        sharedPreferences.edit().remove(key).apply()
    }

    override fun clear() {
        sharedPreferences.edit().clear().apply()
    }

    companion object {
        private const val TAG = "SharedPrefApi"
    }
}
