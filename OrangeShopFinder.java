package com.orange.shop.services;

/**
 * Recherche de boutiques Orange.
 * 
 * Le point d'entr�e doit impl�menter cette interface.
 * 
 * 
 */
public interface OrangeShopFinder {

	String findOrangeShopWithMobileAvailable(double longitude, double latitude, String nameMobile);
}
