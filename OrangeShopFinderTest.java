package com.orange.shop;

import com.orange.shop.exception.OrangeShopNotFoundException;
import com.orange.shop.services.OrangeShopFinder;
import com.orange.shop.services.impl.OrangeShopFinderImpl;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OrangeShopFinderTest {

    public OrangeShopFinder orangeShopFinder = new OrangeShopFinderImpl();


    @Test
    public void orangeShopWithMobileAvailable() {
        String actual = "[Orange] 29 Lesneven (1 Rue de Jérusalem)";
        String expected = orangeShopFinder.findOrangeShopWithMobileAvailable(-30000.16566, 48.57226, "sunusng");
        assertEquals(expected, actual);
    }

    @Test
    public void orangeShopNotFound() {
        OrangeShopNotFoundException exception = assertThrows(OrangeShopNotFoundException.class, () -> {
            orangeShopFinder.findOrangeShopWithMobileAvailable(-30000.16566, 48.57226, "sunusngpp");
        });
        assertEquals("Orange Shop Not Exist", exception.getMessage());
    }


}
