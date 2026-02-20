/**
 * Cette classe fait partie du jeu "World of Zuul".
 * "World of Zuul" est un jeu d'aventure très simple, basé sur du texte.
 * 
 * Cette classe contient une énumération de tous les mots de commande connus du jeu.
 * Elle est utilisée pour reconnaître les commandes au fur et à mesure qu'elles sont tapées.
 *
 * @author  Michael Kolling and David J. Barnes + D.Bureau
 * @version 2008.03.30 + 2019.09.25
 */
public class CommandWords
{
    // un tableau constant qui contient tous les mots de commande valides
    private final String[] aValidCommands = { "go", "help", "quit", "look", "eat", "up", "down" };

    /**
     * Vérifie si une chaîne donnée est un mot de commande valide.
     * @param pString La chaîne à vérifier.
     * @return true si la chaîne donnée est une commande valide, false sinon.
     */
    public boolean isCommand( final String pString )
    {
        for ( int vI=0; vI<this.aValidCommands.length; vI++ ) {
            if ( this.aValidCommands[vI].equals( pString ) )
                return true;
        } // for
        // si nous arrivons ici, la chaîne n'a pas été trouvée dans les commandes
        return false;
    } // isCommand()
    
    /**
     * Affiche toutes les commandes valides sur la sortie standard.
     */
    public String getCommandList()
    {
        String vCommandList = "";
        for (String vCommand : this.aValidCommands) {
            vCommandList += vCommand + " ";
        }
        return vCommandList;
    }
} // CommandWords
