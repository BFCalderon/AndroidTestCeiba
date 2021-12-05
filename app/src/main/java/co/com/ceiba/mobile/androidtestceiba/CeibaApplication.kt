package co.com.ceiba.mobile.androidtestceiba

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Clase mediante la cual Dagger-Hitl obtiene el contexto de la aplicación
 */
@HiltAndroidApp
class CeibaApplication : Application()