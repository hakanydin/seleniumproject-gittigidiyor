import org.junit.Test;

public class MainTest extends BaseTest{
    @Test
    public void controlAndTest() throws InterruptedException{
        controlTitle();
        controlLogin("hakanydin@gmail.com","5249070As");
        contolSearch("Bilgisayar");
        gotoPageandControl();
        controlPrdct();
        controlDetailPrice();
        controlAddCart();
        controlGoToCart();
        controlDropDownValue();
        controlDeleteCart();

    }


}

