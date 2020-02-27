package com.shinplest.airbnbclone.src.search.interfaces;

import com.shinplest.airbnbclone.src.main.models.DefaultResponse;
import com.shinplest.airbnbclone.src.search.models.ExistLocationResponse;
import com.shinplest.airbnbclone.src.search.models.RequestSaveHouse;
import com.shinplest.airbnbclone.src.search.models.SimpleHouseInfoResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;


public interface SearchRetrofitInterface{
    @GET("/houses")
    Call<SimpleHouseInfoResponse> getSimpleHouseInfo(@QueryMap Map<String, String> querymap);

    @GET("/search")
    Call<ExistLocationResponse> getExistLocation(@Query("category") String category, @Query("search") String searchWord);

    //숙소 저장하는 API
    @POST("/users/{userNo}/saveList")
    Call<DefaultResponse> postHouseSave(@Path("userNo") int userNo, @Body RequestSaveHouse requestSaveHouse);

}




//public interface SearchRetrofitInterface {
//    @GET("/houses")
//    Call<SimpleHouseInfoResponse> getSimpleHouseInfo(@Query("search") String search,
//                                                     @Query("guest") int guest,
//                                                     @Query("houseType") String houseType,
//                                                     @Query("bed") int bed,
//                                                     @Query("room") int room,
//                                                     @Query("bathroom") int bathroom,
//                                                     @Query("facilities") String facilities,
//                                                     @Query("buildingType") String buildingType,
//                                                     @Query("rule") String rule,
//                                                     @Query("location") String location,
//                                                     @Query("language") String language);
//}