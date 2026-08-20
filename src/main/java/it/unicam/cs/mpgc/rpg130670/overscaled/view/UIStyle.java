package it.unicam.cs.mpgc.rpg130670.overscaled.view;

/**
 * Questa classe raggruppa i colori utilizzati ripetuti in modo da avere una palette unica per l'estetica
 * e unica da modificare in caso di manutenzione del codice
 * @author Giannini Giovanni
 */
public class UIStyle {
    public static final String BG_BLACK = "#141414";
    public static final String CARD_BG = "#2B3E50";
    public static final String YELLOW_TITLE = "#F1C40F";
    public static final String GREEN_ACCENT = "#2ECC71";
    public static final String WHITE_TEXT = "#ECEFF4";
    public static final String MUTED_TEXT = "#BDC3C7";

    public static final String MAIN_CONTAINER =
            "-fx-background-color: " + BG_BLACK + ";";

    public static final String CARD_PANEL =
            "-fx-background-color: " + CARD_BG + ";" +
                    "-fx-background-radius: 10px;" +
                    "-fx-padding: 25px;";

    public static final String TEXT_FIELD =
            "-fx-background-color: #1F2D3A;" +
                    "-fx-text-fill: " + WHITE_TEXT + ";" +
                    "-fx-prompt-text-fill: #7F8C8D;" +
                    "-fx-border-color: #34495E;" +
                    "-fx-border-width: 1px;" +
                    "-fx-border-radius: 5px;" +
                    "-fx-background-radius: 5px;" +
                    "-fx-font-family: 'Consolas', monospace;" +
                    "-fx-font-size: 14px;" +
                    "-fx-padding: 10px;";

    public static final String BUTTON_GREEN =
            "-fx-background-color: " + GREEN_ACCENT + ";" +
                    "-fx-text-fill: white;" +
                    "-fx-font-family: 'Consolas', monospace;" +
                    "-fx-font-weight: bold;" +
                    "-fx-font-size: 14px;" +
                    "-fx-background-radius: 5px;" +
                    "-fx-padding: 10px 20px;" +
                    "-fx-cursor: hand;";
}