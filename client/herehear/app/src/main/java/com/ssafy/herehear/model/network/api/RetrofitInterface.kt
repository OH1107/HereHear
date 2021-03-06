package com.ssafy.herehear.model.network.api

import com.ssafy.herehear.model.network.response.*
import retrofit2.Call
import retrofit2.http.*

interface RetrofitInterface {

    @POST("auth/login")
    @Headers("Content-type: application/json")
    fun login(
        @Body loginbody: LoginRequest
    ): Call<LoginResponse>

    @POST("auth/signup")
    fun signup(
        @Body signupBody: SignupRequest
    ): Call<SignupResponse>

    @GET("books/search")
    fun getSearch(
        @Query("type") type: String,
        @Query("keyword") keyword: String
    ): Call<SearchResponse>

    @GET
    fun getSearchDetail(@Url url: String): Call<SearchDetailResponse>

    @GET("libraries/mine")
    fun getMyLibrary(): Call<GetMyLibraryResponse>

    @GET
    fun getHomeBookDetail(@Url url: String): Call<BookDetailResponse>

    @GET
    fun getAllComments(@Url url: String): Call<AllCommentsResponse>

    @POST
    fun registerBook(
        @Url url: String,
    ): Call<RegisterBookResponse>

    @DELETE
    fun deleteBook(
        @Url url: String
    ): Call<DeleteBookResponse>

    @PUT("libraries")
    fun updateBookStatus(
        @Body bookStatusBody: UpdateBookStatusRequest
    ): Call<UpdateBookStatusResponse>

    @POST
    fun createComment(
        @Url url: String,
        @Body commentBody: CreateCommentRequest
    ): Call<CreateCommentResponse>

    @GET("comment/my")
    fun getMyComments(): Call<MyCommentsResponse>
}

