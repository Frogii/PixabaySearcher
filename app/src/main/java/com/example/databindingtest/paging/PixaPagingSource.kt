package com.example.databindingtest.paging

import androidx.paging.PagingSource
import com.example.databindingtest.retrofit.PixaAPI
import com.example.databindingtest.retrofit.model.Hit
import retrofit2.HttpException
import java.io.IOException

private const val START_PAGE = 1

class PixaPagingSource(
    private val api: PixaAPI,
    private val searchString: String
) : PagingSource<Int, Hit>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Hit> {
        val position = params.key ?: START_PAGE

        return try {
            val response = api.getPhotos(searchString, position)
            if (response.isSuccessful) {
                val photos = checkNotNull(response.body()).hits
                LoadResult.Page(
                    data = photos,
                    prevKey = if (position == START_PAGE) null else position - 1,
                    nextKey = if (photos.isEmpty()) null else position + 1
                )
            } else {
                LoadResult.Error(HttpException(response))
            }
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}