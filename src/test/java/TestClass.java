import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TestClass {
    @Mock
    Calculator calculator;
    private TestClassInner testClassInner;

    @BeforeEach
    void setUp() {
        testClassInner = new TestClassInner(calculator);
    }

    @Test
    void should_return_addition_when_plus_sign_passed_to_the_method() {

        given(calculator.calculate(1, 1)).willReturn(2);
        Assertions.assertEquals(testClassInner.doCalculation("+"),2);
        then(calculator).should().calculate(1,1);
    }

    @Test
    void should_throw_exception_when_other_than_plus_sign_passed() {
        given(calculator.calculate(0,0)).willThrow(IllegalStateException.class);
        Assertions.assertThrows(IllegalStateException.class,()-> testClassInner.doCalculation("-"));
        then(calculator).should().calculate(0,0);
    }

    class TestClassInner {
        public Calculator calculator;

        public TestClassInner(Calculator calculator) {
            this.calculator = calculator;
        }

        public int doCalculation(String sign) {
            return sign.equals("+") ? calculator.calculate(1, 1) : calculator.calculate(0, 0);
        }
    }

    interface Calculator {
        int calculate(int a, int b);
    }
}
