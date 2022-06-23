package com.creactivestudio.lerntagebuchapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;


public class Helper {

    // Message Typen sind final definiert um tipp fehler zu vermeiden
    public static final String TOAST_MESSAGE_TYPE_SUCCESS="success";
    public static final String TOAST_MESSAGE_TYPE_ERROR="error";
    Context context;

    /**
     * In diese Klasse befindet sich hilfreiche Methode und
     * man kann diese Methode gesamte Projekt ganz einfach
     * benutzen.
     * @param context
     */
    public Helper (Context context)
    {
        this.context=context;
    }

    /**
     * Diese Methode customisiert Toast Message weil default Toast Message
     * ist zu klein für Tablets
     * Man kann erfolgreiche Situationen mit Grün Farbe und Fehler
     * Nachrichten mit Rot Farbe visualiesieren
     * @param message
     * @param messageType
     */
    public void showToast(String message, String messageType) {

        Toast toast = new Toast(context);
        View view = null;
        view = LayoutInflater.from(context)
                .inflate(R.layout.custom_toast_message_layout, null); // Set custom toast layout
        CardView cardView= view.findViewById(R.id.custom_toast_cardview);

        if(messageType.equals(Helper.TOAST_MESSAGE_TYPE_SUCCESS))// für Erfolgreiche Situationen
        {
            cardView.setBackgroundColor(context.getResources().getColor(R.color.teal_700));
        }
        else if (messageType.equals(TOAST_MESSAGE_TYPE_ERROR)) // Für Fehler Nachrichten
        {

            cardView.setBackgroundColor(context.getResources().getColor(R.color.design_default_color_error));
        }
        TextView tvMessage = view.findViewById(R.id.tvMessage);
        tvMessage.setText(message);
        toast.setView(view);
        toast.show();

    }
}
