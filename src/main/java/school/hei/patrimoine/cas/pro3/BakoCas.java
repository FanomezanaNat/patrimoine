package school.hei.patrimoine.cas.pro3;

import static java.time.Month.APRIL;
import static school.hei.patrimoine.modele.Argent.ariary;
import static school.hei.patrimoine.modele.Devise.MGA;

import java.time.LocalDate;
import java.util.Map;
import java.util.Set;
import school.hei.patrimoine.cas.Cas;
import school.hei.patrimoine.modele.Argent;
import school.hei.patrimoine.modele.Devise;
import school.hei.patrimoine.modele.Personne;
import school.hei.patrimoine.modele.possession.Compte;
import school.hei.patrimoine.modele.possession.FluxArgent;
import school.hei.patrimoine.modele.possession.Materiel;
import school.hei.patrimoine.modele.possession.Possession;

public class BakoCas extends Cas {
  public BakoCas(LocalDate ajd, LocalDate finSimulation, Map<Personne, Double> possesseurs) {
    super(ajd, finSimulation, possesseurs);
  }

  @Override
  protected Devise devise() {
    return MGA;
  }

  @Override
  protected String nom() {
    return "Cas de Bako";
  }

  @Override
  protected void init() {}

  @Override
  protected void suivi() {}

  @Override
  public Set<Possession> possessions() {
    var au_8_AVRIL_2025 = LocalDate.of(2025, APRIL, 8);
    var dateIndeterminee = LocalDate.MAX;

    var compteCourantBNI =
        new Compte("Compte courant BNI", au_8_AVRIL_2025, new Argent(2_000_000, MGA));
    var compteEpargneBMOI =
        new Compte("Compte Epargne BMOI", au_8_AVRIL_2025, new Argent(625_000, MGA));
    var coffreFort =
        new Compte("espece dans le coffre fort", au_8_AVRIL_2025, new Argent(1_750_000, MGA));

    new FluxArgent(
        "contrat durée indéterminéé",
        compteCourantBNI,
        au_8_AVRIL_2025,
        dateIndeterminee,
        2,
        ariary(2_125_000));
    new FluxArgent(
        "virement vers BMOI",
        compteCourantBNI,
        au_8_AVRIL_2025,
        dateIndeterminee,
        3,
        ariary(-200_000));
    new FluxArgent(
        "virement depuis bni",
        compteEpargneBMOI,
        au_8_AVRIL_2025,
        dateIndeterminee,
        3,
        ariary(200_000));
    new FluxArgent(
        "colocation,nourriture,transport",
        compteCourantBNI,
        au_8_AVRIL_2025,
        dateIndeterminee,
        1,
        ariary(-1_300_000));

    var laptop =
        new Materiel(
            "ordinateur portable",
            au_8_AVRIL_2025.minusMonths(1),
            au_8_AVRIL_2025,
            ariary(3_000_000),
            -0.12);

    return Set.of(compteCourantBNI, laptop, compteEpargneBMOI, coffreFort);
  }
}
