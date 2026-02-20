/**
 * Classe Command - une commande du jeu d'aventure Zuul.
 *
 * Cette classe contient les informations sur la commande utilisateur :
 * le mot de commande (ex: "go") et le second mot éventuel (ex: "north").
 *
 * @author Simon ROPIOT
 * @version 2025.02.20
 */
public class Command
{ 
    private String aCommandWord;
    private String aSecondWord;
    /**
     * Crée un objet commande. Le premier et le second mot doivent être fournis,
     * mais l'un ou l'autre (ou les deux) peuvent être null.
     * @param pCommandWord Le premier mot de la commande. Null si la commande n'est pas reconnue.
     * @param pSecondWord Le second mot de la commande.
     */
    public  Command(final String pCommandWord, final String pSecondWord) {
        this.aCommandWord = pCommandWord;
        this.aSecondWord = pSecondWord;
    }
    
    /**
     * Retourne le mot de commande (le premier mot) de cette commande.
     * Si la commande n'était pas comprise, le résultat est null.
     * @return Le mot de commande.
     */
    public String getCommandWord()
    {
        return this.aCommandWord; 
    } 
    
    /**
     * Retourne le second mot de cette commande.
     * Retourne null s'il n'y avait pas de second mot.
     * @return Le second mot de la commande.
     */
    public String getSecondWord()
    {
        return this.aSecondWord; 
    } 
    
    /**
     * Vérifie si la commande a un second mot.
     * @return true si la commande a un second mot, false sinon.
     */
    public boolean hasSecondWord() {
        return this.aSecondWord != null;
    }
    
    /**
     * Vérifie si la commande est inconnue.
     * @return true si la commande est inconnue, false sinon.
     */
    public boolean isUnknown(){
        return this.aCommandWord == null;
    }

} // Command
