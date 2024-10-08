/*
 * Copyright (c) 2023, WSO2 LLC. (http://www.wso2.com).
 *
 * WSO2 LLC. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.carbon.identity.api.server.application.management.v1.core.functions.application;

import org.wso2.carbon.identity.api.server.application.management.v1.AuthAttribute;
import org.wso2.carbon.identity.api.server.application.management.v1.UserRegistrant;
import org.wso2.carbon.identity.auth.attribute.handler.model.AuthAttributeHolder;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.wso2.carbon.identity.api.server.common.Util.base64URLEncode;

/**
 * Converts the backend model AuthAttributeHolder into the corresponding API model object.
 */
public class AuthAttributeHolderToUserRegistrant implements Function<AuthAttributeHolder, UserRegistrant> {

    @Override
    public UserRegistrant apply(AuthAttributeHolder authAttributeHolder) {

        List<AuthAttribute> authAttributes = null;
        if (authAttributeHolder.getAuthAttributes() != null) {
            authAttributes = authAttributeHolder.getAuthAttributes().stream().map(new
                    AuthAttributeToApiModel()).collect(Collectors.toList());
        }

        return new UserRegistrant()
                .id(base64URLEncode(authAttributeHolder.getHandlerName()))
                .name(authAttributeHolder.getHandlerName())
                .authAttributes(authAttributes);
    }
}
