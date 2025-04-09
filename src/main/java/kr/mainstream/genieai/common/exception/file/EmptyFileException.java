package kr.mainstream.genieai.common.exception.file;

import kr.mainstream.genieai.common.exception.SimpleMessageIllegalArgumentException;

public class EmptyFileException extends SimpleMessageIllegalArgumentException {
    public EmptyFileException() {
        super("common.text.file.error.empty"); // 파일을 선택해주세요.
    }
}
