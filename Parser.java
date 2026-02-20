import java.util.Scanner;

/**
 * Cette classe fait partie du jeu "World of Zuul".
 * "World of Zuul" est un jeu d'aventure très simple, basé sur du texte.
 * 
 * Cet analyseur lit les entrées utilisateur et essaie de les interpréter comme une commande
 * d'aventure. Chaque fois qu'il est appelé, il lit une ligne du terminal et
 * tente d'interpréter la ligne comme une commande à deux mots. Il retourne la commande
 * sous forme d'un objet de la classe Command.
 *
 * L'analyseur a un ensemble de mots de commande connus. Il vérifie l'entrée utilisateur par rapport
 * aux commandes connues, et si l'entrée n'est pas l'une des commandes connues, il
 * retourne un objet commande marqué comme une commande inconnue.
 * 
 * @author  Michael Kolling and David J. Barnes + D.Bureau
 * @version 2008.03.30 + 2013.09.15
 */
public class Parser 
{
    private CommandWords aValidCW;  // (voir la classe CommandWords)
    private Scanner      aReader;   // permettra de lire les commandes au clavier

    /**
     * Constructeur par défaut qui crée les 2 objets prévus pour les attributs.
     */
    public Parser() 
    {
        this.aValidCW = new CommandWords();
        this.aReader  = new Scanner( System.in );
        // System.in désigne le clavier, comme System.out désigne l'écran
    } // Parser()

    /**
     * Récupère la commande suivante de l'utilisateur.
     * @return La prochaine commande de l'utilisateur.
     */
    public Command getCommand() 
    {
        String vInputLine;    // contiendra toute la ligne tapée
        String vWord1 = null;
        String vWord2 = null;

        System.out.print( "> " );  // affiche le prompt (invite de commande)

        vInputLine = this.aReader.nextLine(); // lit la ligne tapée au clavier

        // cherche jusqu'à 2 mots dans la ligne tapée
        Scanner vTokenizer = new Scanner( vInputLine );
        if ( vTokenizer.hasNext() ) {     // y a-t-il un mot suivant ?
            vWord1 = vTokenizer.next();      // récupère le premier mot
            if ( vTokenizer.hasNext() ) { // y a-t-il encore un mot suivant ?
                vWord2 = vTokenizer.next();  // récupère le deuxième mot
                // note : on ignore tout le reste de la ligne tapée !
            } // if
        } // if

        // Vérifie si le premier mot est une commande connue.
        // Si oui, crée un objet Command avec ce mot. (vWord2 peut être null)
        // Sinon, crée une commande vide avec "null" (pour dire 'commande inconnue').
        if ( this.aValidCW.isCommand( vWord1 ) ) {
            return new Command( vWord1, vWord2 );
        }
        else {
            return new Command( null, null ); 
        }
    } // getCommand()
    
    /**
     * Affiche la liste de tous les mots de commande valides.
     */
        /**
     * Affiche la liste de tous les mots de commande valides.
     */
    public void showCommands()
    {
        System.out.println(this.aValidCW.getCommandList());
    }
} // Parser
