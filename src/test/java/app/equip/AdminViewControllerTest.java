package app.equip;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdminViewControllerTest {

    @Test
    void shouldAddNewEquipmentAfterClicked(){
        var controller = new AdminViewController();
        controller.equipmentAddBtn();

    }

}