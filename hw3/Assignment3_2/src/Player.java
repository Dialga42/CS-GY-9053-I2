public class Player {
    private String name, team;
    private int uniformNumber;

    public Player(String a, String b, int c) {
        name = a;
        team = b;
        uniformNumber = c;
    }

    public boolean equals(Player b) {
        return team.equals(b.team) && uniformNumber == b.uniformNumber;
    }
}
