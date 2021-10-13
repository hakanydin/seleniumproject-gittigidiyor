import org.junit.Test;

public class MainTest extends BaseTest{
    @Test
    public void controlAndTest() throws InterruptedException{
        controlTitle();
        controlLogin("",""); //enter mail and password
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

