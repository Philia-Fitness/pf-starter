package io.github.philiafitness.pfstarter.pfwebstarter.enums;

import io.github.philiafitness.pfstarter.pfwebstarter.bean.response.BaseResponse;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.Optional;

import static io.github.philiafitness.pfstarter.pfwebstarter.bean.response.BaseResponse.Status.FAILURE;
import static io.github.philiafitness.pfstarter.pfwebstarter.bean.response.BaseResponse.Status.SUCCESS;


/**
 * Response code enum with
 * <ul>
 *     <li>new horizon code</li>
 *     <li>description</li>
 *     <li>{@link HttpStatus}</li>
 *     <li>{@link io.github.philiafitness.pfstarter.pfwebstarter.bean.response.BaseResponse.Status}</li>
 * </ul>
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum ResponseCodesEnum {

    // OK response
    OK(0, "", HttpStatus.OK, SUCCESS),
    OK_EMPTY(1, "Empty response", HttpStatus.NO_CONTENT, SUCCESS),
    BAD_PARAMETER(-400, "Bad input parameters", HttpStatus.BAD_REQUEST, FAILURE),
    NOT_FOUND(-404, "Not found", HttpStatus.NOT_FOUND, FAILURE),
    GENERIC_ERROR(-500, "Generic Error", HttpStatus.INTERNAL_SERVER_ERROR, FAILURE),
    NOT_IMPLEMENTED(-501, "Not Implemented", HttpStatus.NOT_IMPLEMENTED, FAILURE),

    BAD_CREDENTIALS(-101, "Invalid credentials", HttpStatus.UNAUTHORIZED, FAILURE),
    INVALID_CLIENT_CONFIGURATION(-102, "Invalid auth client configuration", HttpStatus.SERVICE_UNAVAILABLE, FAILURE),
    UNAUTHORIZED_CLIENT(-103, "Auth client is not authorized to perform invoked operation", HttpStatus.INTERNAL_SERVER_ERROR, FAILURE),
    BAD_GRANT_TYPE(-104, "Unsupported grant type", HttpStatus.SERVICE_UNAVAILABLE, FAILURE),


    UNAUTHORIZED(-107, "Unauthorized", HttpStatus.UNAUTHORIZED, FAILURE),
    FORBIDDEN(-108, "Forbidden", HttpStatus.FORBIDDEN, FAILURE);

    private final Integer errorCode;
    private final String description;
    private final HttpStatus httpErrorCode;
    private final BaseResponse.Status status;

    public static ResponseCodesEnum getResponseCodeByErrorCode(Integer npplErrorCode) {
        Optional<ResponseCodesEnum> responseCode = Arrays.stream(ResponseCodesEnum.values())
                .filter(responseCodesEnum -> npplErrorCode.equals(responseCodesEnum.getErrorCode()))
                .findFirst();
        return responseCode.orElse(null);
    }

}
