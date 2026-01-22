package elisaraeli.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
// Annotazione OBBLIGATORIA. Questa classe viene mappata con una specifica tabella nel DB (w3d4)
// Hibernate andrà a creare la tabella in questione (oppure se esiste già, la modifica)

@Table(name = "evento") // opzionale, serve per personalizzare il nome della tabella
@Inheritance(strategy = InheritanceType.JOINED)
public class Evento {
    @Id // Annotazione OBBLIGATORIA. Dichiaro che questo attributo corrisponderà alla primary key della tabella
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Annotazione OPZIONALE (consigliata).
    // Fa generare al DB i valori per la primary key
    // IDENTITY indica che invece di biginteger si usa un bigserial.
    private long id; // long corrisponde al tipo biginteger (a meno di non specificare diversamente)

    @Column(name = "titolo", nullable = false, length = 50)
    private String titolo;

    @Column(name = "data_evento", nullable = false)
    private LocalDate dataEvento;

    @Column(name = "descrizione", nullable = false, columnDefinition = "TEXT")
    private String descrizione;

    @Column(name = "tipo_evento", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoEvento tipoEvento;

    @Column(name = "numero_massimo_partecipanti", nullable = false)
    private int numeroMassimoPartecipanti;

    @ManyToOne
    @JoinColumn(name = "location_evento", nullable = false)
    private Location location;

    public Evento() { // Il costruttore vuoto è OBBLIGATORIO per tutte le entities!
        // Viene usato da JPA per costruire degli oggetti quando leggeremo delle righe dalla tabella
    }

    public Evento(String titolo, LocalDate dataEvento, String descrizione,
                  TipoEvento tipoEvento, int numeroMassimoPartecipanti, Location location) {
        this.titolo = titolo;
        this.dataEvento = dataEvento;
        this.descrizione = descrizione;
        this.tipoEvento = tipoEvento;
        this.numeroMassimoPartecipanti = numeroMassimoPartecipanti;
        this.location = location;

    }

    // GETTER E SETTER
    public long getId() {
        return id;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public LocalDate getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(LocalDate dataEvento) {
        this.dataEvento = dataEvento;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public TipoEvento getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(TipoEvento tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public int getNumeroMassimoPartecipanti() {
        return numeroMassimoPartecipanti;
    }

    public void setNumeroMassimoPartecipanti(int numeroMassimoPartecipanti) {
        this.numeroMassimoPartecipanti = numeroMassimoPartecipanti;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

}