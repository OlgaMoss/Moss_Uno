package com.example.chanta.translator.bottomNavigation;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import com.example.chanta.translator.DatePicker;
import com.example.chanta.translator.R;
import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;

/**
 * Created by chanta on 07.10.17.
 */

public class ItemSettingFragment extends Fragment {
    private TextView textView;
    private Button button;

    public static ItemSettingFragment newInstance() {
        ItemSettingFragment fragment = new ItemSettingFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_item_setting, container, false);

        textView = (TextView) view.findViewById(R.id.textViewChoiceDate);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dateDialog = new DatePicker();
                dateDialog.show(getFragmentManager(),"Выбрать");
            }
        });

        textView = (TextView) view.findViewById(R.id.textViewName);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater li = LayoutInflater.from(getActivity());
                View promptsView = li.inflate(R.layout.prompt, null);

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setView(promptsView);
                final TextView textView1 = (TextView) promptsView.findViewById(R.id.input_text);
                builder.setTitle(R.string.choice_name)
                        .setPositiveButton(R.string.choice_ok,
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        textView.setText(textView1.getText());
                                    }
                                })
                        .setNegativeButton(R.string.choice_cansel,
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        dialog.cancel();
                                    }
                                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        });

        button = (Button) view.findViewById(R.id.color_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ColorPickerDialogBuilder
                        .with(getActivity())
                        .setTitle(R.string.my_choose_color)
                        .initialColor(((ColorDrawable)button.getBackground()).getColor())
                        .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                        .density(12)
                        .setOnColorSelectedListener(new OnColorSelectedListener() {
                            @Override
                            public void onColorSelected(int selectedColor) {
                            }
                        })
                        .setPositiveButton(R.string.choice_ok, new ColorPickerClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int selectedColor, Integer[] allColors) {
                               button.setBackgroundColor(selectedColor);
                            }
                        })
                        .setNegativeButton(R.string.choice_cansel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .build()
                        .show();
            }
            });
        return view;
    }





}
