package com.soucriador.cardapiodigital.io;

import com.soucriador.edeploy.jhonattas.model.Cidade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CidadeContent {

    public static List<Cidade> ITEMS = new ArrayList<>();

    public static Map<String, Cidade> ITEM_MAP = new HashMap<>();

    static {
    }

    private static void addItem(Cidade item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.getNome(), item);
    }

}
