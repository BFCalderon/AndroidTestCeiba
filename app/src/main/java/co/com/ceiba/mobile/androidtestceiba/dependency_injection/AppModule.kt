package co.com.ceiba.mobile.androidtestceiba.dependency_injection

import android.app.Application
import androidx.room.Room
import co.com.ceiba.mobile.androidtestceiba.common.EndPoints
import co.com.ceiba.mobile.androidtestceiba.data.local.database.CeibaDatabase
import co.com.ceiba.mobile.androidtestceiba.data.remote.CeibaApi
import co.com.ceiba.mobile.androidtestceiba.data.repository_implementation.UsersRepositoryImplementation
import co.com.ceiba.mobile.androidtestceiba.domain.repository_definition.UsersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Define las dependencias a inyectar
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

  /**
   * Provee la instancia del api
   */
  @Provides
  @Singleton
  fun provideCeibaApi(): CeibaApi {
    return Retrofit.Builder()
      .baseUrl(EndPoints.URL_BASE)
      .addConverterFactory(GsonConverterFactory.create())
      .build()
      .create(CeibaApi::class.java)
  }

  /**
   * Provee la instancia singleton de la base de datos
   */
  @Provides
  @Singleton
  fun provideCeibaDatabase(context : Application) : CeibaDatabase {
    synchronized(this) {
      return Room.databaseBuilder(
        context, CeibaDatabase::class.java, CeibaDatabase.DATABASE_NAME
      ).build()
    }
  }

  /**
   * Provee la instancia de la implementacion del repositorio
   */
  @Provides
  @Singleton
  fun provideUsersRepositoryImplementation(
    ceibaApi : CeibaApi,
    ceibaDatabase : CeibaDatabase
  ) : UsersRepository {
    return UsersRepositoryImplementation(ceibaApi, ceibaDatabase.userDao, ceibaDatabase.postDao)
  }
}