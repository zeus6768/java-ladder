package ladder.domain;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PeopleTest {

    @DisplayName("이름을 입력하여 People을 생성한다.")
    @Test
    void validPeopleTest() {
        assertThatCode(() -> new People(List.of("명오", "제우스")))
                .doesNotThrowAnyException();
    }

    @DisplayName("중복된 이름이 입력되면 예외가 발생한다.")
    @Test
    void peopleTest() {
        assertThatThrownBy(() -> new People(
                List.of("명오", "명오")
        ))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("이름이 1~5글자 범위를 벗어나면 예외를 발생한다.")
    @ValueSource(strings = {"", "우아한테크코스"})
    @ParameterizedTest
    void nameTest(String name) {
        assertThatThrownBy(() -> new People(List.of(name)))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
