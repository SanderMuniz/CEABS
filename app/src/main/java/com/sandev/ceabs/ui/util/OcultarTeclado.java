package com.sandev.ceabs.ui.util;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;

public class OcultarTeclado {
    public static void ocultarTeclado(Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        assert imm != null;
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }
}
