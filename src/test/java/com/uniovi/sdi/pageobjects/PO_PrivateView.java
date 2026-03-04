package com.uniovi.sdi.pageobjects;

import com.uniovi.sdi.util.SeleniumUtils;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class PO_PrivateView extends PO_NavView {
    static public void fillFormAddMark(WebDriver driver, int userOrder, String descriptionp, String scorep)
    {
//Esperamos 5 segundo a que carge el DOM porque en algunos equipos falla
        SeleniumUtils.waitSeconds(driver, 5);
//Seleccionamos el alumnos userOrder
        new Select(driver.findElement(By.id("user"))).selectByIndex(userOrder);
//Rellenemos el campo de descripción
        WebElement description = driver.findElement(By.name("description"));
        description.clear();
        description.sendKeys(descriptionp);
        WebElement score = driver.findElement(By.name("score"));
        score.click();
        score.clear();
        score.sendKeys(scorep);
        By boton = By.className("btn");
        driver.findElement(boton).click();
    }

    static public void logout(WebDriver driver, String checkText) {
        String loginText = PO_HomeView.getP().getString("signup.message", PO_Properties.getSPANISH());
        PO_PrivateView.clickOption(driver, "logout", "text", loginText);
        List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, result.getFirst().getText());
    }


    //pulsar en un elemento como un enlace
    static public void clickInElement(WebDriver driver, String type, String text, int number) {
        List<WebElement> elements = PO_View.checkElementBy(driver, type, text);
        elements.get(number).click();
    }

    //pulsa en el botón de pagina para cambiar de pagina
    static public void clickPageButton(WebDriver driver, String text, int number) {
        clickInElement(driver, "free", text, number);
    }



}