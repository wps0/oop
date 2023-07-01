//package pl.wieczorekp.mim.oop.lab7;
//
//import java.util.List;
//
//public class Licytator {
//    public int[] przeprowadz(Przedmiot[] przedmioty, UczestnikLicytacji[] uczestnicy) {
//        int[] kupujacy = new int[przedmioty.length];
//        for (int i = 0; i < przedmioty.length; i++) {
//            kupujacy[i] = wylicytujPrzedmiot(przedmioty[i], uczestnicy);
//        }
//    }
//
//    public int wylicytujPrzedmiot(Przedmiot p, UczestnikLicytacji[] uczestnicy) {
//        int kupujacy = -1;
//
//        for (UczestnikLicytacji u : uczestnicy) {
//            if (u.czyKupuje(p)) {
//                u.kupPrzedmiot(p);
//                kupujacy = u.getId();
//                break;
//            }
//        }
//
//        return kupujacy;
//    }
//}
//
//public class Aukcja {
//    private Licytator licytator;
//    private List<UczestnikLicytacji> uczestnicy;
//    private List<Przedmiot> przedmioty;
//
//    Aukcja(Licytator licytator, List<UczestnikLicytacji> uczestnik, List<Przedmiot> przedmiot) {
//        this.licytator = licytator;
//        this.uczestnicy = uczestnik;
//        this.przedmioty = przedmiot;
//    }
//
//    public int[] przeprowadzAukcje() {
//        return licytator.przeprowadz(przedmioty.toArray(), uczestnicy.toArray());
//    }
//}
//
