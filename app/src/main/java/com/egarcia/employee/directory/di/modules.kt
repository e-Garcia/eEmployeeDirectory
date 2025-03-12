package com.egarcia.employee.directory.di

import com.egarcia.employee.directory.data.remote.EmployeeRemoteDataSource
import com.egarcia.employee.directory.data.remote.EmployeeRemoteDataSourceImpl
import com.egarcia.employee.directory.data.remote.api.EmployeeApiService
import com.egarcia.employee.directory.data.repository.EmployeeListRepository
import com.egarcia.employee.directory.data.repository.EmployeeListRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideRemoteDataSource(apiService: EmployeeApiService): EmployeeRemoteDataSourceImpl =
        EmployeeRemoteDataSourceImpl(apiService)

    @Provides
    fun provideEmployeeApiService(retrofit: Retrofit): EmployeeApiService {
        return retrofit.create(EmployeeApiService::class.java)
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindEmployeeListRepository(
        employeeListRepositoryImpl: EmployeeListRepositoryImpl
    ): EmployeeListRepository

    @Binds
    abstract fun bindEmployeeRemoteDataSource(
        employeeRemoteDataSourceImpl: EmployeeRemoteDataSourceImpl
    ): EmployeeRemoteDataSource
}