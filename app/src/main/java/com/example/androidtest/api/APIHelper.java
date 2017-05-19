package com.example.androidtest.api;

import com.example.androidtest.web_responses.search_resturant.SearchResturantResponse;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Muhammad Abid on 11/28/2016.
 */

public class APIHelper {

    //   heyFit developemnt api
//    private static final String BASE_URL = "https://heyfitapp.com/app/public/api/";
    private static final String BASE_URL = "https://api.yelp.com/v3/";
//    private static final String BASE_URL = "http://192.168.1.108/HeyFitApp/public/api/";


    private static APIHelper instance = new APIHelper();
    private heyFitAPI retfit;

    private APIHelper() {
        Retrofit retrofit = createAdapter().build();
        retfit = retrofit.create(heyFitAPI.class);
    }

    public static APIHelper getInstance() {
        return instance;
    }

    private Retrofit.Builder createAdapter() {


        // Define the interceptor, add authentication headers
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC)
                .setLevel(HttpLoggingInterceptor.Level.HEADERS)
                .setLevel(HttpLoggingInterceptor.Level.BODY);

        // Add the interceptor to OkHttpClient
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor)
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS).build();

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create());
    }

    public Call<SearchResturantResponse> getResturantsList(double latitude, double longitude) {
        return retfit.getResturantsList("Bearer s26VOhWNmbGl-uYAN7m4px62_jpyRol-VCSFfj3u0AditddjW5QljS0qXjuZ0CwYZaPMsyfogFswvaKp7dKtz2MgII6uxnthUUE6bc8D_ecdlnJkBcIWxLBqh5UeWXYx", latitude, longitude);
    }


//    public Call<Configuration> getConfigurationsData() {
//        return heyFitService.getConfigurationsData();
//    }
//
//    // sign up with facebook
//    public Call<FacebookResponse> getFaceBookSignUpResponseData(String platform, String facebookInputTokenValue, String password, String email, String apiKey, String deviceToken) {
//        return heyFitService.getFaceBookSignUpResponseData(platform, facebookInputTokenValue, password, email, apiKey, deviceToken);
//    }
//
//    // signup with email
//    public Call<SignInResponse> getSimpleSignUpResponseData(String password, String email, String apiKey, String platform, String deviceToken) {
//        return heyFitService.getSimpleSignUpResponseData(password, email, apiKey, platform, deviceToken);
//    }
//
//    public Call<ServerResponse> getServerResponseDataWithoutImage(HashMap<String, RequestBody> partMap) {
//        return heyFitService.getServerResponseDataWithoutImage(partMap);
//    }
//
//    public Call<ServerResponse> getServerResponseData(MultipartBody.Part filename,/*RequestBody fileName ,*/HashMap<String, RequestBody> partMap) {
//        return heyFitService.getServerResponseData(filename,/*fileName,*/partMap);
//    }
//
//
//    //getLogOutResponseData
//    public Call<LogOutResponse> getLogOutResponseData(String url, String token, String apiKey) {
//        return heyFitService.getLogOutResponseData(url, token, apiKey);
//    }
//
//    //getConnectNowResponseData
//
//    public Call<LogOutResponse> getConnectNowResponseData(String url, String statusValue, String receiverUserId, String token, String apiKey) {
//        return heyFitService.getConnectNowResponseData(url, statusValue, receiverUserId, token, apiKey);
//    }
//
//    //cancel Connecction Request
//    public Call<LogOutResponse> getCancelConnectResponseData(String url, String statusValue, String receiverUserId, String token, String apiKey) {
//        return heyFitService.getCancelConnectResponseData(url, statusValue, receiverUserId, token, apiKey);
//    }
//
//    public Call<LogOutResponse> getByeConnectResponseData(String url, String statusValue, String userID, String receiverUserId, String token, String apiKey) {
//        return heyFitService.getByeUserCall(url, statusValue, userID, receiverUserId, token, apiKey);
//    }
//
//    public Call<LogOutResponse> getByeGroupCall(String url, String statusValue, String connection_id, String senderUserId, String token, String apiKey) {
//        return heyFitService.getByeGroupCall(url, statusValue, connection_id, senderUserId, token, apiKey);
//    }
//
//
//    //getHyeConnectResponseData
//
//    public Call<LogOutResponse> getHyeConnectResponseData(String url, String statusValue, String connection_id, String receiverUserId, String token, String apiKey) {
//        return heyFitService.getHeyUserCall(url, statusValue, connection_id, receiverUserId, token, apiKey);
//    }
//
//    public Call<LogOutResponse> getHeyGroupRequest(String url, String statusValue, String connection_id, String receiverUserId, String token, String apiKey) {
//        return heyFitService.getHeyGroupCall(url, statusValue, connection_id, receiverUserId, token, apiKey);
//    }
//
//
//    //getSignInResponseData
//
//    public Call<SignInResponse> getSignInResponseData(String password, String email, String apiKey, String platform, String deviceToken) {
//        return heyFitService.getSignInResponseData(password, email, apiKey, platform, deviceToken);
//    }
//
//
//    public Call<SelectedActivitiesResponse> requestCall(String url, JsonObject jsonObject, String api_key, String userToken) {
//        return heyFitService.sendRequest(url, jsonObject);
//    }
//
//    public Call<FacebookLoginResponse> getFacebookSignInResponse(String url, String platform, String facebookInputTokenValue, String password, String email, String apiKey, String token) {
//        return heyFitService.signIn(url, platform, facebookInputTokenValue, password, email, apiKey, token);
//    }
//
//    public Call<Example> getSearchResult(String url) {
//        return heyFitService.searchResult(url);
//    }
//
//    public Call<UserProfileResponse> getPrivateProfile(String url, String apiKey, String accessToken) {
//        return heyFitService.privateProfile(url, apiKey, accessToken);
//    }
//
//    public Call<ActivitesData> getUserActivites(String url, String userId, String apiKey, String accessToken, String timeAxis, int page) {
//        return heyFitService.getActivites(url, userId, apiKey, accessToken, timeAxis, page);
//    }
//
//
////    public Call<UserPublicProfileData> getPublicProfile(String url, String apiKey, String page, String accessToken) {
////        return heyFitService.publicProfile(url, apiKey, page, accessToken);
////    }
//
//
//    public Call<UserPublicProfileData> getUserSearchResult(String url, String apiKey, String page, String accessToken, String nameValueToSend, String searchRadiusValue, String cityId, String genderID, String activityLevelID, String activityID, String savePreference, String minageValue, String maxageValue) {
//        return heyFitService.userAdvanceSearch(url, apiKey, page, accessToken, nameValueToSend, searchRadiusValue, cityId, genderID, activityLevelID, activityID, savePreference, minageValue, maxageValue);
//    }
//
//    public Call<GetAllGroups> getGroupsResult(String url, String apiKey, String page, String accessToken) {
//        return heyFitService.getAllGroups(url, apiKey, page, accessToken);
//    }
//
//    public Call<GroupResponse> getGroupSearchResult(String url, String apiKey, String page, String accessToken, String nameValueToSend, String searchRadiusValue, String cityId, String genderID, String activityLevelID, String activityID, String savePreference, String profileType) {
//        return heyFitService.groupAdvanceSearch(url, apiKey, page, accessToken, nameValueToSend, searchRadiusValue, cityId, genderID, activityLevelID, activityID, savePreference, profileType);
//    }
//
//
//    public Call<ReceivedConnectionsData> getReceivedConnections(String url, String type, String entityType, String status, String searchString, String userToken, String apiKey, int pagenumber) {
//
//        return heyFitService.receivedConnections(url, type, entityType, status, searchString, apiKey, userToken, pagenumber);
//    }
//
//    public Call<ReceivedConnectionsData> getGroupConnections(String url, String type, String entityType, String status, String searchString, String userToken, String apiKey, int pagenumber) {
//
//        return heyFitService.groupConnections(url, type, entityType, status, searchString, apiKey, userToken, pagenumber);
//    }
//
//
//    public Call<ReceivedConnectionsData> getSentConnections(String url, String type, String entityType, String status, String userToken, String apiKey, int page) {
//
//        return heyFitService.sentConnections(url, type, entityType, status, apiKey, userToken, page);
//
//
//    }
//
//    public Call<LogOutResponse> reportConnectedUserProfile(String url, String userId, String userType, String apiKey, String accessToken) {
//        return heyFitService.reportConnectedUserProfile(url, userId, userType, apiKey, accessToken);//reportConnectedUserProfile
//    }
//
//    public Call<UserProfileResponse> deleteConnectedUserProfile(String url, String userStatus, String apiKey, String accessToken, String receiverUserId) {
//        //String userStatus,String connection_Id
//        return heyFitService.deleteConnectedUserProfile(url, userStatus, apiKey, accessToken, receiverUserId);//reportConnectedUserProfile
//    }
//
//
//    public Call<LogOutResponse> deleteGroup(String url) {
//        //String userStatus,String connection_Id
//        return heyFitService.deleteGroup(url);//reportConnectedUserProfile
//    }
//
////    public Call<GetLocationResponse> getAddress(String url) {
////        return heyFitService.getAddressApi(url);//reportConnectedUserProfile
////    }
//
//    public Call<LogOutResponse> getEditConnectNowResponseData(String url, String statusValue, String receiverUserId, String token, String apiKey) {
//        return heyFitService.getEditConnectResponseData(url, statusValue, receiverUserId, token, apiKey);
//    }
//
//    public Call<LogOutResponse> userBlockCall(String url, String token, String apiKey, String blockedUserId, String blockedUserType, int status) {
//        return heyFitService.userBlocked(url, token, apiKey, blockedUserId, blockedUserType, status);
//    }
//
//    public Call<LogOutResponse> reportUserCall(String url, String token, String apiKey, String blockedUserId, String blockedUserType) {
//        return heyFitService.reportUser(url, token, apiKey, blockedUserId, blockedUserType);
//    }
//
//
//    public Call<LogOutResponse> reportGroupCall(String url, String token, String apiKey, String groupId, String entitiyType) {
//        return heyFitService.reportGroup(url, token, apiKey, groupId, entitiyType);
//    }
//
//
//    public Call<ServerResponse> getServerResponseaddAcctivitiesDataWithUser(MultipartBody.Part filename, HashMap<String, RequestBody> partMap, List<Integer> activityTypeIdA, List<Integer> activityUserIdA, JsonArray jsonObject) {
//        if (jsonObject != null && jsonObject.size() > 0) {
//            return heyFitService.getServerResponseaddAcctivitiesDataWithUser(filename, partMap, activityTypeIdA, activityUserIdA, jsonObject.toString());
//
//        } else {
//            return heyFitService.getServerResponseaddAcctivitiesDataWithUserWithoutRepeat(filename, partMap, activityTypeIdA, activityUserIdA);
//
//        }
//    }
//
//    public Call<ServerResponse> getServerResponseaddAcctivitiesDataWithGroup(MultipartBody.Part filename, HashMap<String, RequestBody> partMap, List<Integer> activityTypeIdA, List<Integer> activityGroupIdA, JsonArray jsonObject) {
//        if (jsonObject != null && jsonObject.size() > 0) {
//            return heyFitService.getServerResponseaddAcctivitiesDataWithGroup(filename, partMap, activityTypeIdA, activityGroupIdA, jsonObject.toString());
//        } else {
//            return heyFitService.getServerResponseaddAcctivitiesDataWithGroupWithoutRepeat(filename, partMap, activityTypeIdA, activityGroupIdA);
//
//        }
//    }
//
//    public Call<AddGroupResponse> getServerResponseaddGroupData(MultipartBody.Part filename, HashMap<String, RequestBody> partMap/*,JsonObject jsonObject*//*, List<SendActivitiesArray> activityTypeIdA*/) {
//        return heyFitService.getServerResponseaddGroupData(filename, partMap/*, activityTypeIdA*//*,jsonObject*/);
//    }
//
//    public Call<AddGroupResponse> getServerResponseaddGroupDataWithoutFile(String Url, HashMap<String, RequestBody> partMap/*,JsonObject jsonObject*//*, List<SendActivitiesArray> activityTypeIdA*/) {
//        return heyFitService.getServerResponseaddGroupDataWithoutFile(Url, partMap);
//    }
//
//
//    public Call<AddGroupResponse> getServerResponseaddGroupDataEdit(String url, MultipartBody.Part filename, HashMap<String, RequestBody> partMap/*,JsonObject jsonObject*//*, List<SendActivitiesArray> activityTypeIdA*/) {
//        return heyFitService.getServerResponseaddGroupDataEditCall(url, filename, partMap/*, activityTypeIdA*//*,jsonObject*/);
//    }
//
//    public Call<ServerResponse> getServerResponseaddAcctivitiesDataWithoutFileWithUser(HashMap<String, RequestBody> partMap, List<Integer> activityTypeIdA, List<Integer> activityUserIdA, JsonArray jsonObject) {
//        if (jsonObject != null && jsonObject.size() > 0) {
//            return heyFitService.getServerResponseaddAcctivitiesDataWithoutFileWithUser(partMap, activityTypeIdA, activityUserIdA, jsonObject.toString());
//
//        } else {
//            return heyFitService.getServerResponseaddAcctivitiesDataWithoutFileWithUserWithoutRepeat(partMap, activityTypeIdA, activityUserIdA);
//
//        }
//    }
//
//    public Call<ServerResponse> getServerResponseaddAcctivitiesDataWithoutFileWithGroup(HashMap<String, RequestBody> partMap, List<Integer> activityTypeIdA, List<Integer> activityGroupIdA, JsonArray jsonObject) {
//        if (jsonObject != null && jsonObject.size() > 0) {
//            return heyFitService.getServerResponseaddAcctivitiesDataWithoutFileWithGroup(partMap, activityTypeIdA, activityGroupIdA, jsonObject.toString());
//
//        } else {
//            return heyFitService.getServerResponseaddAcctivitiesDataWithoutFileWithGroupWithoutRepeat(partMap, activityTypeIdA, activityGroupIdA);
//
//        }
//    }
//
//    public Call<ServerResponse> getEditCallWithImageWithUser(String url, MultipartBody.Part filename, HashMap<String, RequestBody> partMap, List<Integer> activityTypeIdA, List<Integer> activityUserIdA, JsonArray jsonObject) {
//        if (jsonObject != null && jsonObject.size() > 0) {
//            return heyFitService.callEditActivityWithImageWithUser(url, filename, partMap, activityTypeIdA, activityUserIdA, jsonObject.toString());
//
//        } else {
//            return heyFitService.callEditActivityWithImageWithUserWithoutRepeat(url, filename, partMap, activityTypeIdA, activityUserIdA);
//
//        }
//    }
//
//    public Call<ServerResponse> getEditCallWithImageWithGroup(String url, MultipartBody.Part filename, HashMap<String, RequestBody> partMap, List<Integer> activityTypeIdA, List<Integer> activityGroupIdA, JsonArray jsonObject) {
//        if (jsonObject != null && jsonObject.size() > 0) {
//            return heyFitService.callEditActivityWithImageWithGroup(url, filename, partMap, activityTypeIdA, activityGroupIdA, jsonObject.toString());
//
//        } else {
//            return heyFitService.callEditActivityWithImageWithGroupWithoutRepeat(url, filename, partMap, activityTypeIdA, activityGroupIdA);
//
//        }
//    }
//
//
//    public Call<ServerResponse> getEditCallWithoutImageWithUser(String url, HashMap<String, RequestBody> partMap, List<Integer> activityTypeIdA, List<Integer> activityUserIdA, JsonArray jsonObject) {
//        if (jsonObject != null && jsonObject.size() > 0) {
//            return heyFitService.callEditActivityWithoutImageWithUser(url, partMap, activityTypeIdA, activityUserIdA, jsonObject.toString());
//        } else {
//            return heyFitService.callEditActivityWithoutImageWithUserWithoutRepeat(url, partMap, activityTypeIdA, activityUserIdA);
//
//        }
//    }
//
//    public Call<ServerResponse> getEditCallWithoutImageWithGroup(String url, HashMap<String, RequestBody> partMap, List<Integer> activityTypeIdA, List<Integer> activityGroupIdA, JsonArray jsonObject) {
//        if (jsonObject != null && jsonObject.size() > 0) {
//            return heyFitService.callEditActivityWithoutImageWithGroup(url, partMap, activityTypeIdA, activityGroupIdA, jsonObject.toString());
//        } else {
//            return heyFitService.callEditActivityWithoutImageWithGroupWithoutRepeat(url, partMap, activityTypeIdA, activityGroupIdA);
//
//        }
//    }
//
//
//    public Call<LogOutResponse> deleteActivity(String Url) {
//        return heyFitService.deleteActivty(Url);
//    }
//
//    public Call<UserPublicProfileData> getGroupMembersList(String url, String apiKey, String page, String accessToken, String nameValueToSend, String searchRadiusValue, String cityId, String genderID, String activityLevelID, String activityID, String savePreference, String minageValue, String maxageValue, int groupId) {
//        return heyFitService.groupMemberList(url, apiKey, page, accessToken, nameValueToSend, searchRadiusValue, cityId, genderID, activityLevelID, activityID, savePreference, minageValue, maxageValue, groupId);
//    }
//
//    public Call<GroupProfileResponse> getGroupProfile(String url, String apiKey, String accessToken) {
//        return heyFitService.groupProfile(url, apiKey, accessToken);
//    }
//
//    public Call<LogOutResponse> groupConnection(String url, String statusValue, int groupId, String token, String apiKey) {
//        return heyFitService.connectNowGroup(url, statusValue, groupId, token, apiKey);
//    }
//
//    //getResources().getString(R.string.server_api_address) + "connection/group", invitation, groupID,invitedUserId, userToken, getResources().getString(R.string.server_api_key
//    public Call<LogOutResponse> groupConnectionWithUserInvite(String url, String invitation, String groupId, String invitedUserId, String token, String apiKey) {
//        return heyFitService.groupConnectionWithUserInvite(url, invitation, groupId, invitedUserId, token, apiKey);
//    }
//
//    public Call<ChatResponse> getUserChat(String url, String userToken, String server_api_key, int pageNumber, String userID, String userType) {
//        return heyFitService.getUserChat(url, userToken, server_api_key, pageNumber, userID, userType);
//    }
//
//    public Call<ChatResponse> getGroupChat(String url, String userToken, String server_api_key, int pageNumber, String userID, String userType) {
//        return heyFitService.getGroupChat(url, userToken, server_api_key, pageNumber, userID, userType);
//    }
//
//    public Call<SettingsResponse> settings(String url, String userToken, String server_api_key, int hideMeFromMale, int hideMeFromFemale, int isAge, int isPushNotification, int isEmailNotification, int isConnectionRequest, int isChatMessages, int isUpComingActivites, int isPendingRatings) {
//        return heyFitService.callSettings(url, userToken, server_api_key, hideMeFromMale, hideMeFromFemale, isAge, isPushNotification, isEmailNotification, isConnectionRequest, isChatMessages, isUpComingActivites, isPendingRatings);
//
//    }
//
//    public Call<SettingsResponse> getSettings(String url, String userToken, String apiKey) {
//        return heyFitService.callGetSettings(url, userToken, apiKey);
//
//    }
//
//    public Call<TermsServicesResponse> getTermsAndConditions(String url, String apiKey) {
//        return heyFitService.termsService(url, apiKey);
//    }
//
//    public Call<TermsServicesResponse> showPrivacy(String url, String apiKey, String contentCategoryId, String parentContentCategoryId) {
//        return heyFitService.privacy(url, apiKey, contentCategoryId, parentContentCategoryId);
//
//    }
//
//    public Call<DeleteChat> deleteConnectedUserChat(String userIdToJoin, String url, String userType, String pageNumber, String apiKey, String userToken) {
//        return heyFitService.deleteConnectedUserChat(url, userIdToJoin, userType, pageNumber, apiKey, userToken);
//    }
//
//
//    public Call<UnReadMessages> getUnreadChat(String url, String userToken, String apiKey) {
//        return heyFitService.getUnreadChat(url, userToken, apiKey);
//    }
//
//    public Call<LogOutResponse> passwordReset(String url, String apiKey, String email) {
//        return heyFitService.resetPassword(url, apiKey, email);
//
//    }
//
//    public Call<MakeDefaultResponse> getServerResponseAddNewImage(MultipartBody.Part filename, HashMap<String, RequestBody> partMap) {
//        return heyFitService.getServerResponseAddNewImage(filename, partMap);
//    }
//
//    public Call<MakeDefaultResponse> deleteImg(String url) {
//        return heyFitService.deleteImage(url);
//    }
//
//    public Call<DefaultImageResponse> makeDefault(String base_url, String apiKey, String imageName, String userToken, String entityType, String makeDefaultImage) {
//        return heyFitService.makeDefault(base_url, apiKey, imageName, userToken, entityType, makeDefaultImage);
//
//    }
}
