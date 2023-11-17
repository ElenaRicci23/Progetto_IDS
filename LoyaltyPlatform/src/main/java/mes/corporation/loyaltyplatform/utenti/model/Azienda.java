package mes.corporation.loyaltyplatform.utenti.model;

import jakarta.persistence.*;
import mes.corporation.loyaltyplatform.fedelta.model.ProgrammaFedeltaAzienda;
import mes.corporation.loyaltyplatform.fedelta.model.TipoProgrammaFedelta;

import java.util.ArrayList;
import java.util.List;


/**
 * Questa classe rappresenta un'azienda all'interno del sistema.
 * Estende la classe Utente e include informazioni specifiche dell'azienda,
 * tra cui dati personali, tipo di programma fedeltà e riferimento al programma fedeltà basato su punti.
 */
@Entity
public class Azienda extends Utente {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    // Associazione uno-a-uno con dati personali dell'azienda
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "azienda_id")
    private DatiPersonaliAzienda datiPersonali = new DatiPersonaliAzienda();

    // Associazione uno-a-molti con i programmi fedeltà
    @OneToMany(mappedBy = "azienda", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProgrammaFedeltaAzienda> programmiFedeltaAzienda = new ArrayList<>();

    /**
     * Costruttore vuoto per l'entità Azienda.
     */
    public Azienda() {
    }

    /**
     * Costruttore per creare un'istanza di Azienda con dati iniziali.
     *
     * @param nome           Nome dell'azienda.
     * @param cognome        Cognome dell'azienda.
     * @param email          Indirizzo email dell'azienda.
     * @param password       Password dell'azienda.
     * @param datiPersonali  Dati personali dell'azienda.
     */
    public Azienda(String nome, String cognome, String email, String password, DatiPersonaliAzienda datiPersonali) {
        super(email, password);
        this.datiPersonali = datiPersonali;
        this.datiPersonali.setAzienda(this); // Imposta il collegamento bidirezionale
    }

    /**
     * Restituisce i dati personali dell'azienda.
     *
     * @return I dati personali dell'azienda.
     */
    public DatiPersonaliAzienda getDatiPersonali() {
        return datiPersonali;
    }

    public List<ProgrammaFedeltaAzienda> getProgrammiFedeltaAzienda() {
        return programmiFedeltaAzienda;
    }

    public void setProgrammiFedeltaAzienda(List<ProgrammaFedeltaAzienda> programmiFedeltaAzienda) {
        this.programmiFedeltaAzienda = programmiFedeltaAzienda;
    }


    /**
     * Imposta i dati personali dell'azienda e imposta il collegamento bidirezionale.
     *
     * @param datiPersonali I dati personali dell'azienda.
     */
    public void setDatiPersonali(DatiPersonaliAzienda datiPersonali) {
        this.datiPersonali = datiPersonali;
        this.datiPersonali.setAzienda(this); // Imposta il collegamento bidirezionale
    }

    /**
     * Restituisce l'ID dell'azienda.
     *
     * @return L'ID dell'azienda.
     */
    public Long getId() {
        return id;
    }

    /**
     * Imposta l'ID dell'azienda.
     *
     * @param id L'ID dell'azienda.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Imposta la partita IVA dell'azienda nei dati personali.
     *
     * @param partitaIva La partita IVA dell'azienda.
     */
    public void setPartitaIva(String partitaIva) {
        this.datiPersonali.setPartitaIva(partitaIva);
    }

    public void aggiungiProgrammaFedelta(ProgrammaFedeltaAzienda programmaFedeltaAzienda) {
        programmiFedeltaAzienda.add(programmaFedeltaAzienda);
    }


}



