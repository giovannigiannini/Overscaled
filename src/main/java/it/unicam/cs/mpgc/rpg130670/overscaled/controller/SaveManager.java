package it.unicam.cs.mpgc.rpg130670.overscaled.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import it.unicam.cs.mpgc.rpg130670.overscaled.model.PlayerData;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
/**
 * Classe per la gestione del salvataggio e caricamento dei dati dei giocatori in formato JSON.
 * @author Giannini Giovanni
 */
public class SaveManager {
    private static final String DIR_PATH = "data";
    private static final String FILE_PATH = DIR_PATH + File.separator + "players_history.json";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void savePlayer(PlayerData player) {
        List<PlayerData> players = loadAllPlayers();
        players.add(player);

        File dir = new File(DIR_PATH);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            gson.toJson(players, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<PlayerData> loadAllPlayers() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            Type listType = new TypeToken<ArrayList<PlayerData>>(){}.getType();
            List<PlayerData> players = gson.fromJson(reader, listType);
            return (players != null) ? players : new ArrayList<>();
        } catch (IOException e) {
            System.err.println("Errore nella lettura del file JSON: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    public static List<PlayerData> getTop10Players() {
        List<PlayerData> players = loadAllPlayers();
        return players.stream()
                .sorted((p1, p2) -> Integer.compare(p2.victories(), p1.victories()))
                .limit(10)
                .collect(Collectors.toList());
    }
}