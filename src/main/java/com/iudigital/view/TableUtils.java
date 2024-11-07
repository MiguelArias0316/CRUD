package com.iudigital.view;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

public class TableUtils {

    public static void ajustarAnchoColumnasPersonalizado(JTable table) {
        // Anchos personalizados para cada columna en píxeles
        int[] anchos = {20, 130, 120, 80, 80, 80, 90, 60, 90, 130, 140, 140};

        for (int i = 0; i < table.getColumnCount(); i++) {
            TableColumn columna = table.getColumnModel().getColumn(i);
            columna.setMinWidth(anchos[i]);
            columna.setPreferredWidth(anchos[i]);
            columna.setMaxWidth(anchos[i]);
        }
    }
}