package com.fjrodriguez.tresenraya;

/**
 * Created by fjrodriguez on 28/06/14.
 */
public class TresEnRaya {
    private int[][] tablero = new int[3][3];

    public final int JUGADOR1 = 1;
    public final int JUGADOR2 = 2;
    public int jugador;
    private int numJugadas;
    private boolean finDePartida;

    public TresEnRaya ()  {
        // Inicializamos el tablero a cero.
        borrarTablero();
        jugador = JUGADOR1;
    }

    public void borrarTablero () {
        numJugadas = 0;
        finDePartida = false;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tablero[i][j] = 0;
            }
        }
    }
    public boolean juegaJugador (int jugador, int posX, int posY) {
        if (tablero[posX][posY] == 0) {
            tablero[posX][posY] = jugador;
            numJugadas++;
            return true;
        }
        return false;
    }

    private boolean solucionColumna (int jugador, int posX) {
        for (int j = 0; j < 3; j++) {
            if (tablero[posX][j] != jugador)
                return false;
        }
        return true;
    }

    private boolean solucionFila (int jugador, int posY) {
        for (int i = 0; i < 3; i++) {
            if (tablero[i][posY] != jugador)
                return false;
        }
        return true;
    }

    private boolean solucionDiagonal1(int jugador) {
        return tablero[0][0] == jugador && tablero[1][1] == jugador
                && tablero[2][2] == jugador;
    }

    private boolean solucionDiagonal2(int jugador) {
        return tablero[2][0] == jugador && tablero[1][1] == jugador
                && tablero[0][2] == jugador;
    }

    public boolean haGanado (int jugador, int posX, int posY) {
        finDePartida = true;
        if (solucionFila(jugador, posY))
            return true;
        if (solucionColumna(jugador, posX))
            return true;
        // Comprobamos la primera diagonal
        if ((posX == 0 && posY == 0) || (posX == 2 && posY == 2) || (posX == 1 && posY == 1))
            if (solucionDiagonal1(jugador))
                return true;
        // Comprobamos la segunda diagonal
        if ((posX == 0 && posY == 2) || (posX == 2 && posY == 0) || (posX == 1 && posY == 1))
            if (solucionDiagonal2(jugador))
                return true;
        finDePartida = false;
        return false;
    }

    public boolean finalizaPartida() {
        return numJugadas == 9 || finDePartida;
    }
}
