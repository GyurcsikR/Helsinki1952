package hu.progmatic;

import java.util.Objects;

public class Olimpia {
    private int place;
    private int membersOfTeam;
    private String nameOfSport;
    private String typeOfSport;

    public Olimpia() {
    }

    public Olimpia(int place, int membersOfTeam, String nameOfSport, String typeOfSport) {
        this.place = place;
        this.membersOfTeam = membersOfTeam;
        this.nameOfSport = nameOfSport;
        this.typeOfSport = typeOfSport;
    }
    public Olimpia(String line){
        String[] parts = line.split(" ");
        this.place = Integer.parseInt(parts[0]);
        this.membersOfTeam = Integer.parseInt(parts[1]);
        this.nameOfSport = parts[2];
        this.typeOfSport = parts[3];
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public int getMembersOfTeam() {
        return membersOfTeam;
    }

    public void setMembersOfTeam(int membersOfTeam) {
        this.membersOfTeam = membersOfTeam;
    }

    public String getNameOfSport() {
        return nameOfSport;
    }

    public void setNameOfSport(String nameOfSport) {
        this.nameOfSport = nameOfSport;
    }

    public String getTypeOfSport() {
        return typeOfSport;
    }

    public void setTypeOfSport(String typeOfSport) {
        this.typeOfSport = typeOfSport;
    }

    public int getOlimpiaiPont(){
        switch (place){
            case 1:
                return 7;
            case 2:
                return 5;
            case 3:
                return 4;
            case 4:
                return 3;
            case 5:
                return 2;
            case 6:
                return 1;
            default:
                return 0;

        }
    }

    @Override
    public String toString() {
        return
                "Helyezés:" + " " + place +
                ", Sportolók száma:" + " " + membersOfTeam +
                ", Sportág:" + " " + nameOfSport + '\'' +
                ", Versenyszám:" + " " + typeOfSport + '\'';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Olimpia olimpia = (Olimpia) o;
        return place == olimpia.place && membersOfTeam == olimpia.membersOfTeam && nameOfSport.equals(olimpia.nameOfSport) && typeOfSport.equals(olimpia.typeOfSport);
    }

    @Override
    public int hashCode() {
        return Objects.hash(place, membersOfTeam, nameOfSport, typeOfSport);
    }
}
