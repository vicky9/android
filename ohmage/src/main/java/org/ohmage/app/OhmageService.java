/*
 * Copyright (C) 2013 ohmage
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.ohmage.app;

import org.apache.http.auth.AuthenticationException;
import org.ohmage.auth.AuthUtil;
import org.ohmage.models.User;
import org.ohmage.sync.StreamWriterOutput;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by cketcham on 12/9/13.
 */
public interface OhmageService {

    @POST("/people") void createUser(@Query("provider") AuthUtil.GrantType grantType,
            @Query("access_token") String accessToken, @Body User user, Callback<User> callback);

    @POST("/people")
    void createUser(@Query("password") String password, @Body User user, Callback<User> callback);

    @POST("/streams/{streamId}/{streamVersion}/data")
    Response uploadStreamData(@Header("Authorization") String token,
            @Path("streamId") String streamId, @Path("streamVersion") String streamVersion,
            @Body StreamWriterOutput data) throws AuthenticationException;
}