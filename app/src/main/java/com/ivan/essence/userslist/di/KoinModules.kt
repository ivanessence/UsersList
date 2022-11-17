package com.ivan.essence.userslist.di

import com.ivan.essence.userslist.BuildConfig
import com.ivan.essence.userslist.data.network.AppAPI
import com.ivan.essence.userslist.data.repositories.RemoteRepositoryImpl
import com.ivan.essence.userslist.domain.repositories.RemoteRepository
import com.ivan.essence.userslist.domain.usecases.PostsUseCase
import com.ivan.essence.userslist.domain.usecases.UsersUseCase
import com.ivan.essence.userslist.presentation.fragments.postDetails.UserDetailsViewModel
import com.ivan.essence.userslist.presentation.fragments.users.UsersViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val BASE_URL = "https://my-json-server.typicode.com"
const val TIMEOUT = 10L

val networkModule = module {
    fun provideOkHttp(): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
        .callTimeout(TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(TIMEOUT, TimeUnit.SECONDS)
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.BODY
        })
        .build()

    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        baseUrl: String,
    ): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun provideCashApi(retrofit: Retrofit): AppAPI = retrofit.create(AppAPI::class.java)

    single { provideOkHttp() }
    single { provideRetrofit(okHttpClient = get(), BASE_URL) }
    single { provideCashApi(retrofit = get()) }
}

val viewModelsModule = module {
    viewModel { UsersViewModel(usersUseCase = get(), postsUseCase = get()) }
    viewModel { UserDetailsViewModel() }
}

val repositoriesModule = module {
    single<RemoteRepository> { RemoteRepositoryImpl(api = get()) }
}

val useCasesModule = module {
    factory { UsersUseCase(remoteRepository = get()) }
    factory { PostsUseCase(remoteRepository = get()) }
}
