package com.example.randomuser.di

import android.content.Context
import androidx.room.Room
import com.example.randomuser.data.api.RandomUserApi
import com.example.randomuser.data.db.AppDatabase
import com.example.randomuser.data.db.UserDao
import com.example.randomuser.data.repository.RandomUserRepositoryImpl
import com.example.randomuser.domain.repository.RandomUserRepository
import com.example.randomuser.domain.usecase.GetAllUsersUseCase
import com.example.randomuser.domain.usecase.GetRandomUserUseCase
import com.example.randomuser.domain.usecase.GetUserByIdUseCase
import com.example.randomuser.domain.usecase.impl.GetAllUsersUseCaseImpl
import com.example.randomuser.domain.usecase.impl.GetRandomUserUseCaseImpl
import com.example.randomuser.domain.usecase.impl.GetUserByIdUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideRandomUserApi(): RandomUserApi {
        return Retrofit.Builder()
            .baseUrl("https://randomuser.me/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RandomUserApi::class.java)
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.inMemoryDatabaseBuilder(
            context,
            AppDatabase::class.java,
//            "app_database"
        ).build()
    }

    @Provides
    fun provideUserDao(database: AppDatabase) = database.userDao()

    @Provides
    @Singleton
    fun provideRandomUserRepository(
        api: RandomUserApi,
        dao: UserDao
    ): RandomUserRepository {
        return RandomUserRepositoryImpl(apiService = api, dao = dao)
    }

    @Provides
    @Singleton
    fun provideGetRandomUserUseCase(repository: RandomUserRepository): GetRandomUserUseCase {
        return GetRandomUserUseCaseImpl(repository)
    }

    @Provides
    @Singleton
    fun provideGetAllUsersUseCase(repository: RandomUserRepository): GetAllUsersUseCase {
        return GetAllUsersUseCaseImpl(repository)
    }

    @Provides
    @Singleton
    fun provideGetUserByIdUseCase(repository: RandomUserRepository): GetUserByIdUseCase {
        return GetUserByIdUseCaseImpl(repository)
    }
}