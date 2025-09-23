package io.github.philiafitness.pfstarter.pfwebstarter.bean.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.philiafitness.pfstarter.pfwebstarter.enums.ResponseCodesEnum;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

/**
 * Base Response
 *
 */
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
@Data
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse {
    private Status status = Status.SUCCESS;
    private Instant timestamp = Instant.now();
    private String responseMessage = ResponseCodesEnum.OK.getDescription();
    private Integer responseCode = ResponseCodesEnum.OK.getErrorCode();


    /**
     * Response status class
     */
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @Getter
    public enum Status {
        SUCCESS("OK"),
        WARNING("WARNING"),
        FAILURE("KO");

        private final String status;
    }
}
