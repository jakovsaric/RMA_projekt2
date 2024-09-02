package org.unizd.rma.saric

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.unizd.rma.saric.data.datasources.room.RoomMemoriesDataSource
import org.unizd.rma.saric.data.interfaces.MemoriesDataSource
import org.unizd.rma.saric.data.interfaces.MemoriesDatabase
import org.unizd.rma.saric.domain.interfaces.MemoriesRepository
import org.unizd.rma.saric.domain.interfaces.usecases.CreateMemoriesUseCase
import org.unizd.rma.saric.domain.interfaces.usecases.DeleteMemoriesUseCase
import org.unizd.rma.saric.domain.interfaces.usecases.GetAllMemoriesUseCase
import org.unizd.rma.saric.domain.interfaces.usecases.GetMemoriesUseCase
import org.unizd.rma.saric.domain.interfaces.usecases.UpdateMemoriesUseCase
import org.unizd.rma.saric.domain.repositories.MemoriesRepositoryImpl
import org.unizd.rma.saric.domain.usecases.contact.CreateMemoriesUseCaseImpl
import org.unizd.rma.saric.domain.usecases.contact.DeleteMemoriesUseCaseImpl
import org.unizd.rma.saric.domain.usecases.contact.GetAllMemoriesUseCaseImpl
import org.unizd.rma.saric.domain.usecases.contact.GetMemoriesUseCaseImpl
import org.unizd.rma.saric.domain.usecases.contact.UpdateMemoriesUseCaseImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent:: class)
object AppModule {

    @Provides
    @Singleton
    fun providesMemoriesDataSource(
        @ApplicationContext
        context: Context
    ): MemoriesDataSource {
        return RoomMemoriesDataSource(
            dao = Room.databaseBuilder(
                context,
                MemoriesDatabase::class.java,
                MemoriesDatabase.DATABASE_NAME
            ).build().memoriesDao
        )
    }

    @Provides
    @Singleton
    fun providesMemoriesRepository(
        dataSource: MemoriesDataSource
    ): MemoriesRepository {
        return MemoriesRepositoryImpl(dataSource)
    }

    @Provides
    @Singleton
    fun providesGetMemoriesUseCase(
        repository: MemoriesRepository
    ): GetAllMemoriesUseCase {
        return GetAllMemoriesUseCaseImpl(repository)
    }

    @Provides
    @Singleton
    fun providesDeleteMemoriesUseCase(repository: MemoriesRepository): DeleteMemoriesUseCase {
        return DeleteMemoriesUseCaseImpl(repository)
    }

    @Provides
    @Singleton
    fun provideGetMemoriesUseCase(repository: MemoriesRepository): GetMemoriesUseCase {
        return GetMemoriesUseCaseImpl(repository)

    }

    @Provides
    @Singleton
    fun providesUpdateMemoriesUseCase(repository: MemoriesRepository): UpdateMemoriesUseCase {
        return UpdateMemoriesUseCaseImpl(repository)
    }

    @Provides
    @Singleton
    fun providesCreateMemoriesUseCase(
        repository: MemoriesRepository
    ): CreateMemoriesUseCase {
        return CreateMemoriesUseCaseImpl(repository)
    }
}