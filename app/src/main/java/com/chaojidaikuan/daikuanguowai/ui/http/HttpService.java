package com.chaojidaikuan.daikuanguowai.ui.http;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.QueryMap;

public interface HttpService {
    @GET(ApiConstant.getSmsVerify)
    Observable<String> getSmsVerify(@QueryMap Map<String, String> map);

    @GET(ApiConstant.checkforupdate)
    Observable<String> checkforupdate(@QueryMap Map<String, String> map);

    @GET(ApiConstant.codeLogin)
    Observable<String> codeLogin(@QueryMap Map<String, String> map);

    @GET(ApiConstant.login)
    Observable<String> login(@QueryMap Map<String, String> map);

    @GET(ApiConstant.publicRegister)
    Observable<String> publicRegister(@QueryMap Map<String, String> map);

    @GET(ApiConstant.publicForget)
    Observable<String> publicForget(@QueryMap Map<String, String> map);

    @GET(ApiConstant.userModify_password)
    Observable<String> userModify_password(@QueryMap Map<String, String> map, @Header("token") String token);

    @GET(ApiConstant.homeDashbord)
    Observable<String> homeDashbord(@QueryMap Map<String, String> map, @Header("token") String token);

    @GET(ApiConstant.openDashbord)
    Observable<String> homeDashbord(@QueryMap Map<String, String> map);

    @GET(ApiConstant.bankLists)
    Observable<String> bankLists(@QueryMap Map<String, String> map, @Header("token") String token);

    @GET(ApiConstant.bankUpdate)
    Observable<String> bankUpdate(@QueryMap Map<String, String> map, @Header("token") String token);

    @GET(ApiConstant.bankValidBanks)
    Observable<String> bankValidBanks(@QueryMap Map<String, String> map, @Header("token") String token);

    @GET(ApiConstant.loanapplyLoan)
    Observable<String> loanapplyLoan(@QueryMap Map<String, String> map, @Header("token") String token);

    @GET(ApiConstant.loanget)
    Observable<String> loanget(@QueryMap Map<String, String> map, @Header("token") String token);

    @GET(ApiConstant.userWork)
    Observable<String> userWork(@QueryMap Map<String, String> map, @Header("token") String token);

    @GET(ApiConstant.userContact)
    Observable<String> userContact(@QueryMap Map<String, String> map, @Header("token") String token);

    @GET(ApiConstant.userExtendInfo)
    Observable<String> userExtendInfo(@QueryMap Map<String, String> map, @Header("token") String token);

    @GET(ApiConstant.userGet)
    Observable<String> userGet(@QueryMap Map<String, String> map, @Header("token") String token);

    @GET(ApiConstant.userInfo)
    Observable<String> userInfo(@QueryMap Map<String, String> map, @Header("token") String token);

    @GET(ApiConstant.loanLists)
    Observable<String> loanLists(@QueryMap Map<String, String> map, @Header("token") String token);

    @GET(ApiConstant.userContract)
    Observable<String> userContract(@QueryMap Map<String, String> map, @Header("token") String token);

    @GET(ApiConstant.userstatistics)
    Observable<String> userstatistics(@QueryMap Map<String, String> map);

    @GET(ApiConstant.publicaddress)
    Observable<String> publicaddress();
}