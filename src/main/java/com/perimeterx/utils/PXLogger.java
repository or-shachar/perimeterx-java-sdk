package com.perimeterx.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PXLogger {

    private Logger logger;

    private final String DEBUG_PREFIX = "[PerimeterX - DEBUG] ";
    private final String ERROR_PREFIX = "[PerimeterX - ERROR] ";

    public enum LogReason {
        DEBUG_INITIALIZING_MODULE("Initializing PerimeterX enforcer"),

        DEBUG_MODULE_DISABLED("Request will not be verified, module is disabled"),
        DEBUG_STARTING_REQUEST_VERIFICATION("Starting request verification"),
        DEBUG_REQUEST_CONTEXT_CREATED("Request context created successfully"),

        DEBUG_COOKIE_MISSING("Cookie is missing"),
        DEBUG_COOKIE_VERSION_FOUND("Cookie {} found, Evaluating"), //version
        DEBUG_COOKIE_DECRYPTION_HMAC_FAILED("Cookie HMAC validation failed, value: {}, user-agent: {}"), //decrypted-cookie-value, user agent
        DEBUG_COOKIE_HMAC_VALIDATION_FAILED("Cookie HMAC validation failed, value: {}"), //decrypted-cookie-value
        DEBUG_COOKIE_TLL_EXPIRED("Cookie TTL is expired, value: {}, age: {}"), //decrypted-cookie-value, cookie age
        DEBUG_COOKIE_EVALUATION_FINISHED("Cookie evaluation ended successfully, risk score: {}"), //score

        DEBUG_S2S_RISK_API_SENSITIVE_ROUTE("Sensitive route match, sending Risk API. path: {}"), //path
        DEBUG_S2S_RISK_API_REQUEST("Evaluating Risk API request, call reason: {}"), //s2s_call_reason
        DEBUG_S2S_RISK_API_RESPONSE("Risk API response returned successfully, risk score: {}, round_trip_time: {}"), //score,rtt

        DEBUG_S2S_SCORE_IS_HIGHER_THAN_BLOCK("Risk score is higher or equal  to blocking score. score: {} blockingScore: {}"),
        DEBUG_S2S_SCORE_IS_LOWER_THAN_BLOCK("Risk score is lower than blocking score. score: {} blockingScore: {}"),
        DEBUG_S2S_ENFORCING_ACTION("Enforcing action: {} page is served"), //Block/Captcha/Challenge

        DEBUG_MOBILE_SDK_DETECTED("Mobile SDK token detected"),

        ERROR_CONFIGURATION_MISSING_MANDATORY_CONFIGURATION("Unable to initialize module, missing mandatory configuration. {}"), //config name
        ERROR_COOKIE_EVALUATION_EXCEPTION("Unexpected exception while evaluating Risk cookie. {}"),//error
        ERROR_DATA_ENRICHMENT_JSON_PARSING_FAILED("Data enrichment payload parsing as json failed");

        String reason;

        LogReason(String reason) {
            this.reason = reason;
        }

        public String toString() {
            return this.reason;
        }
    }

    public static PXLogger getLogger(Class<?> clazz) {
        return new PXLogger(clazz);
    }

    private PXLogger(Class<?> clazz) {
        logger = LoggerFactory.getLogger(clazz);
    }

    public void debug(LogReason reason, Object... args) {
        logger.debug(DEBUG_PREFIX + reason, args);
    }

    public void debug(String msg, Object... args) {
        logger.debug(DEBUG_PREFIX + msg, args);
    }

    public void error(LogReason reason, Object... args) {
        logger.error(ERROR_PREFIX + reason, args);
    }

    public void error(String msg, Object... args) {
        logger.error(ERROR_PREFIX + msg, args);
    }
}