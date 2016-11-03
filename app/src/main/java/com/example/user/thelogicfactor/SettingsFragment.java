package com.example.user.thelogicfactor;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by User on 08.07.2016.
 */
public class SettingsFragment extends Fragment {    //фрагмент - настройки для пользователя, их обработка
    private static String namePlayer = "Пользователь";
    private static float fontSize = 10f;
    private static boolean isMusicOff = false;
    EditText name_player, font_size;
    CheckBox is_music_play;
    Button buttonSave;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_settings, container, false);
        name_player = (EditText)v.findViewById(R.id.editTextNamePlayer);
        font_size = (EditText)v.findViewById(R.id.editTextFontSize);
        is_music_play = (CheckBox)v.findViewById(R.id.checkBoxMusic);
        buttonSave = (Button)v.findViewById(R.id.buttonSave);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pastName = namePlayer;
                String stringName = name_player.getText().toString();
                String stringFontSize = font_size.getText().toString();
                namePlayer = checkInformationInEditText(stringName);
                fontSize = checkInformationInEditText(stringFontSize, 10);
                isMusicOff = is_music_play.isChecked(); //true - отключить, false - включить

                if(!(pastName.equals(namePlayer))) {
                    EasyQuestions.setShowNameOfPlayer(true);
                    DifferentQuestionsFragment.isNewName1 = true;
                    DifferentQuestionsFragment.isNewName2 = true;
                    DifferentQuestionsFragment.isNewName3 = true;
                }

                Toast.makeText(getActivity(), "Сохранено", Toast.LENGTH_SHORT).show();
            }
        });

        return v;
    }

    public String checkInformationInEditText(String string) {
        if(string.isEmpty()) {
            return "Пользователь";
        }
        char[] symbols = new char[string.length()];
        string.getChars(0, string.length(), symbols, 0);
        for(int i = 0; i < string.length(); i++) {
            if(symbols[i] != ' ') {
                return string;
            }
        }
        return "Пользователь";
    }

    public float checkInformationInEditText(String string, float f) {
        if(!(string.isEmpty())) {
            Float fl = Float.parseFloat(string);
            if((fl > 10) && (fl <= 25)) {
                return fl;
            } else {
                return f;
            }
        } else {
            return f;
        }
    }

    public static String getNamePlayer() {
        return namePlayer;
    }

    public static float getFontSize() {
        return fontSize;
    }

    public static boolean isMusicOff() {
        return isMusicOff;
    }
}
