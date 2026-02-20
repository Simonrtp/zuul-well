import java.util.HashMap;
import java.util.Set;

/**
 * Classe Room - un lieu du jeu d'aventure Zuul.
 *
 * @author Simon ROPIOT
 * @version 2025.02.20
 */
public class Room
{
    private String aDescription;
    private HashMap<String, Room> aExits;
    
    // Ajout d'un commentaire simple pour l'attribut statique
    /** Room par défaut en cas de direction inconnue (non utilisée pour l'instant) */
    public static final Room UNKNOWN_DIRECTION = new Room("Unknown direction !");
    
    /**
     * Constructeur de la classe Room.
     * Crée une pièce décrite par la chaîne "description".
     * Au départ, il n'y a aucune sortie.
     * @param pDescription La description de la pièce (ex: "dans la cuisine").
     */
    public Room(final String pDescription)
    {
        this.aDescription = pDescription;
        this.aExits = new HashMap<String, Room>();
    }
    
    /**
     * Retourne la description de la pièce (telle que définie dans le constructeur).
     * @return La description de la pièce.
     */
    public String getDescription()
    {
        return this.aDescription;    
    } 
    
    /**
     * Définit une sortie pour cette pièce.
     * @param pDirection La direction de la sortie (ex: "north").
     * @param pNeighbor La pièce voisine dans cette direction.
     */
    public void setExit(final String pDirection, final Room pNeighbor) {
        this.aExits.put(pDirection, pNeighbor);
    }
    
    /**
     * Retourne la pièce atteinte si nous nous déplaçons dans la direction pDirection.
     * Si il n'y a pas de sortie dans cette direction, retourne null.
     * @param pDirection La direction souhaitée.
     * @return La pièce dans la direction donnée, ou null.
     */
    public Room getExit(final String pDirection) {
        return this.aExits.get(pDirection);
    }
    
    /**
     * Retourne une chaîne décrivant les sorties de la pièce,
     * par exemple "north east west".
     * Utilise un StringBuilder pour construire la chaîne.
     * @return Une description des sorties disponibles.
     */
    public String getExitString() {
        StringBuilder vExits = new StringBuilder();
        
        Set<String> vKeys = this.aExits.keySet();
        for (String vDirection : vKeys) {
            vExits.append(vDirection).append(" ");
        }
        
        return vExits.toString();
    }
    
    /**
     * Retourne une description longue de la pièce, de la forme:
     *     Vous êtes dans la cuisine.
     *     Sorties: north west
     * @return Une description complète de la pièce et de ses sorties.
     */
    public String getLongDescription()
    {
        return "You are " + this.aDescription + ".\n" + getExitString();
    }
    
} // Room
