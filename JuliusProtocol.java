public class JuliusProtocol {

    private static final int WAITING = 0;
    private static final int CHATSTARTED = 1;
    private int state = WAITING;

    String returnStatement;

    public String processInput(String input) {

        if (state == WAITING) {
            returnStatement = "Welcome to JuliusServer.";
            state = CHATSTARTED;
        } else if (state == CHATSTARTED) {
            returnStatement = "I am server.";
            if (input.equals("Okay Thank U Bye.")) {
                returnStatement = "Okay Thank U Bye.";
            }
        }

        return returnStatement;
    }
}