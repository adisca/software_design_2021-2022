package com.utcn.assignment3.Util;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.utcn.assignment3.Model.Category;
import com.utcn.assignment3.Model.Food;
import com.utcn.assignment3.Model.Restaurant;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public final class Exporter {

    public static void exportPDF(Restaurant restaurant) throws DocumentException, FileNotFoundException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("temp/" + restaurant.getName() + ".pdf"));

        document.open();

        Phrase phrase = new Phrase();

        phrase.add(restaurant.getName() + "\n");

        for (Category menu : restaurant.getMenus()) {
            phrase.add("\n" + menu.getName() + "\n");
            for (Food food : menu.getFoods()) {
                phrase.add(food.toString() + "\n");
            }
        }
        System.out.println(phrase.getContent());
        document.add(phrase);
        document.close();
    }
}
