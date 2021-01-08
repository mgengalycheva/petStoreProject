package api.tests;
import api.steps.ApiSteps;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class ApiTests {

    @Steps
    ApiSteps apiSteps;

    @Test
    public void checkPetMethods() {
        apiSteps.postGetPetById();
        apiSteps.postPutPetById();
    }
}
