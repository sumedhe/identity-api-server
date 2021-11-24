/*
 * Copyright (c) 2019, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.wso2.carbon.identity.api.server.email.template.common;

import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.core.Response.Status;

import static org.wso2.carbon.email.mgt.constants.I18nMgtConstants.ErrorCodes.EMAIL_TEMPLATE_TYPE_ALREADY_EXISTS;
import static org.wso2.carbon.email.mgt.constants.I18nMgtConstants.ErrorCodes.EMAIL_TEMPLATE_TYPE_NODE_FOUND;

/**
 * Claim Management constant class.
 */
public class Constants {

    public static final String EMAIL_TEMPLATES_ERROR_CODE_PREFIX = "ETM-";
    public static final String EMAIL_TEMPLATES_API_BASE_PATH = "/email";
    public static final String EMAIL_TEMPLATE_TYPES_PATH = "/template-types";
    public static final String EMAIL_TEMPLATES_PATH = "/templates";
    public static final String PATH_SEPARATOR = "/";

    // Required attributes.
    public static final String TEMPLATES = "templates";
    public static final String LOCALE = "templates.id";
    public static final String CONTENT_TYPE = "templates.contentType";
    public static final String SUBJECT = "templates.subject";
    public static final String BODY = "templates.body";
    public static final String FOOTER = "templates.footer";

    private static final Map<String, ErrorMessage> ERROR_CODE_MAP = new HashMap<>();

    /**
     * Enum for error messages.
     */
    public enum ErrorMessage {

        ERROR_RETRIEVING_EMAIL_TEMPLATE_TYPES("55002", Status.INTERNAL_SERVER_ERROR,
                "Unable to retrieve email template types.",
                "Server encountered an error while retrieving email template types."),
        ERROR_RETRIEVING_EMAIL_TEMPLATE_TYPE("55003", Status.INTERNAL_SERVER_ERROR,
                "Unable to retrieve the email template type.",
                "Server encountered an error while retrieving the email template " +
                        "type identified by the given template-type-id."),
        ERROR_RETRIEVING_EMAIL_TEMPLATE("55004", Status.INTERNAL_SERVER_ERROR,
                "Unable to retrieve the email template.",
                "Server encountered an error while retrieving the email template " +
                        "identified by the given template-type-id and the template-id."),
        ERROR_ADDING_EMAIL_TEMPLATE_TYPE("55005", Status.INTERNAL_SERVER_ERROR,
                "Unable to add the email template type.",
                "Server encountered an error while adding the email template type."),
        ERROR_ADDING_EMAIL_TEMPLATE("55006", Status.INTERNAL_SERVER_ERROR,
                "Unable to add the email template.",
                "Server encountered an error while adding the email template to the system."),
        ERROR_DELETING_EMAIL_TEMPLATE_TYPE("55007", Status.INTERNAL_SERVER_ERROR,
                "Unable to delete the email template type.",
                "Server encountered an error while deleting the email template type."),
        ERROR_DELETING_EMAIL_TEMPLATE("55008", Status.INTERNAL_SERVER_ERROR,
                "Unable to delete the email template.",
                "Server encountered an error while deleting the email template."),
        ERROR_UPDATING_EMAIL_TEMPLATE_TYPE("55009", Status.INTERNAL_SERVER_ERROR,
                "Unable to update the email template type.",
                "Server encountered an error while updating the email template type."),
        ERROR_UPDATING_EMAIL_TEMPLATE("55010", Status.INTERNAL_SERVER_ERROR,
                "Unable to update the email template.",
                "Server encountered an error while updating the email template."),
        ERROR_PAGINATION_NOT_SUPPORTED("55011", Status.METHOD_NOT_ALLOWED,
                "Pagination is not yet supported.",
                "Please remove 'limit' and 'offset' parameters from the request and try again."),
        ERROR_SORTING_NOT_SUPPORTED("55012", Status.METHOD_NOT_ALLOWED,
                "Sorting is not yet supported.",
                "Please remove 'sortOrder' and 'sortBy' parameters from the request and try again."),
        ERROR_EMAIL_TEMPLATE_TYPE_NOT_FOUND("50002", Status.NOT_FOUND,
                "Email Template Type does not exists.",
                "Specified email template type does not exist in the system."),
        ERROR_EMAIL_TEMPLATE_NOT_FOUND("50003", Status.NOT_FOUND,
                "Email Template does not exists.",
                "Specified email template does not exist in the system."),
        ERROR_EMAIL_TEMPLATE_ALREADY_EXISTS("50004", Status.CONFLICT,
                "Email Template already exists in the system.",
                "An email template for the provided template id already exists in the system."),
        ERROR_EMAIL_TEMPLATE_TYPE_ALREADY_EXISTS("50005", Status.CONFLICT,
                "Email Template Type already exists in the system.",
                "An email template type for the provided template display name already exists " +
                        "in the system."),
        ERROR_ATTRIBUTE_NOT_SUPPORTED("50006", Status.BAD_REQUEST, "Attribute type not found",
                "Invalid attribute for email templates. Supported attributes are: 'templates', " +
                        "'templates.id', 'templates.contentType', 'templates.subject', 'templates.body' " +
                        "and 'templates.footer'.");

        private final String message;
        private final Status httpStatus;
        private final String code;
        private final String description;

        ErrorMessage(String code, Status httpStatus, String message, String description) {

            this.code = code;
            this.httpStatus = httpStatus;
            this.message = message;
            this.description = description;
        }

        public String getCode() {

            return EMAIL_TEMPLATES_ERROR_CODE_PREFIX + code;
        }

        public String getMessage() {

            return message;
        }

        public String getDescription() {

            return description;
        }

        public Status getHttpStatus() {

            return httpStatus;
        }

        @Override
        public String toString() {

            return code + " | " + message;
        }
    }

    static {
        ERROR_CODE_MAP.put(EMAIL_TEMPLATE_TYPE_NODE_FOUND, ErrorMessage.ERROR_EMAIL_TEMPLATE_TYPE_NOT_FOUND);
        ERROR_CODE_MAP.put(EMAIL_TEMPLATE_TYPE_ALREADY_EXISTS, ErrorMessage.ERROR_EMAIL_TEMPLATE_TYPE_ALREADY_EXISTS);
    }

    public static ErrorMessage getMappedErrorMessage(String errorCode) {

        return ERROR_CODE_MAP.get(errorCode);
    }
}
