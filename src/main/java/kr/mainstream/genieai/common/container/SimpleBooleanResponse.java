package kr.mainstream.genieai.common.container;

import lombok.Getter;

@Getter
public class SimpleBooleanResponse {

    private final Boolean result;

    public SimpleBooleanResponse(Boolean result) {
        this.result = result;
    }
}
