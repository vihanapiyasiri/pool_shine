package lk.ijse.util;

import com.jfoenix.controls.JFXTextField;
import javafx.scene.paint.Paint;

import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {
    public static boolean isTextFiledValid(TextField textField, String text){
        String field = "";

        switch (textField){
            case ID :
                field = "^([A-Z][0-9]{3})$";
                break;
                case CONTACT:
                field = "/^(\\()?\\d{3}(\\))?(-|\\s)?\\d{3}(-|\\s)\\d{4}$/";
                break;
        }

        Pattern pattern = Pattern.compile(field);

        if (text != null){
            if (text.trim().isEmpty()){
                return false;
            }
        }else {
            return false;
        }

        Matcher matcher = pattern.matcher(text);

        if (matcher.matches()){
            return true;
        }
        return false;
    }

    public static boolean setTextColor(TextField location, JFXTextField field){
        if (Regex.isTextFiledValid(location,field.getText())){
            field.setFocusColor(Paint.valueOf("Green"));
            field.setUnFocusColor(Paint.valueOf("Green"));
            return true;
        }else {
            field.setFocusColor(Paint.valueOf("Red"));
            field.setUnFocusColor(Paint.valueOf("Red"));
            return false;
        }
    }
}