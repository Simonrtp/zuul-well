
/**
 * Classe Game - le moteur principal du jeu d'aventure Zuul.
 * 
 * "La Roue des Saisons" est un jeu d'aventure simple, basé sur du texte.
 * Les joueurs peuvent se déplacer entre différentes salles en tapant
 * des commandes. 
 * 
 * Cette classe crée et initialise toutes les autres : elle crée les salles,
 * crée l'analyseur et démarre le jeu. Elle évalue et exécute aussi
 * les commandes que l'analyseur retourne.
 * 
 * @author  Michael Kolling and David J. Barnes + Simon ROPIOT
 * @version 2025.02.20
 */
public class Game 
{
    private Parser parser;
    private Room currentRoom;
        
    /**
     * Crée le jeu et initialise la carte interne.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    /**
     * Crée toutes les salles et relie leurs sorties les unes aux autres.
     */
    private void createRooms()
    {
        Room villageCentral, foretHiver, grotteHiver, clairierePrintemps;
        Room oasisEte, foretAutomne, sanctuaireAutomne;
      
        // Création des salles
        villageCentral = new Room("in the central neutral village, with a sanctuary dedicated to the Wheel of Seasons");
        foretHiver = new Room("in the Winter Forest, cold and covered in snow");
        grotteHiver = new Room("in the Winter Cave, the sanctuary of the Spirit of Winter");
        clairierePrintemps = new Room("in the Spring Glade, full of flowers and life");
        oasisEte = new Room("in the Summer Oasis, hot and bright");
        foretAutomne = new Room("in the Autumn Forest, with orange-colored leaves");
        sanctuaireAutomne = new Room("in the Autumn Sanctuary, sacred place of the Spirit of Autumn");

        
        // Initialisation des sorties des salles
        villageCentral.setExit("north", clairierePrintemps);
        villageCentral.setExit("east", oasisEte);
        villageCentral.setExit("west", foretHiver);
        villageCentral.setExit("south", foretAutomne);
        
        foretHiver.setExit("east", villageCentral);
        foretHiver.setExit("west", grotteHiver);
        
        grotteHiver.setExit("east", foretHiver);
        grotteHiver.setExit("down", sanctuaireAutomne);
        
        clairierePrintemps.setExit("south", villageCentral);
        
        oasisEte.setExit("west", villageCentral);
        
        foretAutomne.setExit("north", villageCentral);
        foretAutomne.setExit("south", sanctuaireAutomne);
        
        sanctuaireAutomne.setExit("north", foretAutomne);
        sanctuaireAutomne.setExit("up", grotteHiver);

        currentRoom = villageCentral;  // le jeu démarre au village central
    }

        /**
         * Routine principale du jeu. Boucle jusqu'à la fin du jeu.
         */
        public void play() 
    {            
        printWelcome();

        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing. Goodbye.");
    }

    /**
     * Prints the welcome message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to The Wheel of Seasons!");
        System.out.println("The Wheel of Seasons is a very boring text adventure game.");
        System.out.println("Type 'help' if you need assistance.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }


    /**
     * Étant donnée une commande, traite (c'est-à-dire exécute) la commande.
     * @param command La commande à traiter.
     * @return true si la commande termine le jeu, false sinon.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if (command.isUnknown()) {
        System.out.println("I don't know what you mean...");
        return false;
    }


        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("look")) {
            look(command);
        }
        else if (commandWord.equals("eat")) {
            eat(command);
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }

        return wantToQuit;
    }

    // Implémentation des commandes utilisateur :

    /**
     * Affiche des informations d'aide.
     * Ici nous affichons un message stupide et une liste des 
     * mots de commande.
     */
        private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("through the realm of The Wheel of Seasons.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }


    /** 
     * Essaie d'aller dans une direction. Si il y a une sortie, entre dans
     * la nouvelle salle, sinon affiche un message d'erreur.
     * @param command La commande contenant la direction.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // si il n'y a pas de second mot, nous ne savons pas où aller...
            System.out.println("Go where ?");
            return;
        }

        String direction = command.getSecondWord();

        // Essaie de quitter la salle actuelle.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("far lands !");
        }
        else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }

    /** 
     * "Quit" a été tapé. Vérifie le reste de la commande pour voir
     * si nous voulons vraiment quitter le jeu.
     * @param command La commande à vérifier.
     * @return true, si cette commande quitte le jeu, false sinon.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what ?");
            return false;
        }
        else {
            return true;  // signale que nous voulons quitter
        }
    }
    
        /**
     * Commande "look". Affiche la description détaillée de la pièce courante.
     * (Exercice 7.14)
     * @param command La commande "look" saisie par l'utilisateur.
     */
    private void look(Command command)
    {
        if (command.hasSecondWord()) {
            // Si l'utilisateur tape "look something", on affiche un message d'erreur (Exercice optionnel)
            System.out.println("I don't know how to look at something in particular yet.");
        } else {
            // Sinon, on affiche la description de la pièce
            System.out.println(currentRoom.getLongDescription());
        }
    }

        /**
     * Commande "eat". Pour l'instant, affiche juste un message.
     * @param command La commande "eat" saisie par l'utilisateur.
     */
    private void eat(Command command)
    {
        System.out.println("You have eaten now and you are not hungry any more.");
    }

}
