public class Elixir {
    String characteristic;

    Elixir(String characteristic) {
        this.characteristic = characteristic;
    }

    int heal() {
        if(characteristic.length() % 2 == 0) {
            return characteristic.length();
        } else {
            return characteristic.length() * 2;
        }
    }

    boolean makesInvisible() {
        String lastPartOfAlphabet = "MNOPQRSTUVWXYZ";
        if(lastPartOfAlphabet.contains(characteristic.substring(0,1))) {
            return true;
        } else {
            return makesVisible();
        }

    }

    boolean makesVisible() {
            return false;
    }
}
