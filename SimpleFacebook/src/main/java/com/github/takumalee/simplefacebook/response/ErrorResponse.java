package com.github.takumalee.simplefacebook.response;

/**
 * Created by Nijugon on 2015/6/6.
 */
public class ErrorResponse {

    /**
     * error : {"code":2500,"message":"An active access token must be used to query information about the current user.","type":"OAuthException"}
     */
    private ErrorEntity error;

    public ErrorEntity getError() {
        return error;
    }

    public void setError(ErrorEntity error) {
        this.error = error;
    }

    public class ErrorEntity {
        /**
         * code : 2500
         * message : An active access token must be used to query information about the current user.
         * type : OAuthException
         */
        private int code;
        private String message;
        private String type;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
