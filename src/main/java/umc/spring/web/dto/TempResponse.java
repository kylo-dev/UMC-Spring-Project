package umc.spring.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class TempResponse {

    /**
     * DTO 자체는 많은 곳에서 사용된다.
     * static class를 통해, 매번 class 파일을 만들 필요 없이 범용적으로 DTO를 사용할 수 있다.
     * !! DTO들을 큰 묶음의 관련된 클래스로 만들어 담고, 내부적으로 static 클래스를 만들어 사용합니다.
     */
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TempTestDTO{
        String testString;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TempExceptionDTO{
        Integer flag;
    }
}
